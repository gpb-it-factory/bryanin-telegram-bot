package ru.bryanin.dev.bryanintelegrambot.bot;
import org.springframework.stereotype.Component;
import ru.bryanin.dev.bryanintelegrambot.handlers.Handler;
import ru.bryanin.dev.bryanintelegrambot.handlers.KnownHandler;
import ru.bryanin.dev.bryanintelegrambot.handlers.UnknownCommandHandler;

import java.util.Optional;
import java.util.Set;

@Component
public class Dispatcher {

    private final Set<KnownHandler> knownHandlers;
    private final UnknownCommandHandler unknownCommandHandler;

    public Dispatcher(Set<KnownHandler> knownHandlers, UnknownCommandHandler unknownCommandHandler) {
        this.knownHandlers = knownHandlers;
        this.unknownCommandHandler = unknownCommandHandler;
    }

    public Handler getHandler(String command) {
        Optional<KnownHandler> optionalHandler = knownHandlers.stream()
                .filter(handler -> command.startsWith(handler.getCommand()))
                .findFirst();
        if(optionalHandler.isPresent()) {
            return optionalHandler.get();
        }
        return unknownCommandHandler;
    }
}
