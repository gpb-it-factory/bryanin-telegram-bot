package ru.bryanin.dev.bryanintelegrambot.handlers;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Handler {
    String handle(Message message);
    String getCommand();
}

