package ru.bryanin.dev.bryanintelegrambot.bot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bryanin.dev.bryanintelegrambot.AbstractTest;

@ExtendWith(MockitoExtension.class)
class ActionsTest extends AbstractTest {

    final String START_COMMAND ="/start";
    final String HELP_COMMAND = "/help";
    final String REGISTER_COMMAND = "/register";
    final String CREATE_ACCOUNT_COMMAND = "/createaccount";
    final String PING_COMMAND = "/ping";

    @BeforeEach
    void setUp() {

    }

//    @Test
//    void responseForStartCommand(String START_COMMAND) {
//
//    }
}