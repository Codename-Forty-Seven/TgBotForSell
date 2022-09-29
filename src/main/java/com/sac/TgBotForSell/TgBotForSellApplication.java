package com.sac.TgBotForSell;

import core.CoreBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TgBotForSellApplication {

    public static void main(String[] args) throws TelegramApiException {
        SpringApplication.run(TgBotForSellApplication.class, args);
        CoreBot runBot = new CoreBot();
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(runBot);
    }

}
