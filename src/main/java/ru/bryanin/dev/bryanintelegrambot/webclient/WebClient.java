package ru.bryanin.dev.bryanintelegrambot.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;
import ru.bryanin.dev.bryanintelegrambot.dto.TelegramUser;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class WebClient {

    @Value("${middle-service.baseURL}")
    private String middleServiceBaseURL;
    private final RestClient restClient;

    public WebClient() {
        restClient = RestClient.builder().baseUrl(middleServiceBaseURL).build();
    }

    public @ResponseBody String registrationResponse(long telegramId) {
        TelegramUser user = new TelegramUser(telegramId);
        String result = restClient
                .post()
                .uri(middleServiceBaseURL + "/users")
                .accept(APPLICATION_JSON)
                .body(user.getTelegramIdToString())
                .retrieve()
                .body(String.class);
        System.out.println(result);
        return result;
    }

}
