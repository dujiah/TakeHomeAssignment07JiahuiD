package com.example.du.takehomeassignment07jiahuid;

/**
 * Created by du on 3/18/18.
 */

public class Question1 {
    private int myTextResId;
    private boolean myAnswerTrue;

    public int getTextResId() {
        return myTextResId;
    }

    public void setTextResId(int textResId) {
        this.myTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return myAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.myAnswerTrue = answerTrue;
    }

    public Question1(int textResId, boolean answerTrue) {
        myTextResId = textResId;
        myAnswerTrue = answerTrue;
    }
}
