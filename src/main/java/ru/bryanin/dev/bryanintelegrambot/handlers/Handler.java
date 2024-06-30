package ru.bryanin.dev.bryanintelegrambot.handlers;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {
    String handle(Message message);
    String getCommand();
}

