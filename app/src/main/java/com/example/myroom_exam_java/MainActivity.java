package com.example.myroom_exam_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //UI refresh
        viewModel.getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // DB insert when the add button clicked
        findViewById(R.id.add_button).setOnClickListener(v -> {
            //db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
            viewModel.insert(new Todo(mTodoEditText.getText().toString()));
        });

        // Delete all when the reset button clicked
        findViewById(R.id.remove_button).setOnClickListener(v ->{
//                db.todoDao().deleteAll();
            viewModel.reset(new Todo());
        });
    }
}