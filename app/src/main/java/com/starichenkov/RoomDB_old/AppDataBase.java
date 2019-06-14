package com.starichenkov.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Users.class, Events.class, BookMarks.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UsersDao usersDao();
    public abstract EventsDao eventsDao();
    public abstract BookMarksDao bookMarksDao();

}
