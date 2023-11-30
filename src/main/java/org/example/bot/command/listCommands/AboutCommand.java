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
                        /get_singers - список исполнителей +
                        /get_popular - список популярных треков +
                        /start - запуск +
                        /about - о командах +
                        /help - помощь об избранной команде +
                        /create_playlist - создать плейлист +
                        /add - добавить песню в плейлист -
                        /get_playlist - получить плейлист -
                        /share_playlist - получить ссылку на плейлист -
                        /remove_playlist - удалить плейлист -
                        /get_my_playlists - получить список моих плейлистов - 
                        """.trim()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}

