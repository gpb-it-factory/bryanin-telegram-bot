package ru.bryanin.dev.bryanintelegrambot.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bryanin.dev.bryanintelegrambot.AbstractTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.bryanin.dev.bryanintelegrambot.utils.TransferUtil.*;

class TransferUtilTest extends AbstractTest {

    String stringOfTwoWords = "Lorem ipsum";
    String stringOfFourWords = "Lorem ipsum dolor sit";
    String stringOfFiveWords = "Lorem ipsum dolor sit amet";
    String stringOfFiveWordsWithAdditionalSpaces = "  Lorem       ipsum dolor           sit amet          ";
    String stringWithoutTransferWord = "not transfer 300";
    String stringWithShortSecondWord = "/transfer 1234 300";
    String stringWithSpecialCharsAtSecondWord = "/transfer tert#'*95@`~5 300";
    String stringWithoutDoubleTypeAtThirdWord = "/transfer username01 yyy";
    String stringWithNegativeNumberAtThirdWord = "/transfer username02 -500";
    String stringWithTransferWord = "/transfer user12345 300";
    List<String> listOfFiveWords = List.of("Lorem", "ipsum", "dolor", "sit", "amet");



    @BeforeAll
    static void setUp() {

    }

    @Test
    @DisplayName("На вход метода подается строка из нескольких слов, разделенных пробелом. На выходе получается массив слов")
    void convertCommandToWordsReturnsCorrectValueFromString() {
        Assertions.assertIterableEquals(listOfFiveWords, convertCommandToWords(stringOfFiveWords));
    }

    @Test
    @DisplayName("На вход метода подается строка из нескольких слов, разделенных пробелами (по несколько штук подряд, с пробелом перед и после. На выходе получается массив слов")
    void convertCommandToWordsReturnsCorrectValueFromStringWithAdditionalSpaces() {
        Assertions.assertIterableEquals(listOfFiveWords, convertCommandToWords(stringOfFiveWordsWithAdditionalSpaces));
    }

    @Test
    @DisplayName("Ошибка, если на вход метода isCommandCorrect() подается строка, состоящая из 2 слов")
    void isCommandCorrectMethodReturnsFalseIfStringContainsOfTwoWords() {
        Assertions.assertFalse(isCommandCorrect(stringOfTwoWords));
    }

    @Test
    @DisplayName("Ошибка, если на вход метода isCommandCorrect() подается строка, состоящая из 4 слов")
    void isCommandCorrectMethodReturnsFalseIfStringContainsOfFourWords() {
        Assertions.assertFalse(isCommandCorrect(stringOfFourWords));
    }

    @Test
    @DisplayName("Ошибка, если на вход метода isCommandCorrect() подается строка из 3 слов, первое слово которой не \"/transfer\"")
    void isCommandCorrectMethodReturnsFalseIfFirstWordIsNotTransfer() {
        Assertions.assertFalse(isCommandCorrect(stringWithoutTransferWord));
    }

    @Test
    @DisplayName("Ошибка, если на вход метода isCommandCorrect() подается строка из 3 слов, второе слово которой меньше 5 символов, что нарушает требования к username от телеграм")
    void isCommandCorrectMethodReturnsFalseIfSecondWordIsShorterThanFiveChars() {
        Assertions.assertFalse(isCommandCorrect(stringWithShortSecondWord));
    }

    @Test
    @DisplayName("Ошибка, если на вход метода isCommandCorrect() подается строка из 3 слов, второе слово которой содержит специальные символы, нарушающие требования к username от телеграм")
    void isCommandCorrectMethodReturnsFalseIfSecondWordContainsSpecialChars() {
        Assertions.assertFalse(isCommandCorrect(stringWithSpecialCharsAtSecondWord));
    }

    @Test
    @DisplayName("Ошибка, если на вход метода isCommandCorrect() подается строка из 3 слов, третье слово которой не может быть приведено к типу Double")
    void isCommandCorrectMethodReturnsFalseIfThirdWordIsNotDoubleType() {
        Assertions.assertFalse(isCommandCorrect(stringWithoutDoubleTypeAtThirdWord));
    }

    @Test
    @DisplayName("Ошибка, если на вход метода isCommandCorrect() подается строка из 3 слов, третье слово которой является числом меньше нуля")
    void isCommandCorrectMethodReturnsFalseIfThirdWordIsNegativeNumber() {
        Assertions.assertFalse(isCommandCorrect(stringWithNegativeNumberAtThirdWord));
    }

    @Test
    @DisplayName("Случай, когда все требования к строке соблюдены")
    void isCommandCorrectMethodReturnsTrueIfAllClear() {
        Assertions.assertTrue(isCommandCorrect(stringWithTransferWord));
    }


    @Test
    @DisplayName("Метод getUserNameFromCommand(String command) возвращает username (второе слово в строке, состоящей из трех слов)")
    void getUserNameFromCorrectCommand() {
        Assertions.assertEquals("user12345", getUserNameFromCommand(stringWithTransferWord));
    }

    @Test
    @DisplayName("Метод getUserNameFromCommand(String command) возвращает сумму перевода (третье слово в строке, состоящей из трех слов)")
    void getAmountFromCorrectCommand() {
        Assertions.assertEquals("300", getAmountFromCommand(stringWithTransferWord));
    }
}