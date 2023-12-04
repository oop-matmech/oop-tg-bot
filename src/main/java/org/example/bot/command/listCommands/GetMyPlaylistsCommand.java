package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

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
        var from = message.getFrom();
        var currUser = userService.findByName(from.getUserName());
        List<PlayListEntity> playlists = currUser.getPlaylists();
        StringBuilder sb = new StringBuilder();
        sb.append("Ваши плейлисты: \n");
        playlists.forEach(it ->
                sb.append(it.getId())
                        .append(". ")
                        .append(it.getName())
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