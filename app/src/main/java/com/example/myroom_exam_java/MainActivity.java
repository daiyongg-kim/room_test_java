package com.example.myroom_exam_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultTextView;
    AppDatabase db =  Room.databaseBuilder(this, AppDatabase.class, "todo-db")
            .allowMainThreadQueries()
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        showResult();

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
                showResult();
            }
        });

        findViewById(R.id.remove_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.todoDao().deleteAll();
                showResult();
            }
        });
    }

    void showResult() {
        mResultTextView.setText(db.todoDao().getAll().toString());

    }
}