package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AboutCommand extends CommunicatorWrapper implements Command {
    public AboutCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public AboutCommand() {
    }

    @Override
    public void execute() {
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                """
                        /get_popular - список популярных треков \n
                        /start - запуск \n
                        /about - о командах \n
                        /help - помощь об избранной команде \n
                        /find [имя песни] - найти песню по названию \n
                        /create_playlist [имя] - создать плейлист \n
                        /add [имя плейлиста] [url песни]- добавить песню в плейлист \n
                        /get_playlist [url плейлиста]- получить плейлист \n
                        /share_playlist [имя плейлиста]- получить ссылку на плейлист \n
                        /get_my_playlists - получить список моих плейлистов \n
                        /get_songs_from_playlist [имя плейлиста] - получить список песен из плейлиста \n\n
                        /get_top_10_24h - получить топ 10 треков за день -
                        /get_top_10_1w - получить топ 10 треков за неделю -
                        /get_top_10_1m - получить топ 10 треков за месяц -
                        """.trim()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}

