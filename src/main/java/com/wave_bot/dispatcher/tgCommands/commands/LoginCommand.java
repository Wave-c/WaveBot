package com.wave_bot.dispatcher.tgCommands.commands;

import com.wave_bot.dispatcher.tgCommands.interfaces.IBotCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


public class LoginCommand extends BotCommand implements IBotCommand
{
    private InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
    public LoginCommand()
    {
        super();
        List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();
        var rowButtons = new ArrayList<InlineKeyboardButton>();
        var button1 = new InlineKeyboardButton();
        var button2 = new InlineKeyboardButton();

        button1.setText("Добавить язык");
        button1.setCallbackData("AddLang");
        rowButtons.add(button1);

        button2.setText("Удалить язык");
        button2.setCallbackData("DeleteLang");
        rowButtons.add(button2);

        rowsButtons.add(rowButtons);
        inlineKeyboard.setKeyboard(rowsButtons);
    }

    @Override
    public void sendAnswer(Update update, TelegramLongPollingBot bot)
    {
        System.out.println("ChatId: " + update.getMessage().getChatId());
        if(update.getMessage().getChatId() == 992020128)
        {
            SendMessage answer = new SendMessage();
            answer.setText("Добро пожаловать, господин)");
            answer.setReplyMarkup(inlineKeyboard);
            answer.setChatId(update.getMessage().getChatId().toString());
            try {
                bot.execute(answer);
                System.out.println("Bot: answer: SEND");
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            SendMessage answer = new SendMessage();
            answer.setText("обманщик)");
            answer.setChatId(update.getMessage().getChatId().toString());
            try {
                bot.execute(answer);
                System.out.println("Bot: answer: SEND");
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
