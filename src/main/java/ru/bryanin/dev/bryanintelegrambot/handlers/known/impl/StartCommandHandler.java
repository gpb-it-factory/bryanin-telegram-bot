package ru.bryanin.dev.bryanintelegrambot.handlers.known.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.handlers.known.KnownHandler;

@Component
public class StartCommandHandler implements KnownHandler {

    private final String COMMAND = "/start";
    private final String RESPONSE = "Добро пожаловать в GPB Telegram bot!";

    @Override
    public String handle(Message message) {
        return RESPONSE;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
