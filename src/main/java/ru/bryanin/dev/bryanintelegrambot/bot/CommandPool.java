package ru.bryanin.dev.bryanintelegrambot.bot;


public enum CommandPool {

    START("/start"),
    HELP("/help"),
    PING("/ping");

    private final String command;

    CommandPool(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandPool getCommandPoolFromCommand(String command) {
        for (CommandPool commandPool : CommandPool.values()) {
            if (command.equals(commandPool.getCommand())) {
                return commandPool;
            }
        }
        return null;
    }
}
