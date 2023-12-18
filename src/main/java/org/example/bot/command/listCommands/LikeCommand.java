package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.dbEntities.StatsEntity;
import org.example.db.UserDatabase.dbService.SongsService;
import org.example.db.UserDatabase.dbService.StatsService;
import org.example.db.UserDatabase.dbService.UserService;
import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;
import org.example.service.music.service.tracks.topTracks.MusicTopTracks;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public class LikeCommand extends CommunicatorWrapper implements Command {
    private final MusicTopTracks musicTopTracks = new MusicTopTracks();
    private final StatsService statsService = new StatsService();
    private final UserService userService = new UserService();
    private final SongsService songsService = new SongsService();

    public LikeCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public LikeCommand() {
    }

    @Override
    public void execute() {
        var args = message.getText().split(" ");
        var songUrl = args[1];
        ArrayList<GetTopItemTrackEntity> music = musicTopTracks.getTopTracks();
        GetTopItemTrackEntity track = music
                .stream()
                .filter(it -> it.getUrl().equals(songUrl))
                .toList()
                .get(0);
        SongEntity newSong = new SongEntity(track.getName(), track.getDuration(), track.getUrl(), track.getArtist().name);

        try {
            songsService.save(newSong);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            var currSong = songsService.findByUrl(newSong.getUrl());
            var from = message.getFrom();
            var currUser = userService.findByName(from.getUserName());
            StatsEntity stats = new StatsEntity(currUser, currSong);
            statsService.save(stats);
        } catch (Exception e) {
            e.printStackTrace();
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    """
                            Ошибка
                            """.trim()
            );
        } finally {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    """
                            Ого хороший вкус
                            """.trim()
            );
        }
    }

    @Override
    public String name() {
        return "start";
    }
}
