package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBotHandler extends TelegramLongPollingBot {
    private String botUsername = "firsttgbot123_bot";
    private String botToken = "6836114530:AAEN2o7MblY6Yz1qS9a9Ux98yVoiO-BN-Q0";

    private GuessNumberGame guessNumberGame = new GuessNumberGame();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() == true) {
            Message messageFromUser = update.getMessage();

            if (messageFromUser.hasText() == true) {
                String textFromUser = messageFromUser.getText();
                long chatId = messageFromUser.getChatId();

                String textToUser = guessNumberGame.processGameLogic(textFromUser);

                SendMessage messageToUser = new SendMessage();
                messageToUser.setChatId(chatId);
                messageToUser.setText(textToUser);

                try {
                    execute(messageToUser);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
