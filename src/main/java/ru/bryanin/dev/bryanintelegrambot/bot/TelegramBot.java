package ru.bryanin.dev.bryanintelegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    public TelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() == null) {
            return;
        }
        final Message message = update.getMessage();
        long chatId = message.getChatId();
        if(message.hasText()) {
            String textFromReceivedMessage = message.getText();
            String textAtSendingMessage = "Команда не распознана. Попробуйте еще";

            Optional<CommandPool> optionalCommandPool = CommandPool.getCommandPoolFromCommand(textFromReceivedMessage);
            if (optionalCommandPool.isPresent())  {
                switch (optionalCommandPool.get()) {
                    case START:
                        textAtSendingMessage = "Добро пожаловать в GPB Telegram bot!";
                        break;
                    case HELP:
                        textAtSendingMessage = "Основной функционал бота на данный момент - отвечать \"pong\" на команду \"/ping\"";
                        break;
                    case PING:
                        textAtSendingMessage = "pong";
                        break;
                }
            }

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
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
