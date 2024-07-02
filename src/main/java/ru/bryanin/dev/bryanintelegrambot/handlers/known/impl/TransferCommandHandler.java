package ru.bryanin.dev.bryanintelegrambot.handlers.known.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.handlers.known.KnownHandler;
import ru.bryanin.dev.bryanintelegrambot.webclient.WebClient;

import java.math.BigDecimal;

import static ru.bryanin.dev.bryanintelegrambot.utils.TransferUtil.*;

@Component
public class TransferCommandHandler implements KnownHandler {

    private final String COMMAND = "/transfer";
    private final String RESPONSE = "Произошла ошибка. Убедитесь, что команда введена правильно: /transfer [toTelegramUser] [amount], где toTelegramUser - это username пользователя, в адрес которого производится перевод, amount - сумма перевода";
    private final WebClient webClient;

    public TransferCommandHandler(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String handle(Message message) {
        String command = message.getText();
        String fromUserName = message.getFrom().getUserName();
        if(isCommandCorrect(message.getText())) {
            String toUserName = getUserNameFromCommand(command);
            BigDecimal amount = new BigDecimal(getAmountFromCommand(command));
            return webClient.transfer(fromUserName, toUserName, amount);
        }
        return RESPONSE;

    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
