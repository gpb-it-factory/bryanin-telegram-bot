package ru.bryanin.dev.bryanintelegrambot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Transfer {

    @JsonProperty("fromUserName")
    private String fromUserName;
    @JsonProperty("toUserName")
    private String toUserName;
    @JsonProperty("amount")
    private BigDecimal amount;


}
