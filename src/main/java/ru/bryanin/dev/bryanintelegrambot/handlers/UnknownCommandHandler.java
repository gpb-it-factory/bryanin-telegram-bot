package ru.bryanin.dev.bryanintelegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

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
