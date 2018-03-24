package com.example.du.takehomeassignment07jiahuid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String Tag = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button myTrueButton;
    private Button myFalseButton;
    private Button myNextButton;
    private Button myPrevButton;
    private Button myCheatButton;
    private TextView myQuestionTextView;

    private Question1[] myQuestionBank = new Question1[]{
            new Question1(R.string.question_egyptians, false),
            new Question1(R.string.question_wonders, true),
            new Question1(R.string.question_india, true),
            new Question1(R.string.question_china, true),
            new Question1(R.string.question_olympics, true),
            new Question1(R.string.question_roman, false),
    };

    private int myCurrentIndex = 0;
    private boolean myIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Tag, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        myQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = myQuestionBank[myCurrentIndex].getTextResId();
        myQuestionTextView.setText(question);

        myTrueButton = (Button) findViewById(R.id.true_button);
        myTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });

        myFalseButton = (Button) findViewById(R.id.false_button);
        myFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        myNextButton = (Button) findViewById(R.id.next_button);
        myNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCurrentIndex = (myCurrentIndex + 1) % myQuestionBank.length;
                myIsCheater = false;
                updateQuestion1();
            }
        });
        myPrevButton = (Button) findViewById(R.id.previous_button);
        myPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCurrentIndex = (myCurrentIndex - 1) % myQuestionBank.length;
                myIsCheater = false;
                updateQuestion1();
            }
        });
        myCheatButton = (Button) findViewById(R.id.cheat_button);
        myCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = myQuestionBank[myCurrentIndex].isAnswerTrue();
                Intent intent = Cheat.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
        updateQuestion1();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (resultCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            myIsCheater = Cheat.wasAnswerShown(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Tag, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Tag, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Tag, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Tag, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "onDestroy() called");
    }

    private void updateQuestion1() {
        int question = myQuestionBank[myCurrentIndex].getTextResId();
        myQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerTrue = myQuestionBank[myCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (myIsCheater) {
            messageResId = R.string.judgment_toast;

        } else {
            if (userPressedTrue == answerTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

        }

    }
}
