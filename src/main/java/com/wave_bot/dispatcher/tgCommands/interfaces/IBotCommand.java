package com.wave_bot.dispatcher.tgCommands.interfaces;

import java.io.IOException;
import java.net.URISyntaxException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface IBotCommand
{
    void sendAnswer(Update update, TelegramLongPollingBot bot) throws IOException, URISyntaxException;
}
