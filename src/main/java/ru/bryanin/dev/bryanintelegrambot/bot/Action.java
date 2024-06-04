package ru.bryanin.dev.bryanintelegrambot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.webclient.WebClient;

@Component
public class Action {

    private final WebClient webClient;

    final String START_COMMAND ="/start";
    final String HELP_COMMAND = "/help";
    final String REGISTER_COMMAND = "/register";
    final String PING_COMMAND = "/ping";

    public Action(WebClient webClient) {
        this.webClient = webClient;
    }

    public String response(Message message) {
        String responseText;
        switch (message.getText()) {
            case START_COMMAND -> responseText = "Добро пожаловать в GPB Telegram bot!";
            case HELP_COMMAND -> responseText = "Бот предназначен для регистрации пользователей в мини-банке, открытия счета, а также переводов денежных средств между пользователями приложения";
            case REGISTER_COMMAND -> responseText = webClient.registrationResponse(message.getFrom().getId());
            case PING_COMMAND -> responseText = "pong";
            default -> responseText = "Команда не распознана. Попробуйте еще";
        }
        return responseText;
    }



}
