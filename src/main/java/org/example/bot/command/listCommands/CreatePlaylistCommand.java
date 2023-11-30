package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.dbService.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CreatePlaylistCommand extends CommunicatorWrapper implements Command {
    private final UserService userService = new UserService();

    public CreatePlaylistCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    @Override
    public void execute() {
        var args = message.getText().replace("/create_playlist", "").trim().split(" ");
        var from = message.getFrom();
        var isExist = !userService.findByName(from.getUserName()).getName().isEmpty();
        try {
            if (!isExist) {
                UserEntity user = new UserEntity(from.getUserName(), from.getId().toString());
                userService.save(user);
            }
            var currUser = userService.findByName(from.getUserName());
            PlayListEntity playList = new PlayListEntity(args[0], args[1], currUser);
            userService.addPlaylist(currUser.getId(), playList);
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Создан плейлист с именем: " + args[0]
            );
        } catch (Exception e) {
            e.printStackTrace();
            communicator.sendText(
                    bot,
                    message.getFrom().getId(),
                    "Произошла ошибка: " + e.getMessage()
            );
        }
    }

    @Override
    public String name() {
        return "start";
    }
}