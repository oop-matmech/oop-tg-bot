package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class GetSongsFromPlaylistCommand extends CommunicatorWrapper implements Command {
    private final PlayListService playListService = new PlayListService();
    private final UserService userService = new UserService();


    public GetSongsFromPlaylistCommand() {
    }

    public GetSongsFromPlaylistCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    @Override
    public void execute() {
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                """
                        Загрузка...
                        """.trim()
        );
        var args = message.getText().trim().split(" ");
        if (args.length == 1) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Не введено имя плейлиста."
            );
            return;
        }
        var playlistName = args[1];
        var from = message.getFrom();

        List<PlayListEntity> results = playListService
                .findByName(playlistName);

        results.forEach(it -> {
            System.out.println("USR: " + it.toString());
        });

        PlayListEntity playList = results
                .stream()
                .filter(it -> it.getUser().getName().equals(from.getUserName()))
                .toList()
                .get(0);

        Set<SongEntity> songs = playListService.getSongsFromPlaylist(playList.getId());
        StringBuilder sb = new StringBuilder();
        AtomicInteger counter = new AtomicInteger();
        if (songs.isEmpty()) {
            sb.append("Плейлист пуст.");
        } else {

            String begin = "Песни из плейлиста " + playlistName + ": \n";
            sb.append(begin);

            songs.forEach(it -> {
                sb.append("-----").append(counter.incrementAndGet()).append("-----\n");
                sb.append(it.toString());
                sb.append("\n\n");
            });
        }
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                sb.toString()
        );
    }

    @Override
    public String name() {
        return "get";
    }
}