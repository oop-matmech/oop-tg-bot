package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.service.music.MusicApi;
import org.example.utils.FormatTracks;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FindCommand extends CommunicatorWrapper implements Command {
    private final FormatTracks formatTracks = new FormatTracks();
    private final MusicApi musicApi = new MusicApi();

    public FindCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public FindCommand() {
    }

    @Override
    public void execute() {
        var trackName = message.getText().replace("/find", "").trim();
        if (trackName.isEmpty()) {
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Не введено имя песни."
            );
            return;
        }
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                """
                        Ищем...
                        """.trim()
        );
        String res = "К сожалению, не удалось найти песни.";
        String api = formatTracks.format(musicApi.getTracksFoundByName(trackName, "10"));
        if (!api.equals(res)) {
            res = String.format("Найденные песни:\n%s", api);
        }
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
        return "find";
    }
}
