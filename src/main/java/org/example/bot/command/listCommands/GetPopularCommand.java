package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.communicator.ICommunicator;
import org.example.service.music.MusicApi;
import org.example.utils.FormatTracks;
import org.telegram.telegrambots.meta.api.objects.Message;

public class GetPopularCommand extends CommunicatorWrapper implements Command {
    private final FormatTracks formatTracks = new FormatTracks();
    private final MusicApi musicApi = new MusicApi();

    public GetPopularCommand(ICommunicator communicator, Message message, Bot bot) {
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
        String api = formatTracks.format(musicApi.getTopTracks());
        String res = String.format("Список чартов:\n%s", api);
        String[] arr = res.split("\n\n");
        for (String s : arr) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    s
            );
        }
    }

    @Override
    public String name() {
        return "start";
    }
}