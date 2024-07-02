package ru.bryanin.dev.bryanintelegrambot.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import ru.bryanin.dev.bryanintelegrambot.AbstractTest;

class TelegramBotTest extends AbstractTest {

    @Mock
    private TelegramBot telegramBot;
    @Value("${GPB_BOT_NAME}")
    private String name;

    @Test
    void botUsernameReturnsTrueName() {
        Assertions.assertEquals(telegramBot.getBotUsername(), name);
    }

}