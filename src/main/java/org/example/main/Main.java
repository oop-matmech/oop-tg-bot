package org.example.main;

import org.example.bot.Bot;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.SessionFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main {

    public static void main(String[] args) throws TelegramApiException {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(bot);
    }
}