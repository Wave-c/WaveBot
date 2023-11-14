package com.wave_bot.dispatcher.controller;

import com.wave_bot.dispatcher.tgCommands.commands.LoginCommand;
import com.wave_bot.dispatcher.tgCommands.commands.StartCommand;
import com.wave_bot.dispatcher.tgCommands.inlineComands.AddLangCommand;
import com.wave_bot.dispatcher.tgCommands.inlineComands.DeleteLangCommand;
import com.wave_bot.dispatcher.tgCommands.interfaces.IBotCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class TelegramBot extends TelegramLongPollingBot
{
    @Value("${com.wave_bot.dispatcher.token}")
    private String token;
    @Value("${com.wave_bot.dispatcher.username}")
    private String username;
    private final IBotCommand START_COMMAND = new StartCommand();
    private final IBotCommand LOGIN_COMMAND = new LoginCommand();
    private final IBotCommand ADD_LANG_COMMAND = new AddLangCommand();
    private final IBotCommand DELETE_LANG_COMMAND = new DeleteLangCommand();

    public TelegramBot() throws IOException, URISyntaxException {
        super();
        ((StartCommand)START_COMMAND).setCommand("/start");
        ((LoginCommand)LOGIN_COMMAND).setCommand("/login");
        ((AddLangCommand)ADD_LANG_COMMAND).setCommand("AddLang");
        ((DeleteLangCommand)DELETE_LANG_COMMAND).setCommand("DeleteLang");
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        System.out.println("Bot: MESSAGE");
        if(update.getCallbackQuery() != null)
        {
            var message = update.getCallbackQuery().getData();
            System.out.println(message);
            switch(message)
            {
                case "AddLang":
                    ADD_LANG_COMMAND.sendAnswer(update, this);
                    break;
                case "DeleteLang":
                    DELETE_LANG_COMMAND.sendAnswer(update, this);
                    break;
            }
        }
        else if(update.getMessage() != null)
        {
            var message = update.getMessage();
            System.out.println(message.getText());
            if (message.isCommand())
            {
                switch (message.getText())
                {
                    case "/start":
                        START_COMMAND.sendAnswer(update, this);
                        break;
                    case "/login":
                        LOGIN_COMMAND.sendAnswer(update, this);
                        break;
                }
            }
            else
            {
                if(update.getMessage().getReplyToMessage() != null)
                {
                    try {
                        ((AddLangCommand)ADD_LANG_COMMAND).AddToFile(update);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public String getBotToken()
    {
        System.out.println("Bot: getBotToken: OK"); return token;
    }

    @Override
    public String getBotUsername()
    {
        System.out.println("Bot: getBotUsername: OK"); return username;
    }
}
