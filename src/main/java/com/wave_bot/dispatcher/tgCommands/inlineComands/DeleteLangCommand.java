package com.wave_bot.dispatcher.tgCommands.inlineComands;

import com.wave_bot.dispatcher.tgCommands.interfaces.IBotCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public class DeleteLangCommand extends BotCommand implements IBotCommand
{
    @Override
    public void sendAnswer(Update update, TelegramLongPollingBot bot) {

    }
}
