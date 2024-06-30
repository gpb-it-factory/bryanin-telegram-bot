package ru.bryanin.dev.bryanintelegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.dto.TelegramUser;
import ru.bryanin.dev.bryanintelegrambot.utils.TransferUtil;
import ru.bryanin.dev.bryanintelegrambot.webclient.WebClient;

import java.math.BigDecimal;

@Component
public class TransferCommandHandler implements KnownHandler{

    private final String COMMAND = "/transfer";
    private final String RESPONSE = "Произошла ошибка. Убедитесь, что команда введена правильно: /transfer [toTelegramUser] [amount], где toTelegramUser - это username пользователя, в адрес которого производится перевод, amount - сумма перевода";
    private final WebClient webClient;
    private final TransferUtil transferUtil;

    public TransferCommandHandler(WebClient webClient, TransferUtil transferUtil) {
        this.webClient = webClient;
        this.transferUtil = transferUtil;
    }

    @Override
    public String handle(Message message) {
        String command = message.getText();
        String fromUserName = message.getFrom().getUserName();
        if(transferUtil.isCommandCorrect(message.getText())) {
            String toUserName = transferUtil.getUserNameFromCommand(command);
            BigDecimal amount = new BigDecimal(transferUtil.getAmountFromCommand(command));
            return webClient.transfer(fromUserName, toUserName, amount);
        }
        return RESPONSE;

    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
