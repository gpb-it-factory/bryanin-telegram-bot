package ru.bryanin.dev.bryanintelegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bryanin.dev.bryanintelegrambot.handlers.Handler;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    private final Dispatcher dispatcher;

    public TelegramBot(@Value("${bot.token}") String botToken, Dispatcher dispatcher) {
        super(botToken);
        this.dispatcher = dispatcher;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() == null) {
            return;
        }

        final Message message = update.getMessage();
        long chatId = message.getChatId();

        if(message.hasText()) {
            Handler handler = dispatcher.getHandler(message.getText());
            String textAtSendingMessage = handler.handle(message);
            sendMessage(chatId, textAtSendingMessage);
        }
    }

    public void sendMessage(long chatId, String textAtSendingMessage) {
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
