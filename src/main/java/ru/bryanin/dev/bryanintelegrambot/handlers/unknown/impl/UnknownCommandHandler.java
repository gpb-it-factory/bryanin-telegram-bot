package ru.bryanin.dev.bryanintelegrambot.handlers.unknown.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.handlers.unknown.UnknownHandler;

@Component
public class UnknownCommandHandler implements UnknownHandler {

    private final String COMMAND = "";
    private final String RESPONSE = "Команда не распознана. Попробуйте еще";

    @Override
    public String handle(Message message) {
        return RESPONSE;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
