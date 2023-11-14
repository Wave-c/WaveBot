package com.wave_bot.dispatcher.tgCommands.interfaces;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.net.URISyntaxException;

public interface IInlineBotCommand extends IBotCommand
{
    EditMessageText BuildEditMessage(Update update) throws URISyntaxException;
}
