package ru.bryanin.dev.bryanintelegrambot.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TransferUtil {

    public List<String> convertCommandToWords(String command) {
        return List.of(command.strip().split(" "));
    }

    public boolean isCommandCorrect(String command) {
        List<String> words = convertCommandToWords(command);

        if(words == null || words.size() != 3 || words.get(1).length() < 5) {
            return false;
        }

        if(!words.get(0).equals("/transfer")) {
            return false;
        }

        String userNameRegex = "^[-\\w.]+$";
        Pattern userNamePattern = Pattern.compile(userNameRegex);
        Matcher matcher = userNamePattern.matcher(words.get(1));
        if (!matcher.matches()) {
            return false;
        }

        Double amount;
        try {
            amount = Double.valueOf(words.get(2));
        } catch (Exception e) {
            return false;
        }
        if(amount <= 0) {
            return false;
        }

        return true;
    }

    public String getUserNameFromCommand(String command) {
        return convertCommandToWords(command).get(1);
    }

    public String getAmountFromCommand(String command) {
        return convertCommandToWords(command).get(2);
    }
}
