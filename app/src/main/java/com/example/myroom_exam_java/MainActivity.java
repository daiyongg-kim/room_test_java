package com.example.myroom_exam_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myroom_exam_java.databinding.ActivityMainBinding;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.setViewModel(viewModel);
        //UI refresh
//        viewModel.getAll().observe(this, todos -> {
//            binding.resultText.setText(todos.toString());
//        });

        // DB insert when the add button clicked
//        findViewById(R.id.add_button).setOnClickListener(v -> {
//            viewModel.insert(new Todo(binding.todoEdit.getText().toString()));
//        });

        // Delete all when the reset button clicked
//        findViewById(R.id.remove_button).setOnClickListener(v ->{
//            viewModel.reset();
//        });
    }
}