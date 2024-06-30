package ru.bryanin.dev.bryanintelegrambot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Getter
public class TelegramUser {

    @JsonProperty("userTelegramId")
    private Long userTelegramId;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("accountName")
    private String accountName;

    public TelegramUser(Long userTelegramId, String userName) {
        this.userTelegramId = userTelegramId;
        this.userName = userName;
        this.accountName = "Акционный";
    }

    @Override
    public String toString() {
        return "{" +
                "\"" + "userTelegramId" + "\"" + " : " + "\"" + userTelegramId + "\"" + ", \n" +
                "\"" + "userName" + "\"" + " : " + "\"" + userName + "\"" + ", \n" +
                "\"" + "accountName" + "\"" + " : " + "\"" + accountName + "\"" +
                '}';
    }
}
