package ru.bryanin.dev.bryanintelegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.dto.TelegramUser;
import ru.bryanin.dev.bryanintelegrambot.webclient.WebClient;

@Component
public class UserRegisterCommandHandler implements KnownHandler {

    private final String COMMAND = "/register";
    private final WebClient webClient;

    public UserRegisterCommandHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String handle(Message message) {
        Long userTelegramId = message.getFrom().getId();
        String userName = message.getFrom().getUserName();
        TelegramUser user = new TelegramUser(userTelegramId, userName);
        return webClient.register(user);
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
