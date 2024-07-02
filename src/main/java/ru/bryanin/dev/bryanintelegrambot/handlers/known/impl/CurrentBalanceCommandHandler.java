package ru.bryanin.dev.bryanintelegrambot.handlers.known.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.dto.TelegramUser;
import ru.bryanin.dev.bryanintelegrambot.handlers.known.KnownHandler;
import ru.bryanin.dev.bryanintelegrambot.webclient.WebClient;

@Component
public class CurrentBalanceCommandHandler implements KnownHandler {

    private final String COMMAND = "/currentbalance";
    private final WebClient webClient;

    public CurrentBalanceCommandHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String handle(Message message) {
        Long userTelegramId = message.getFrom().getId();
        String userName = message.getFrom().getUserName();
        TelegramUser user = new TelegramUser(userTelegramId, userName);
        return webClient.requestBalance(user);
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
