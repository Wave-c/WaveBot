package com.wave_bot.dispatcher.tgCommands.inlineComands;

import com.google.gson.Gson;
import com.sun.tools.javac.Main;
import com.wave_bot.dispatcher.Entitys.Language;
import com.wave_bot.dispatcher.tgCommands.interfaces.IBotCommand;
import com.wave_bot.dispatcher.tgCommands.interfaces.IInlineBotCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AddLangCommand extends BotCommand implements IBotCommand, IInlineBotCommand
{
    @Override
    public void sendAnswer(Update update, TelegramLongPollingBot bot)
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
    public EditMessageText BuildEditMessage(Update update)
    {
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessage.setText("Введите язык (<Lang> <Price>)");
        return editMessage;
    }

    public void AddToFile(Update update) throws URISyntaxException, IOException
    {
        URL resource = Main.class.getClassLoader().getResource("languages.json");
        File jsonFile = new File(resource.toURI());

        if(jsonFile.canRead())
        {
            BufferedReader br = new BufferedReader(new FileReader(jsonFile));

            StringBuilder languagesBuilder = new StringBuilder();
            String buffer;

            while ((buffer = br.readLine()) != null) {
                languagesBuilder.append(buffer);
            }

            String languagesJson = languagesBuilder.toString();

            Gson gson = new Gson();
            List<String> languages = gson.fromJson(languagesJson, List.class);

            languages.add(update.getMessage().getText());

            languagesJson = gson.toJson(languages);
            br.close();

            if(jsonFile.canWrite())
            {
                FileWriter fw = new FileWriter(jsonFile, false);
                fw.write(languagesJson);
                fw.flush();
            }
        }
    }
}
