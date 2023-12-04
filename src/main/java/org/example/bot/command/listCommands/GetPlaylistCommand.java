package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.utils.PlaylistUrlParser;
import org.telegram.telegrambots.meta.api.objects.Message;

public class GetPlaylistCommand extends CommunicatorWrapper implements Command {
    private PlayListService playListService = new PlayListService();

    public GetPlaylistCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public GetPlaylistCommand() {
    }

    @Override
    public void execute() {
        var args = message.getText().split(" ");
        var url = args[1];
        var playList = PlaylistUrlParser.getPlaylistFromUrl(url);
        StringBuilder sb = new StringBuilder();
        sb.append("Песни из плейлиста ")
                .append(playList.getName())
                .append(": \n");
        playList.getSongs().forEach(it -> sb.append(it.getName())
                .append("\n")
                .append(it.getArtistName())
                .append("\n")
                .append(it.getUrl())
                .append("\n\n\n")
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