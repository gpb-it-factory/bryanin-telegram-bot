package ru.bryanin.dev.bryanintelegrambot.utils;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TransferUtil {

    public static List<String> convertCommandToWords(@NonNull String command) {
        return List.of(command.strip().split("\\s+"));
    }

    public static boolean isCommandCorrect(@NonNull String command) {
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

    public static String getUserNameFromCommand(@NonNull String command) {
        return convertCommandToWords(command).get(1);
    }

    public static String getAmountFromCommand(@NonNull String command) {
        return convertCommandToWords(command).get(2);
    }
}
