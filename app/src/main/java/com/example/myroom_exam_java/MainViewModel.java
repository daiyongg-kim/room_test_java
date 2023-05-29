package com.example.myroom_exam_java;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    AppDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);
        db = Room.databaseBuilder(application, AppDatabase.class, "todo-db")
                .build();
    }

    public LiveData<List<Todo>> getAll() {
        return db.todoDao().getAll();
    }

    public void insert(Todo todo) {
        new InsertAsyncTask(db.todoDao())
                .execute(todo);
    }

    public void reset(Todo todo) {
        new DeleteAsyncTask(db.todoDao())
                .execute(new Todo());
    }

    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao mTodoDao;

        public InsertAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.insert(todos[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao mTodoDao;

        public DeleteAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.deleteAll();
            return null;
        }
    }
}
