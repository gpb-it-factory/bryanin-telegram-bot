package ru.bryanin.dev.bryanintelegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    private final Action action;

    public TelegramBot(@Value("${bot.token}") String botToken, Action action) {
        super(botToken);
        this.action = action;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() == null) {
            return;
        }
        final Message message = update.getMessage();
        long chatId = message.getChatId();
        if(message.hasText()) {
            String textAtSendingMessage = action.response(message);
            sendSimpleMessage(chatId, textAtSendingMessage);
        }
    }

    public void sendSimpleMessage(long chatId, String textAtSendingMessage) {
        try {
            sendApiMethod(
                    SendMessage
                            .builder()
                            .chatId(chatId)
                            .text(textAtSendingMessage)
                            .build()
            );
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
