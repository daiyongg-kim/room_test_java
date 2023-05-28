package com.example.myroom_exam_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        AppDatabase db =  Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .build();

        //UI refresh
        db.todoDao().getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // DB insert when the add button clicked
        findViewById(R.id.add_button).setOnClickListener(v -> {
            //db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
            new InsertAsyncTask(db.todoDao())
                    .execute(new Todo(mTodoEditText.getText().toString()));
        });

        // Delete all when the reset button clicked
        findViewById(R.id.remove_button).setOnClickListener(v ->{
//                db.todoDao().deleteAll();
            new DeleteAsyncTask(db.todoDao())
                    .execute(new Todo());
        });
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