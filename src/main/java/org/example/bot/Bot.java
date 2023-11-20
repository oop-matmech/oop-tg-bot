package org.example.bot;

import org.example.bot.command.CommandHandler;
import org.example.bot.communicator.Communicator;
import org.example.bot.config.BotConfig;
import org.example.db.UserDatabase.Database;
import org.example.db.UserDatabase.dbMethods.UserTable;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bot extends TelegramLongPollingBot {

    private final Communicator communicator = new Communicator();
    private final CommandHandler commandHandler = new CommandHandler(this, communicator);

    private Database db;

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return BotConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();

        try {
            String id = message.getFrom().getId().toString();
            String userName = message.getFrom().getUserName();
            ResultSet rs = db.getUserTable().getItemById(db.getConnection(), id);
            ResultSet rsAll = db.getUserTable().getAll(db.getConnection());
            while (rs.next()) {
                System.out.println(rs.getString(UserTable.COLUMN_ID));
                System.out.println(rs.getString(UserTable.TG_ID));
                System.out.println(rs.getString(UserTable.TG_NAME));
            }
        } catch (NullPointerException e) {
            System.out.println("fucking npe: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (message.isCommand()) {
            var command = CommandHandler.toCommand(message.getText());
            commandHandler.handle(command, message);
        } else {
            communicator.copyMessage(this, message);
        }
    }
}
