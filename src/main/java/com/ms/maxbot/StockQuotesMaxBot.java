package com.ms.maxbot;

import com.ms.maxbot.api.IssMoexStockApi;
import dtos.MarketDataDto;
import dtos.StockDataDto;
import dtos.StockInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public final class StockQuotesMaxBot extends TelegramLongPollingBot {
    private static final String TRADING_MODE = "TQBR";
    private static final String BOT_NAME = "StockQuotesMaxBot";
    private static final String TOKEN = "6085973778:AAF7PCo9qo3-pxNrihcOzMFS0wv3ynq5edc";
    private final IssMoexStockApi issMoexStockApi;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (update.hasMessage() && message.hasText()) {
            long chatId = message.getChatId();
            String[] commandData = message.getText().split(" ");
            switch (commandData[0]) {
                case "/start" -> startAnswer(chatId, message);
                case "/stock-info" -> stockInfoAnswer(chatId, commandData);
                case "/stock-market-info" -> stockMarketInfoAnswer(chatId, commandData);
                case "/help" -> helpAnswer(chatId);
                default -> defaultAnswer(chatId, message);
            }
        }
    }

    private void helpAnswer(long chatId) {
        String answer = "Stock info example: \"/stock-info\" SBER \n";
        answer += "Stock market info example: \"/stock-market-info\" SBER \n";
        sendAnswer(chatId, answer);
    }

    private void startAnswer(long chatId, Message message) {
        String answer = "Hello, " + message.getChat().getFirstName() + ". Nice to meet you!";
        sendAnswer(chatId, answer);
    }

    private void stockInfoAnswer(long chatId, String[] params) {
        if (params.length > 1) {
            StockInfoDto stockInfo = issMoexStockApi.getStockInfoByCode(params[1], TRADING_MODE).blockLast();
            if (stockInfo != null && stockInfo.getStockData() != null && !stockInfo.getStockData().isEmpty()) {
                StockDataDto stockData = stockInfo.getStockData().get(0);
                String answer = "Stock name: " + stockData.getStockName() + "\n";
                answer += "Register number: " + stockData.getRegNumber() + "\n";
                answer += "Listing level: " + stockData.getListLevel() + "\n";
                sendAnswer(chatId, answer);
            } else {
                sendAnswer(chatId, "Stock data has not found for " + params[1]);
            }
        } else {
            sendAnswer(chatId, "Command \"stock-info\" must has parameter StockID");
        }
    }

    private void stockMarketInfoAnswer(long chatId, String[] params) {
        if (params.length > 1) {
            StockInfoDto stockInfo = issMoexStockApi.getStockInfoByCode(params[1], TRADING_MODE).blockLast();
            System.out.println(stockInfo);
            if (stockInfo != null && stockInfo.getStockData() != null && !stockInfo.getStockData().isEmpty()) {
                MarketDataDto marketData = stockInfo.getMarketData().get(0);
                String answer = "Max price: " + marketData.getMaxPrice() + "\n";
                answer += "Min price: " + marketData.getMinPrice() + "\n";
                answer += "Open price: " + marketData.getOpenPrice() + "\n";
                answer += "Close price: " + marketData.getClosePrice() + "\n";
                answer += "Date: " + marketData.getDateTime() + "\n";
                sendAnswer(chatId, answer);
            } else {
                sendAnswer(chatId, "Stock market data has not found for " + params[1]);
            }
        } else {
            sendAnswer(chatId, "Command \"stock-info\" must has parameter StockID");
        }
    }

    private void defaultAnswer(long chatId, Message message) {
        sendAnswer(chatId, "Command unrecognized. Sorry, " + message.getChat().getFirstName());
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
