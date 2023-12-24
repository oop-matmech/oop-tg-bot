package org.example.bot.command.listCommands.UserBetweenUserCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.UserService;
import org.example.utils.PlaylistUrlParser;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class SharePlaylistCommand extends CommunicatorWrapper implements Command {
    private final PlayListService playListService = new PlayListService();
    private final UserService userService = new UserService();

    public SharePlaylistCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public SharePlaylistCommand() {
    }

    @Override
    public void execute() {
        var args = message.getText().split(" ");
        if (args.length == 1) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Не введено имя плейлиста."
            );
            return;
        }
        var playlistName = args[1];
        var me = userService.findByName(message.getFrom().getUserName());
        if (!playListService.userAlreadyHasPlayList(me, playlistName)) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Не могу поделиться плейлистом, которого не существует!");
        }
        var res = me.getPlaylists().stream()
                .filter(it -> it.getName().equals(playlistName))
                .toList()
                .get(0);
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                PlaylistUrlParser.toURL(res)
        );
    }

    @Override
    public String name() {
        return "start";
    }
}