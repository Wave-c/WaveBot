package com.wave_bot.dispatcher.tgCommands.inlineComands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.wave_bot.dispatcher.tgCommands.interfaces.IInlineBotCommand;
import com.google.gson.Gson;
import com.sun.tools.javac.Main;

public class LangCommand extends BotCommand implements IInlineBotCommand
{
    public List<String> fromFileLangs() throws IOException, URISyntaxException
    {
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

            return languages;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void sendAnswer(Update update, TelegramLongPollingBot bot) throws IOException, URISyntaxException 
    {
        EditMessageText editMessage = BuildEditMessage(update);
        try
        {
            bot.execute(editMessage);
        }
        catch (TelegramApiException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EditMessageText BuildEditMessage(Update update) throws IOException, URISyntaxException
    {
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        List<String> languages = fromFileLangs();

        if(update.getCallbackQuery().getData().equals(languages.get(0).split(" ")[0]))
        {
            editMessage.setText("Курсовая/Дипломная работа по " + languages.get(0).split(" ")[0] + ".\nЦена: " + languages.get(0).split(" ")[1] + "р.(цена является приблизительной, точную цену уточняйте у исполнителя)\nОбращаться к имполнителю @den_noname");
        }
        else if(update.getCallbackQuery().getData().equals(languages.get(1).split(" ")[0]))
        {
            editMessage.setText("Курсовая/Дипломная работа по " + languages.get(1).split(" ")[0] + ".\nЦена: " + languages.get(1).split(" ")[1] + "р.(цена является приблизительной, точную цену уточняйте у исполнителя)\nОбращаться к имполнителю @den_noname");
        }
        else if(update.getCallbackQuery().getData().equals(languages.get(2).split(" ")[0]))
        {
            editMessage.setText("Курсовая/Дипломная работа по " + languages.get(2).split(" ")[0] + ".\nЦена: " + languages.get(2).split(" ")[1] + "р.(цена является приблизительной, точную цену уточняйте у исполнителя)\nОбращаться к имполнителю @den_noname");
        }
        else if(update.getCallbackQuery().getData().equals(languages.get(3).split(" ")[0]))
        {
            editMessage.setText("Курсовая/Дипломная работа по " + languages.get(3).split(" ")[0] + ".\nЦена: " + languages.get(3).split(" ")[1] + "р.(цена является приблизительной, точную цену уточняйте у исполнителя)\nОбращаться к имполнителю @den_noname");
        }
        else if(update.getCallbackQuery().getData().equals(languages.get(4).split(" ")[0]))
        {
            editMessage.setText("Курсовая/Дипломная работа по " + languages.get(4).split(" ")[0] + ".\nЦена: " + languages.get(4).split(" ")[1] + "р.(цена является приблизительной, точную цену уточняйте у исполнителя)\nОбращаться к имполнителю @den_noname");
        }

        return editMessage;
    }
}
