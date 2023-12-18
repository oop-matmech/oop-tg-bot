package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbService.StatsService;
import org.example.service.music.MusicApi;
import org.example.utils.FormatArtists;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.SortedMap;

public class GetTopSongsCommand extends CommunicatorWrapper implements Command {
    private final StatsService statsService = new StatsService();

    public GetTopSongsCommand() {
    }

    public GetTopSongsCommand(ICommunicator communicator, Message message, Bot bot) {
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

        var args = message.getText().split(" ");
        var command = args[0];
        StringBuilder res = new StringBuilder();

        var itemStats = statsService.getStatsDay();
        switch (command) {
            case "/get_top_10_24h":
                itemStats = statsService.getStatsDay();
                res.append("Топ за 24 часа: \n\n");
                break;
            case "/get_top_10_1w":
                itemStats = statsService.getStatsWeek();
                res.append("Топ за неделю: \n\n");
                break;
            case "/get_top_10_1m":
                itemStats = statsService.getStatsMonth();
                res.append("Топ за месяц: \n\n");
                break;
        }


        int idx = 1;
        for (String key : itemStats.keySet()) {
            if (idx == 11) {
                break;
            } else {
                res.append(String.format("%s. %s - %s прослушиваний \n", idx, key, itemStats.get(key)));
                idx++;
            }
        }

        communicator.sendText(
                bot,
                message.getFrom().getId(),
                res.toString()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}