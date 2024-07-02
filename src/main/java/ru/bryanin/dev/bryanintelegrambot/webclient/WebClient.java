package ru.bryanin.dev.bryanintelegrambot.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;
import ru.bryanin.dev.bryanintelegrambot.dto.TelegramUser;
import ru.bryanin.dev.bryanintelegrambot.dto.Transfer;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON;

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
        return restClient
                .post()
                .uri(middleServiceBaseURL + "/users")
                .acceptCharset(StandardCharsets.UTF_8)
                .accept(APPLICATION_JSON)
                .body(user.toString())
                .retrieve()
                .body(String.class);
    }

    public @ResponseBody String createAccount(TelegramUser user) {
        return restClient
                .post()
                .uri(middleServiceBaseURL + "/users/" + user.getUserTelegramId() + "/accounts")
                .acceptCharset(StandardCharsets.UTF_8)
                .body(accountName.getBytes(StandardCharsets.UTF_8))
                .retrieve()
                .body(String.class);
    }

    public @ResponseBody String requestBalance(TelegramUser user) {
        return restClient
                .get()
                .uri(middleServiceBaseURL + "/users/" + user.getUserTelegramId() + "/accounts")
                .acceptCharset(StandardCharsets.UTF_8)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }

    public @ResponseBody String transfer(String fromUserName, String toUserName, BigDecimal amount) {
        Transfer transfer = new Transfer(fromUserName, toUserName, amount);
        return restClient
                .post()
                .uri(middleServiceBaseURL + "/transfers")
                .acceptCharset(StandardCharsets.UTF_8)
                .accept(APPLICATION_JSON)
                .body(transfer)
                .retrieve()
                .body(String.class);
    }


}
