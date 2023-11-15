package com.wave_bot.dispatcher.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "languages")
public class Language 
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String lang;
    public int price;

    public Language(String str)
    {
        this.lang = str.split(" ")[0];
        this.price = Integer.parseInt(str.split(" ")[1]);
    }
}
