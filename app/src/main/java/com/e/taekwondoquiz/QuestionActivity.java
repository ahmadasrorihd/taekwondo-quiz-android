package com.e.taekwondoquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.e.taekwondoquiz.databinding.ActivityQuestionBinding;
import com.e.taekwondoquiz.model.AnswerModel;
import com.e.taekwondoquiz.model.QuestionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private ActivityQuestionBinding binding;

    private ArrayList<QuestionModel> questionModelArrayList;
    private ArrayList<AnswerModel> answerModelArrayList = new ArrayList<>();
    private int position=0;
    private String choice = "null";
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        init();
    }

    private void init() {
        setData();
        showQuestion();
        handleAnswer();

        // button next click
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String correctAnswer = questionModelArrayList.get(position).getCorrect_answer();
                if (choice.equals("null")) {
                    Toast.makeText(QuestionActivity.this, "harap memilih jawaban", Toast.LENGTH_SHORT).show();
                } else {
                    if (position==questionModelArrayList.size()-1) {
                        if (choice.equals(correctAnswer)) {
                            score+=10;
                        }
                        binding.btnNext.setText(R.string.submit);
                        Intent i = new Intent(QuestionActivity.this, ResultActivity.class);
                        i.putExtra("score", String.valueOf(score));
                        startActivity(i);
                    } else {
                        if (choice.equals(correctAnswer)) {
                            score+=10;
                        }
                        position++;
                        binding.btnNext.setText(R.string.next);
                        showQuestion();
                    }
                }
            }
        });

        // button prev click
        binding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            @Override
            public void onClick(View v) {
                if (position==0) {
                    Toast.makeText(QuestionActivity.this, "halaman awal", Toast.LENGTH_SHORT).show();
                } else {
                    position--;
                    binding.btnNext.setText(R.string.next);
                    showQuestion();
                }
            }
        });

    }

    // set data from local json
    @SuppressLint("UseCompatLoadingForColorStateLists")
    private void setData() {
        try {
            questionModelArrayList = new ArrayList<>();
            JSONArray array = new JSONArray(loadJSONFromAsset());

            // save data from local json to model
            for (int i = 0; i < array.length(); i++) {
                JSONObject data = array.getJSONObject(i);
                String no = data.getString("no");
                String question = data.getString("question");
                String image = data.getString("image");
                String answer_a = data.getString("answer_a");
                String answer_b = data.getString("answer_b");
                String answer_c = data.getString("answer_c");
                String answer_d = data.getString("answer_d");
                String correct_answer = data.getString("correct_answer");
                QuestionModel questionModel = new QuestionModel();
                questionModel.setId(Integer.parseInt(no));
                questionModel.setQuestion(question);
                questionModel.setImage(image);
                questionModel.setAnswer_a(answer_a);
                questionModel.setAnswer_b(answer_b);
                questionModel.setAnswer_c(answer_c);
                questionModel.setAnswer_d(answer_d);
                questionModel.setCorrect_answer(correct_answer);
                questionModelArrayList.add(questionModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handleAnswer() {
        binding.layoutAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = questionModelArrayList.get(position).getAnswer_a();
                binding.layoutAnswerA.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_rounded_solid));
                binding.tvAnswerA.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorWhite));
                binding.layoutAnswerB.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerB.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerC.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerC.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerD.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerD.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));

            }
        });
        binding.layoutAnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = questionModelArrayList.get(position).getAnswer_b();
                binding.layoutAnswerA.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerA.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerB.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_rounded_solid));
                binding.tvAnswerB.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorWhite));
                binding.layoutAnswerC.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerC.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerD.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerD.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
            }
        });
        binding.layoutAnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = questionModelArrayList.get(position).getAnswer_c();
                binding.layoutAnswerA.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerA.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerB.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerB.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerC.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_rounded_solid));
                binding.tvAnswerC.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorWhite));
                binding.layoutAnswerD.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerD.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
            }
        });
        binding.layoutAnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = questionModelArrayList.get(position).getAnswer_d();
                binding.layoutAnswerA.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerA.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerB.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerB.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerC.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
                binding.tvAnswerC.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
                binding.layoutAnswerD.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_rounded_solid));
                binding.tvAnswerD.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorWhite));
            }
        });
    }

    private void showQuestion() {
        // show data to widget
        choice = "null";
        binding.layoutAnswerA.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
        binding.tvAnswerA.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
        binding.layoutAnswerB.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
        binding.tvAnswerB.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
        binding.layoutAnswerC.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
        binding.tvAnswerC.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
        binding.layoutAnswerD.setBackgroundDrawable(QuestionActivity.this.getResources().getDrawable(R.drawable.bg_paket));
        binding.tvAnswerD.setTextColor(QuestionActivity.this.getResources().getColor(R.color.colorBlack));
        binding.tvNo.setText("No. "+String.valueOf(questionModelArrayList.get(position).getId()));
        binding.tvQuestion.setText(questionModelArrayList.get(position).getQuestion());
        if (questionModelArrayList.get(position).getImage().equals("null")) {
            binding.ivQuestion.setVisibility(View.GONE);
        } else {
            binding.ivQuestion.setVisibility(View.VISIBLE);
            String uri = "@drawable/"+questionModelArrayList.get(position).getImage();  // where myresource (without the extension) is the file
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            binding.ivQuestion.setImageDrawable(res);
        }
        binding.tvAnswerA.setText(questionModelArrayList.get(position).getAnswer_a());
        binding.tvAnswerB.setText(questionModelArrayList.get(position).getAnswer_b());
        binding.tvAnswerC.setText(questionModelArrayList.get(position).getAnswer_c());
        binding.tvAnswerD.setText(questionModelArrayList.get(position).getAnswer_d());
    }

    private void handleResult() {
        Toast.makeText(QuestionActivity.this, "Nilai kamu "+score, Toast.LENGTH_SHORT).show();
    }

    // load data from assets
    public String loadJSONFromAsset() {
        String json = null;
        try {
            // read local file
            InputStream is = QuestionActivity.this.getAssets().open("question.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}