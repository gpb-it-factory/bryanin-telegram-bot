package ru.bryanin.dev.bryanintelegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class HelpCommandHandler implements KnownHandler {

    private final String COMMAND = "/help";
    private final String RESPONSE = "Бот предназначен для регистрации пользователей в мини-банке, открытия счета, а также переводов денежных средств между пользователями приложения. \n" +
            "Доступные команды: \n" +
            "/register - Регистрация в Минибанке (с этого шага стоит начать, если Вы не были зарегистрированы ранее); \n" +
            "/createaccount - Открытие счета в Минибанке (возможно только один раз после регистрации пользователя). При открытии счета Минибанк выдает приветственный бонус в размере 5000 рублей!\n" +
            "/currentbalance - Проверить баланс счета в Минибанке\n" +
            "/transfer [toTelegramUser] [amount] - Перевод стороннему пользователю, где toTelegramUser - это username пользователя, в адрес которого производится перевод, amount - сумма перевода";

    @Override
    public String handle(Message message) {
        return RESPONSE;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }
}
