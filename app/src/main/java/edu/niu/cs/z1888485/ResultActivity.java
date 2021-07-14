package edu.niu.cs.z1888485;
/***************************************************************
 *                                                             *
 * CSCI 522      Graduate Project - Math Quiz      Fall 2020   *
 *                                                             *
 * Class Name:  ResultActivity                                 *
 *                                                             *
 * Programmer: Shardul Deepak Arjunwadkar Z1888485             *
 *                                                             *
 * Due Date:   12/04/2020 11:59PM                              *
 *                                                             *
 * Purpose: ResultActivity is used to display the final result *
 *          of the quiz. It will show user how many questions  *
 *          are answered correctly. There is a button displayed*
 *          which after clicking will take user to Main Menu.  *
 *                                                             *
 ***************************************************************/
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    int totalQuestionsCorrectlyAnswered;
    Button backToHomeButton;
    TextView noOfCorrectAnswer;

    /*****************************************************************
     *                                                               *
     * Method Name:  onCreate                                        *
     *                                                               *
     *                                                               *
     * Purpose: The onCreate method is used to initialize activity,  *
     *          and to show the quiz result. It also has button for  *
     *          moving to Main Menu screen.                          *
     *****************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Store correct answers in noOfCorrectAnswer
        noOfCorrectAnswer = findViewById(R.id.no_of_correct);
        backToHomeButton = findViewById(R.id.back_to_home_button);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            totalQuestionsCorrectlyAnswered = bundle.getInt("totalQuestionsCorrectlyAnswered");
        }

        // display Result
        noOfCorrectAnswer.setText("You answered " + totalQuestionsCorrectlyAnswered + " out of 10 questions");
        backToHomeButton = findViewById(R.id.back_to_home_button);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ResultActivity.this, SelectionActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}