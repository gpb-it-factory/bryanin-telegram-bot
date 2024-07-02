package ru.bryanin.dev.bryanintelegrambot.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;
import ru.bryanin.dev.bryanintelegrambot.dto.TelegramUser;
import ru.bryanin.dev.bryanintelegrambot.dto.Transfer;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
public class WebClient {

    @Value("${middle-service.baseURL}")
    private String middleServiceBaseURL;
    private final RestClient restClient;
    private final String requestErrorMessage = "При выполнении запроса произошла ошибка";
    private final String accountName = "{\n" + "\"accountName\": \"Акционный\"" + "\n}";

    public WebClient() {
        restClient = RestClient.builder()
                .baseUrl(middleServiceBaseURL)
                .build();
    }

    public @ResponseBody String register(TelegramUser user) {
        try {
            return restClient
                    .post()
                    .uri(middleServiceBaseURL + "/users")
                    .acceptCharset(StandardCharsets.UTF_8)
                    .accept(APPLICATION_JSON)
                    .body(user.toString())
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            return requestErrorMessage;
        }
    }

    public @ResponseBody String createAccount(TelegramUser user) {
        try {
            return restClient
                    .post()
                    .uri(middleServiceBaseURL + "/users/" + user.getUserTelegramId() + "/accounts")
                    .acceptCharset(StandardCharsets.UTF_8)
                    .body(accountName.getBytes(StandardCharsets.UTF_8))
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            return requestErrorMessage;
        }
    }

    public @ResponseBody String requestBalance(TelegramUser user) {
        try {
            return restClient
                    .get()
                    .uri(middleServiceBaseURL + "/users/" + user.getUserTelegramId() + "/accounts")
                    .acceptCharset(StandardCharsets.UTF_8)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            return requestErrorMessage;
        }
    }

    public @ResponseBody String transfer(String fromUserName, String toUserName, BigDecimal amount) {
        Transfer transfer = new Transfer(fromUserName, toUserName, amount);
        try {
            return restClient
                    .post()
                    .uri(middleServiceBaseURL + "/transfers")
                    .acceptCharset(StandardCharsets.UTF_8)
                    .accept(APPLICATION_JSON)
                    .body(transfer)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            return requestErrorMessage;
        }
    }


}
