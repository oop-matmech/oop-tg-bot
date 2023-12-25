package org.example.bot.command.listCommands.UserBetweenUserCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.utils.PlaylistUrlParser;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.concurrent.atomic.AtomicInteger;

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
        if (args.length == 1) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Не введён url плейлиста."
            );
            return;
        }
        var url = args[1];
        PlayListEntity playList = new PlayListEntity();
        try {
            playList = PlaylistUrlParser.getPlaylistFromUrl(url);
        } catch (NullPointerException nullEx) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Такого плейлиста не существует.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        AtomicInteger counter = new AtomicInteger();
        sb.append("Песни из плейлиста \"")
                .append(playList.getName())
                .append("\": \n");
        playList.getSongs().forEach(it -> sb
                .append("-----")
                .append(counter.incrementAndGet())
                .append("-----\n")
                .append(it.getName())
                .append("\n")
                .append(it.getArtistName())
                .append("\n-----------\n")
                .append("URL: ")
                .append(it.getUrl())
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