package ru.bryanin.dev.bryanintelegrambot.bot;

import org.junit.jupiter.api.*;
import ru.bryanin.dev.bryanintelegrambot.AbstractTest;
import ru.bryanin.dev.bryanintelegrambot.handlers.*;
import ru.bryanin.dev.bryanintelegrambot.webclient.WebClient;

import java.util.Set;

class DispatcherTest extends AbstractTest {

    Set<KnownHandler> knownHandlers;
    UnknownCommandHandler unknownCommandHandler;
    Dispatcher dispatcher;
    String accountRegisterCommand = "/createaccount";
    String currentBalanceCommand = "/currentbalance";
    String helpCommand = "/help";
    String pingCommand = "/ping";
    String startCommand = "/start";
    String transferCommand = "/transfer username 1000";
    String userRegisterCommand = "/register";

    @BeforeEach
    void setUp() {
        knownHandlers = Set.of(
                new AccountRegisterCommandHandler(new WebClient()),
                new CurrentBalanceCommandHandler(new WebClient()),
                new HelpCommandHandler(),
                new PingCommandHandler(),
                new StartCommandHandler(),
                new TransferCommandHandler(new WebClient()),
                new UserRegisterCommandHandler(new WebClient())
        );
        unknownCommandHandler = new UnknownCommandHandler();
        dispatcher = new Dispatcher(knownHandlers, unknownCommandHandler);
    }

    @Test
    @DisplayName("При поступлении команды \"/createaccount\" диспетчер выбирает AccountRegisterCommandHandler")
    void getAccountRegisterCommandHandler() {
        Assertions.assertInstanceOf(AccountRegisterCommandHandler.class, dispatcher.getHandler(accountRegisterCommand));
    }

    @Test
    @DisplayName("При поступлении команды \"/currentbalance\" диспетчер выбирает CurrentBalanceCommandHandler")
    void getCurrentBalanceCommandHandler() {
        Assertions.assertInstanceOf(CurrentBalanceCommandHandler.class, dispatcher.getHandler(currentBalanceCommand));
    }

    @Test
    @DisplayName("При поступлении команды \"/help\" диспетчер выбирает HelpCommandHandler")
    void getHelpCommandHandler() {
        Assertions.assertInstanceOf(HelpCommandHandler.class, dispatcher.getHandler(helpCommand));
    }

    @Test
    @DisplayName("При поступлении команды \"/ping\" диспетчер выбирает PingCommandHandler")
    void getPingCommandHandler() {
        Assertions.assertInstanceOf(PingCommandHandler.class, dispatcher.getHandler(pingCommand));
    }

    @Test
    @DisplayName("При поступлении команды \"/start\" диспетчер выбирает StartCommandHandler")
    void getStartCommandHandler() {
        Assertions.assertInstanceOf(StartCommandHandler.class, dispatcher.getHandler(startCommand));
    }

    @Test
    @DisplayName("При поступлении команды \"/transfer username 1000\" диспетчер выбирает TransferCommandHandler")
    void getTransferCommandHandler() {
        Assertions.assertInstanceOf(TransferCommandHandler.class, dispatcher.getHandler(transferCommand));
    }

    @Test
    @DisplayName("При поступлении команды \"/register\" диспетчер выбирает UserRegisterCommandHandler")
    void getUserRegisterCommandHandler() {
        Assertions.assertInstanceOf(UserRegisterCommandHandler.class, dispatcher.getHandler(userRegisterCommand));
    }

}