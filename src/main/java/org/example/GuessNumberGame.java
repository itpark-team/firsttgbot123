package org.example;

import java.util.Random;

public class GuessNumberGame {
    private Random random;
    private int userNumber, compNumber;
    private boolean isPlaying;

    public GuessNumberGame() {
        random = new Random();
        isPlaying = false;
    }

    private void startGame() {
        compNumber = random.nextInt(1, 100 + 1);
        isPlaying = true;
    }

    private String getStartGamePhrase() {
        return "Я загадал число от 1 до 100. Попробуй отгадать. Введи свой вариант";
    }

    private String getGameHasNotStartedPhrase() {
        return "Игра ещё не началась. Для старта игры напиши /start";
    }

    private String getIncorrectInputPhrase() {
        return "Ошибка. Ты ввел не число";
    }


    private boolean isPlaying() {
        return isPlaying;
    }


    private void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    private String compareCompNumberAndUserNumber() {
        if (userNumber < compNumber) {
            return "Введи побольше";
        } else if (userNumber > compNumber) {
            return "Введи поменьше";
        } else {
            isPlaying = false;
            return "Ураааа ты угадал!! Для начала новой игры введи /start";
        }
    }

    public String processGameLogic(String textFromUser) {
        String textToUser = "";

        if (textFromUser.equals("/start")) {
            startGame();
            textToUser = getStartGamePhrase();
        } else {
            if (isPlaying()) {
                if (NumberUtil.isNumber(textFromUser)) {
                    setUserNumber(Integer.parseInt(textFromUser));

                    textToUser = compareCompNumberAndUserNumber();
                } else {
                    textToUser = getIncorrectInputPhrase();
                }
            } else {
                textToUser = getGameHasNotStartedPhrase();
            }
        }

        return textToUser;
    }
}
