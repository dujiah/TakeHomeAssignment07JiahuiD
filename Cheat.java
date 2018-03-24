package com.example.du.takehomeassignment07jiahuid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.PriorityQueue;

public class Cheat extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.du.takehomeassignment07jiahuid.answer_is_true";
    private static final String EXTRA_ANSWER_SHOW = "com.example.du.takehomeassignment07jiahuid.answer_show";
    private boolean myAnswerIsTrue;
    private TextView myAnswerTextView;
    private Button myShowAnswerButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, Cheat.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOW, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        myAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        myAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        myShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        myShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAnswerIsTrue) {
                    myAnswerTextView.setText(R.string.true_button);
                } else {
                    myAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOW, isAnswerShown);
        setResult(RESULT_OK, data);

    }
}
