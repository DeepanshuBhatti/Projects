package com.threeouncecheese.quizzoholic.Models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface QuizzoholicDataAccessObject {

    @Insert
    public void addQuesiton(QuestionsTable question);
}
