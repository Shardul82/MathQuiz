package edu.niu.cs.z1888485;
/***************************************************************
 *                                                             *
 * CSCI 522      Graduate Project - Math Quiz      Fall 2020   *
 *                                                             *
 * Class Name:  SelectionActivity                              *
 *                                                             *
 * Programmer: Shardul Deepak Arjunwadkar Z1888485             *
 *                                                             *
 * Due Date:   12/04/2020 11:59PM                              *
 *                                                             *
 * Purpose: This activity is displays the main menu where      *
 *          user has to select single or multiple operations   *
 *          like Addition, Subtraction, multiplication and     *
 *          division. After selecting it will move to the next *
 *          activity that is Quiz where questions will be      *
 *          displayed.                                         *
 *                                                             *
 ***************************************************************/
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SelectionActivity extends AppCompatActivity {
    ImageButton addButton;
    ImageButton subtractButton;
    ImageButton multiplyButton;
    ImageButton divideButton;

    Button startButton;

    Boolean isAddSelected, isSubtractSelected, isDivideSelected, isMultiplySelected;

    /*****************************************************************
     *                                                               *
     * Method Name:  onCreate                                        *
     *                                                               *
     *                                                               *
     * Purpose: The onCreate method is used to get the menu options  *
     *          and it will show which are selected or none selected.*
     *          OnClickListener is used here when any operator is    *
     *          selected. Also start Button to start the quiz.       *
     *                                                               *
     *****************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_layout);

        // get buttons
        subtractButton = findViewById(R.id.subtract_image);
        divideButton = findViewById(R.id.divide_image);
        addButton = findViewById(R.id.add_image);
        multiplyButton = findViewById(R.id.multiply_image);

        // nothing is selected
        isAddSelected = isSubtractSelected = isDivideSelected = isMultiplySelected = false;

        // OnClickListener is used when subtract is selected.
        // It will change the background color after selection oe cancellation.
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSubtractSelected = toggleSelection(isSubtractSelected);
                if(isSubtractSelected){
                    subtractButton.setBackground(getResources().getDrawable(R.drawable.selected_background));
                }else{
                    subtractButton.setBackground(getResources().getDrawable(R.drawable.unselected_background));
                }
            }
        });

        // OnClickListener is used when divide is selected.
        // It will change the background color after selection oe cancellation.
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDivideSelected = toggleSelection(isDivideSelected);
                if(isDivideSelected){
                    divideButton.setBackground(getResources().getDrawable(R.drawable.selected_background));
                }else{
                    divideButton.setBackground(getResources().getDrawable(R.drawable.unselected_background));
                }
            }
        });

        // OnClickListener is used when add is selected.
        // It will change the background color after selection oe cancellation.
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAddSelected = toggleSelection(isAddSelected);
                if(isAddSelected){
                    addButton.setBackground(getResources().getDrawable(R.drawable.selected_background));
                }else{
                    addButton.setBackground(getResources().getDrawable(R.drawable.unselected_background));
                }
            }
        });

        // OnClickListener is used when multiply is selected.
        // It will change the background color after selection oe cancellation.
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMultiplySelected = toggleSelection(isMultiplySelected);
                if(isMultiplySelected){
                    multiplyButton.setBackground(getResources().getDrawable(R.drawable.selected_background));
                }else{
                    multiplyButton.setBackground(getResources().getDrawable(R.drawable.unselected_background));
                }
            }
        });

        // OnclickListener is used for start Button which will start the Test activity.
        // Intent is created here for selection
        startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if nothing selected it will show toast message to select operator.
                if(!isSubtractSelected && !isDivideSelected && !isAddSelected && !isMultiplySelected ){
                    Toast.makeText(SelectionActivity.this, getString(R.string.error_toast_text), Toast.LENGTH_SHORT).show();
                } else{
                    Intent i=new Intent(SelectionActivity.this, TestActivity.class);
                    i.putExtra("isSubtractSelected", isSubtractSelected);
                    i.putExtra("isDivideSelected", isDivideSelected);
                    i.putExtra("isAddSelected", isAddSelected);
                    i.putExtra("isMultiplySelected", isMultiplySelected);
                    startActivity(i);
                }
            }
        });
    }

    Boolean toggleSelection(Boolean value){
        if(value == true) {
            return false;
        }else {
            return true;
        }
    }
}