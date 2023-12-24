package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpCommand extends CommunicatorWrapper implements Command {
    public HelpCommand() {
    }

    public HelpCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    @Override
    public void execute() {
        var args = message.getText().trim().split(" ");
        var res = args.length > 1 ? args[1] : args[0];
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                helper(res).trim()
        );
    }

    public static String helper(String command) {
        return switch (command) {
            case "about" -> "информация о командах";
            case "get_popular" -> "Список исполнителей";
            case "share" -> "Делиться плейлистом";
            case "add" -> "Добавить песню";
            case "get_playlist" -> "Получить плейлист";
            case "get_my_playlists" -> "Получить все мои плейлисты";
            default ->
                    "Привет! Я бот, который поможет тебе хранить все твои песни в плейлистах!\nВызови команду /about, чтобы узнать о всех командах!";
        };
    }

    @Override
    public String name() {
        return "start";
    }
}
