package ru.bryanin.dev.bryanintelegrambot.handlers.known.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.handlers.known.KnownHandler;

@Component
public class PingCommandHandler implements KnownHandler {

    private final String COMMAND = "/ping";
    private final String RESPONSE = "pong";

    @Override
    public String handle(Message message) {
        return RESPONSE;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
