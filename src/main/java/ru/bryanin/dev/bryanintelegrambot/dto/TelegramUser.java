package ru.bryanin.dev.bryanintelegrambot.dto;

public class TelegramUser {

    private Long telegramId;
    private Long backendId;

    public TelegramUser() {
    }

    public TelegramUser(Long telegramId) {
        this.telegramId = telegramId;
        this.backendId = null;
    }

    public TelegramUser(Long telegramId, Long backendId) {
        this.telegramId = telegramId;
        this.backendId = backendId;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public String getTelegramIdToString() {
        return "{" +
                "\"" + "telegramId" + "\"" + " : " + "\"" + telegramId + "\"" +
                '}';
    }

    public long getBackendId() {
        return backendId;
    }

    public String getBackendIdToString() {
        return "{" +
                "\"" + "backendId" + "\"" + " : " + "\"" + backendId + "\"" +
                '}';
    }

    @Override
    public String toString() {
        return "{" +
                "\"" + "telegramId" + "\"" + " : " + "\"" + telegramId + "\"" +
                "\"" + "backendId" + "\"" + " : " + "\"" + backendId + "\"" +
                '}';
    }
}
