package com.wave_bot.dispatcher.tgCommands.commands;

import com.google.gson.Gson;
import com.sun.tools.javac.Main;
import com.wave_bot.dispatcher.model.Language;
import com.wave_bot.dispatcher.tgCommands.interfaces.IBotCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StartCommand extends BotCommand implements IBotCommand
{
    private InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();

    public StartCommand() throws IOException, URISyntaxException
    {
        super();
        URL resource = Main.class.getClassLoader().getResource("languages.json");
        File jsonFile = new File(resource.toURI());
        if(jsonFile.canRead()) 
        {
            BufferedReader br = new BufferedReader(new FileReader(jsonFile));

            StringBuilder languagesBuilder = new StringBuilder();
            String temp;

            while ((temp = br.readLine()) != null) 
            {
                languagesBuilder.append(temp);
            }

            String languagesJson = languagesBuilder.toString();

            Gson gson = new Gson();
            List<String> languages = gson.fromJson(languagesJson, List.class);

            List<List<InlineKeyboardButton>> rowsButtons = new ArrayList<>();
            List<InlineKeyboardButton> row = new ArrayList<>();

            for (int i = 0; i < languages.size(); i++) 
            {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setCallbackData(languages.get(i).split(" ")[0]);
                button.setText(languages.get(i).split(" ")[0]);
                row.add(button);
            }
            rowsButtons.add(row);

            inlineKeyboard.setKeyboard(rowsButtons);
            br.close();
        }
    }

    public void sendAnswer(Update update, TelegramLongPollingBot bot)
    {
        SendMessage answer = new SendMessage();
        answer.setText("Курсовые/Дипломные/Домашние работы по программированию - не проблема. Выбери язык");
        answer.setChatId(update.getMessage().getChatId().toString());
        answer.setReplyMarkup(inlineKeyboard);
        try {
            bot.execute(answer);
            System.out.println("Bot: answer: SEND");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
