package org.example.bot.command.listCommands.playlistsCommmands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GetMyPlaylistsCommand extends CommunicatorWrapper implements Command {
    private final PlayListService playListService = new PlayListService();
    private final UserService userService = new UserService();

    public GetMyPlaylistsCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public GetMyPlaylistsCommand() {
    }

    @Override
    public void execute() {
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                "Загрузка..."
        );

        var from = message.getFrom();
        var currUser = userService.findByName(from.getUserName());
        List<PlayListEntity> playlists = currUser.getPlaylists();
        if (playlists.isEmpty()) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "У вас ещё нет сохранённых плейлистов."
            );
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Ваши плейлисты: \n");
        AtomicInteger counter = new AtomicInteger();
        playlists.forEach(it ->
                sb.append(counter.incrementAndGet())
                        .append(". ")
                        .append(it.getName())
                        .append("\n")
        );
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                sb.toString()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}