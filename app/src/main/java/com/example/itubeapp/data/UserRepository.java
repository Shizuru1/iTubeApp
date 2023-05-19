package com.example.itubeapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    UserDAO userDAO;
    LiveData<List<User>> userList;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        userList = userDAO.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return userList;
    }

    public void insert(User user) {
        UserRoomDatabase.databaseWriterExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public void delete(User user) {
        UserRoomDatabase.databaseWriterExecutor.execute(() -> {
            userDAO.delete(user);
        });
    }
}
