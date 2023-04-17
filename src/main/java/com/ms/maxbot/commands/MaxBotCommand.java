package com.ms.maxbot.commands;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Slf4j
public class MaxBotCommand implements IBotCommand {
    @Getter
    private final String commandIdentifier;
    @Getter
    private final String description;

    protected MaxBotCommand(String commandIdentifier, String description) {
        this.commandIdentifier = commandIdentifier;
        this.description = description;
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        try {
            SendMessage sendMessage = SendMessage
                    .builder()
                    .chatId(message.getChatId().toString())
                    .text(message.getText())
                    .build();
            absSender.execute(sendMessage);
        } catch (TelegramApiException ex) {
            log.error(String.format("Process message error: %s", ex.getMessage()), ex);
        }
        log.info(String.format(String.format("Process message: %s(%s)", message.getText(), Arrays.toString(arguments))));
    }

}
