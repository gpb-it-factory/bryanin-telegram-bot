package ru.bryanin.dev.bryanintelegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    public TelegramBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            String message = "Команда не распознана. Попробуйте еще";

            CommandPool commandPool = CommandPool.getCommandPoolFromCommand(text);
            if (commandPool != null)  {
                switch (commandPool) {
                    case START:
                        message = "Добро пожаловать в GPB Telegram bot!";
                        break;
                    case HELP:
                        message = "Основной функционал бота на данный момент - отвечать \"pong\" на команду \"/ping\"";
                        break;
                    case PING:
                        message = "pong";
                        break;
                }
            }

            try {
                sendApiMethod(
                        SendMessage
                                .builder()
                                .chatId(chatId.toString())
                                .text(message)
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
