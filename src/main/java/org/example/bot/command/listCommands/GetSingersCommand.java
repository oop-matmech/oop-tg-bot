package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.communicator.ICommunicator;
import org.example.service.music.MusicApi;
import org.example.utils.FormatArtists;
import org.telegram.telegrambots.meta.api.objects.Message;

public class GetSingersCommand extends CommunicatorWrapper implements Command {
    private final FormatArtists formatArtists = new FormatArtists();
    private final MusicApi musicApi = new MusicApi();

    public GetSingersCommand(ICommunicator communicator, Message message, Bot bot) {
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
        String api = formatArtists.format(musicApi.getTopArtists());
        String res = String.format("Список исполнителей: %s", api);
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                res.trim()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}