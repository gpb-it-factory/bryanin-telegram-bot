package ru.bryanin.dev.bryanintelegrambot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bryanin.dev.bryanintelegrambot.dto.TelegramUser;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    private final String requestErrorMessage = "При выполнении запроса произошла ошибка";

    @Around("ru.bryanin.dev.bryanintelegrambot.aop.LoggingPointcuts.allHandlers()")
    public Object aroundAllHandlers(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Message message = null;
        if (methodSignature.getName().equals("handle")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Message) {
                    message = (Message) arg;
                    log.info("Пользователь (username = " + message.getFrom().getUserName() + ", id = " + message.getFrom().getId() + ") отправил команду: " + message.getText());
                }
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
            result = requestErrorMessage;
        }
        log.info("Пользователь (username = " + message.getFrom().getUserName() + ", id = " + message.getFrom().getId() + ") получил ответ: " + result);
        return result;
    }

    @Around("ru.bryanin.dev.bryanintelegrambot.aop.LoggingPointcuts.AllWebClientMethods()")
    public Object aroundAllWebClientMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        TelegramUser telegramUser = null;
        String x = switch (methodSignature.getName()) {
            case "register" -> "Попытка зарегистрировать пользователя: ";
            case "createAccount" -> "Попытка открыть счет: ";
            case "requestBalance" -> "Попытка запросить баланс: ";
            case "transfer" -> "Попытка перевода денежных средств: ";
            default -> requestErrorMessage;
        };
        Object[] arguments = joinPoint.getArgs();
        for (Object arg : arguments) {
            if (arg instanceof TelegramUser) {
                telegramUser = (TelegramUser) arg;
                log.info(x + "Пользователь (username = " + telegramUser.getUserName() + ", id = " + telegramUser.getUserTelegramId());
            }
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
            result = requestErrorMessage;
        }
        return result;
    }


}
