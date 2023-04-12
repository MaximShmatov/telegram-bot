package com.ms.maxbot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class StockQuotesMaxBot extends TelegramLongPollingBot {
    private static final String BOT_NAME = "MaxBot";
    private static final String TOKEN = "6085973778:AAF7PCo9qo3-pxNrihcOzMFS0wv3ynq5edc";

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasMessage() && message.hasText()) {
            long chatId = message.getChatId();
            switch (message.getText()) {
                case "/start" -> helloAnswer(chatId, message);
                case "/stock-quotes" -> summaryStockQuotesAnswer(chatId, message);
                default -> defaultAnswer(chatId, message);
            }
        }
    }

    private void summaryStockQuotesAnswer(long chatId, Message message) {
    }

    private void helloAnswer(long chatId, Message message) {
        String answer = "Hello, " + message.getChat().getFirstName() + ". Nice to meet you!";
        sendAnswer(chatId, answer);
    }

    private void defaultAnswer(long chatId, Message message) {
        sendAnswer(chatId, "I don't understand you. Sorry, " + message.getChat().getFirstName());
    }

    private void sendAnswer(long chatId, String answer) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(answer);
        try {
            executeAsync(message);
        } catch (TelegramApiException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
