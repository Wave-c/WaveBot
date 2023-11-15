package com.wave_bot.dispatcher.Entitys;

public class Language 
{
    public String lang;
    public int price;

    public Language(String str)
    {
        this.lang = str.split(" ")[0];
        this.price = Integer.parseInt(str.split(" ")[1]);
    }
}
