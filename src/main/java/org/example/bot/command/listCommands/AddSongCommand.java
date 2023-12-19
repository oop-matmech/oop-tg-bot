package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.SongsService;
import org.example.db.UserDatabase.dbService.UserService;
import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;
import org.example.service.music.service.tracks.topTracks.MusicTopTracks;
import org.example.utils.FormatTracks;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public class AddSongCommand extends CommunicatorWrapper implements Command {
    private final SongsService songsService = new SongsService();
    private final PlayListService playListService = new PlayListService();
    private final MusicTopTracks musicTopTracks = new MusicTopTracks();


    public AddSongCommand() {
    }

    public AddSongCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    @Override
    public void execute() {
        var messageOutput = "";
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                "Загрузка..."
        );

        var args = message.getText().split(" ");
        var playlistName = args[1];
        var url = args[2];

        ArrayList<GetTopItemTrackEntity> music = musicTopTracks.getTopTracks();
        GetTopItemTrackEntity track = music
                .stream()
                .filter(it -> it.getUrl().equals(url))
                .toList()
                .get(0);
        SongEntity newSong = new SongEntity(track.getName(), track.getDuration(), track.getUrl(), track.getArtist().name);
        songsService.save(newSong);

        var currPlaylist = playListService
                .findByName(playlistName)
                .stream()
                .filter(it -> it.getUser().getName().equals(message.getFrom().getUserName()))
                .toList()
                .get(0);

        try {
            SongEntity currSong = songsService.findByUrl(newSong.getUrl());
            var res = playListService.addSongToPlaylist(currPlaylist.getId(), currSong);
            currSong.addPlaylist(currPlaylist);
            songsService.update(currSong);
            if (res) {
                messageOutput = "Песня успешно добавлена";
            } else {
                messageOutput = "Ошибка в формате";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            messageOutput = "Завершено";
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    messageOutput
            );
        }
    }

    @Override
    public String name() {
        return null;
    }
}
