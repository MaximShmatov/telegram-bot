package com.ms.maxbot.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends MaxBotCommand {

    public StartCommand() {
        super("/start", "Answer - hello message");
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        String answer = "Hello, " + message.getChat().getFirstName() + ". Nice to meet you!";
        message.setText(answer);
        super.processMessage(absSender, message, arguments);
    }
}
