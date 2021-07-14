package edu.niu.cs.z1888485;
/***************************************************************
 *                                                             *
 * CSCI 522      Graduate Project - Math Quiz      Fall 2020   *
 *                                                             *
 * Class Name:  TestActivity                                   *
 *                                                             *
 * Programmer: Shardul Deepak Arjunwadkar Z1888485             *
 *                                                             *
 * Due Date:   12/04/2020 11:59PM                              *
 *                                                             *
 * Purpose: This activity is displays the quiz questions for   *
 *          selected operators. There will be 10 questions in  *
 *          each quiz. Quiz questions will be of selected one  *
 *          or more selected operators only. After selecting   *
 *          answer, Pop screen will be shown where it will tell*
 *          user if selected answer is correct or wrong. And   *
 *          ask user to Press Next to continue. After 10       *
 *          questions, it will move to next activity which is  *
 *          Result Activity. There will be 4 options for each  *
 *          question with only 1 correct answer.               *
 *                                                             *
 ***************************************************************/
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class TestActivity extends AppCompatActivity {
    private final int SUBTRACT =  1;
    private final int DIVIDE = 2;
    private final int ADD = 3;
    private final int MULTIPLY = 4;

    final int totalQuestion  = 10;
    int currentQuestion = 1;
    int totalQuestionsCorrectlyAnswered;
    float currentCorrectAnswer = 0;
    int correctAnswerPosition;
    int wrongAnswer1, wrongAnswer2, wrongAnswer3, wrongAnswer4;
    Boolean isSubtractSelected, isDivideSelected, isAddSelected, isMultiplySelected;

    Button ansButton1, ansButton2, ansButton3, ansButton4;
    TextView questionTextView;
    TextView questionNumberTextView;

    /*****************************************************************
     *                                                               *
     * Method Name:  onCreate                                        *
     *                                                               *
     *                                                               *
     * Purpose: The onCreate method is used to get the question      *
     *          TextView  and 4 answer Buttons with question number  *
     *          mentioned on  right top corner of screen. Here,      *
     *          OnClickListener is used which will show result popup *
     *          after selecting answer button.                       *
     *                                                               *
     *****************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ansButton1 = findViewById(R.id.button1);
        ansButton2 = findViewById(R.id.button2);
        ansButton3 = findViewById(R.id.button3);
        ansButton4 = findViewById(R.id.button4);
        questionTextView = findViewById(R.id.question_text_view);
        questionNumberTextView = findViewById(R.id.question_number);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isAddSelected = bundle.getBoolean("isAddSelected");
            isSubtractSelected = bundle.getBoolean("isSubtractSelected");
            isDivideSelected = bundle.getBoolean("isDivideSelected");
            isMultiplySelected = bundle.getBoolean("isMultiplySelected");
        }

        // showNextQuestion called here
        showNextQuestion();

        // OnClickListener is used here for first answer button selected.
        // It will check if this answer is correct or wrong and display pop-up screen accordingly.
        ansButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAnswerPosition == 1){
                    showNextDialog(true);
                }else{
                    showNextDialog(false);
                }
            }
        });

        // OnClickListener is used here for second answer button selected.
        // It will check if this answer is correct or wrong and display pop-up screen accordingly.
        ansButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAnswerPosition == 2){
                    showNextDialog(true);
                }else{
                    showNextDialog(false);
                }
            }
        });

        // OnClickListener is used here for third answer button selected.
        // It will check if this answer is correct or wrong and display pop-up screen accordingly.
        ansButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAnswerPosition == 3){
                    showNextDialog(true);
                }else{
                    showNextDialog(false);
                }
            }
        });

        // OnClickListener is used here for forth answer button selected.
        // It will check if this answer is correct or wrong and display pop-up screen accordingly.
        ansButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correctAnswerPosition == 4){
                    showNextDialog(true);
                }else{
                    showNextDialog(false);
                }
            }
        });
    }

    // round function is defined here for getting the float rounded value.
    private float round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }

    /*****************************************************************
     *                                                               *
     * Method Name:  showNextQuestion                                *
     *                                                               *
     *                                                               *
     * Purpose: This method is used to show next question after      *
     *          user selects next. It will create next question      *
     *          randomly with selected operators. Here, position of  *
     *          correct answer is also defined.                      *
     *                                                               *
     *****************************************************************/
    private void showNextQuestion() {
        int number1 = getRandomNumber(0,20); // 0 is minimum number, 20 is maximum
        int number2 = getRandomNumber(1,20); // avoiding dividing by 0
        int operator = getRandomValidOperator();
        String operatorSign="";

        Log.i("correct", number1+" " + number2);
        if(operator == SUBTRACT){
            currentCorrectAnswer = number1 - number2;
            Log.i("correct", "-");
            operatorSign = "-";
        }
        if(operator == DIVIDE){
            currentCorrectAnswer = ((float)number1)
                    / number2;
            currentCorrectAnswer = round(currentCorrectAnswer, 1);
            Log.i("correct", "/");
            operatorSign = "/";
        }
        if(operator == ADD){
            currentCorrectAnswer = number1 + number2;
            Log.i("correct", "+");
            operatorSign = "+";
        }
        if(operator == MULTIPLY){
            currentCorrectAnswer = number1 * number2;
            Log.i("correct", "*");
            operatorSign = "*";
        }

        Log.i("correct answer", currentCorrectAnswer +"");

        // making 4 wrong answers and making sure it is not equal to right answer
        while ((wrongAnswer1 = getRandomNumber(-50, 50)) == currentCorrectAnswer);
        while ((wrongAnswer2 = getRandomNumber(-50, 50)) == currentCorrectAnswer);
        while ((wrongAnswer3 = getRandomNumber(-50, 50)) == currentCorrectAnswer);
        while ((wrongAnswer4 = getRandomNumber(-50, 50)) == currentCorrectAnswer);

        ansButton1.setText(wrongAnswer1 + "");
        ansButton2.setText(wrongAnswer2 + "");
        ansButton3.setText(wrongAnswer3 + "");
        ansButton4.setText(wrongAnswer4 + "");

        // now selecting randomly right answer position
        correctAnswerPosition = getRandomNumber(1,4);
        if(correctAnswerPosition == 1){
            if(operator == DIVIDE) ansButton1.setText(currentCorrectAnswer +"");
            else  ansButton1.setText((int)currentCorrectAnswer +""); // just to remove .0 after number like 25.0
        }else if(correctAnswerPosition == 2){
            if(operator == DIVIDE) ansButton2.setText(currentCorrectAnswer +"");
            else  ansButton2.setText((int)currentCorrectAnswer +""); // just to remove .0 after number like 25.0
        }else if(correctAnswerPosition == 3){
            if(operator == DIVIDE) ansButton3.setText(currentCorrectAnswer +"");
            else  ansButton3.setText((int)currentCorrectAnswer +""); // just to remove .0 after number like 25.0
        }else if(correctAnswerPosition == 4){
            if(operator == DIVIDE) ansButton4.setText(currentCorrectAnswer +"");
            else  ansButton4.setText((int)currentCorrectAnswer +""); // just to remove .0 after number like 25.0
        }

        // update the question
        questionTextView.setText(number1 + " "+ operatorSign + " " + number2);
    }

    /*****************************************************************
     *                                                               *
     * Method Name:  getRandomNumber                                 *
     *                                                               *
     *                                                               *
     * Purpose: This method will create random numbers.              *
     *                                                               *
     *****************************************************************/
    private int getRandomNumber(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /*****************************************************************
     *                                                               *
     * Method Name:  getRandomValidOperator                          *
     *                                                               *
     *                                                               *
     * Purpose: This method will generate random operator. It will   *
     *          first check which operators are selected by user in  *
     *          mani menu and it will generate random operator for   *
     *          questions and it is used in the showNextQuestion     *
     *          method.                                              *
     *                                                               *
     *****************************************************************/
    // + - / *  (if it is selected by the user)
    private int getRandomValidOperator(){
        int validOperator = -1;

        // finding a valid operation selected by user
        Boolean validOperationFound = false;
        while(!validOperationFound){
            int operation = getRandomNumber(1, 4);

            if(operation == 1 && isSubtractSelected) {
                validOperationFound = true;
                validOperator = SUBTRACT;
            }else if(operation == 2 && isDivideSelected){
                validOperationFound = true;
                validOperator = DIVIDE;
            }else if(operation == 3 && isAddSelected){
                validOperationFound = true;
                validOperator = ADD;
            }else if(operation == 4 && isMultiplySelected){
                validOperationFound = true;
                validOperator = MULTIPLY;
            }
        }
        return validOperator;
    }

    /*****************************************************************
     *                                                               *
     * Method Name:  showNextDialog                                  *
     *                                                               *
     *                                                               *
     * Purpose: This method will display the pop-up screen after     *
     *          every answer selected. It will display if answer is  *
     *          correct or wrong with Next button. Also intent is    *
     *          created here to store the all corrected answers to   *
     *          show result at the end of the quiz.                  *
     *                                                               *
     *****************************************************************/
    void showNextDialog(Boolean userAnswerResult){
        String answerWas= "";
        if(userAnswerResult){
            answerWas= getString(R.string.correct_answer);
            totalQuestionsCorrectlyAnswered++;
        }else{
            answerWas= getString(R.string.wrong_answer);
        }

        new AlertDialog.Builder(this)
                .setTitle("Press next to continue")
                .setMessage(answerWas)
                .setPositiveButton(R.string.next_button_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        currentQuestion++;

                        if(currentQuestion == totalQuestion +1){ // all questions done
                            Intent i=new Intent(TestActivity.this, ResultActivity.class);
                            i.putExtra("totalQuestionsCorrectlyAnswered", totalQuestionsCorrectlyAnswered);
                            startActivity(i);
                            finish();
                        }else {
                            questionNumberTextView.setText(currentQuestion + "");
                            showNextQuestion();
                        }
                    }
                }).create().show();
    }

    /*****************************************************************
     *                                                               *
     * Method Name:  onBackPressed                                   *
     *                                                               *
     *                                                               *
     * Purpose: If back button is pressed it will display a pop-up   *
     *          where it will ask if user wants to exit quiz. If OK  *
     *          selected it will take it to Main Menu screen.        *
     *                                                               *
     *****************************************************************/
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit Test?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        TestActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}