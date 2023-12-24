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
import org.example.service.music.service.tracks.findtracks.MusicFindTracks;
import org.example.service.music.service.tracks.info.MusicGetInfo;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddSongCommand extends CommunicatorWrapper implements Command {
    private final SongsService songsService = new SongsService();
    private final PlayListService playListService = new PlayListService();
    private final MusicGetInfo musicGetInfo = new MusicGetInfo();



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
        if (args.length < 3) {
            if (args.length == 1) {
                messageOutput = "Не введены имя плейлиста и url песни";
            }
            if (args.length == 2) {
                messageOutput = "Не введён url песни";
            }
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    messageOutput
            );
            return;
        }
        var playlistName = args[1];
        var url = args[2];
        var trashName = url.split("/");
        var trackName = Arrays.stream(trashName[4].split("[+|,]")).collect(Collectors.joining(" ")) + " " + Arrays.stream(trashName[6].split("[+|,]")).collect(Collectors.joining(" "));

        ArrayList<GetTopItemTrackEntity> music = new MusicFindTracks().getTracksFoundByName(trackName, "30");
        List<GetTopItemTrackEntity> trackList = music
                .stream()
                .filter(it -> it.getUrl().equals(url))
                .toList();
        GetTopItemTrackEntity track = new GetTopItemTrackEntity();
        if (!trackList.isEmpty()) {
            track = trackList.get(0);
            track = musicGetInfo.getTrackInfo(track.getName(), track.getArtist().name);
            SongEntity newSong = new SongEntity(track.getName(), track.getDuration(), track.getUrl(), track.getArtist().name);
            try {
                songsService.save(newSong);

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                var currentPlaylist = playListService.findByName(playlistName);
                var currPlaylist = currentPlaylist
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
                    messageOutput = "Завершено.";
                }
            } catch (Exception e) {
                messageOutput = "Плейлиста с таким именем не существует.";
            } finally {
                communicator.sendText(
                        bot,
                        message.getFrom().getId(),
                        messageOutput
                );
            }
        } else {
            messageOutput = "Не удалось добавить песню.";
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
