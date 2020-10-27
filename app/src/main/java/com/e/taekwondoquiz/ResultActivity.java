package com.e.taekwondoquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.e.taekwondoquiz.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    private String score;
    private ActivityResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        Intent i = getIntent();
        score = i.getStringExtra("score");
        binding.tvScore.setText(score);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(i);
    }
}