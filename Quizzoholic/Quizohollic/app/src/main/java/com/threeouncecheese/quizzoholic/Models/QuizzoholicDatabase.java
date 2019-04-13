package com.threeouncecheese.quizzoholic.Models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {QuestionsTable.class}, version = 1)
public abstract class QuizzoholicDatabase extends RoomDatabase {

    public abstract QuizzoholicDataAccessObject quizzoholicDao();
}
