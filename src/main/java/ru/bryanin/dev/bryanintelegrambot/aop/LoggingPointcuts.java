package ru.bryanin.dev.bryanintelegrambot.aop;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcuts {

    @Pointcut("execution(* ru.bryanin.dev.bryanintelegrambot.webclient.WebClient.*(..))")
    public void AllWebClientMethods() {}


    @Pointcut("execution(* ru.bryanin.dev.bryanintelegrambot.handlers..handle(..))")
    public void allHandlers() {}

}
