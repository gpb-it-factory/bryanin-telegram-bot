package ru.bryanin.dev.bryanintelegrambot.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TelegramBotTest {

    @Mock
    private TelegramBot telegramBot;
    @Value("${GPB_BOT_TOKEN}")
    private String token;
    @Value("${GPB_BOT_NAME}")
    private String name;

    // TODO
    // Изучить подходы к тестированию телеграм-ботов

    @Test
    void getBotUsernameReturnsTrueName() {
        Assertions.assertEquals(telegramBot.getBotUsername(), name);
    }
}