package edu.niu.cs.z1888485;
/***************************************************************
 *                                                             *
 * CSCI 522      Graduate Project - Math Quiz      Fall 2020   *
 *                                                             *
 * Class Name:  SplashScreen                                   *
 *                                                             *
 * Programmer: Shardul Deepak Arjunwadkar Z1888485             *
 *                                                             *
 * Due Date:   12/04/2020 11:59PM                              *
 *                                                             *
 * Graduate Project Topic: For the Graduate project I have     *
 *          selected option 3 which is "Design an application  *
 *          that can be used as a math tutor for children"     *
 *                                                             *
 * Purpose: SplashScreen is a main class, it has onCreate      *
 *         method which calls when we start the app and it     *
 *         creates an intent that will start the next activity.*
 *                                                             *
 ***************************************************************/
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /*****************************************************************
     *                                                               *
     * Method Name:  onCreate                                        *
     *                                                               *
     *                                                               *
     * Purpose: The onCreate method is used to initialize activity   *
     *          here and intent is created to move to next activity  *
     *          SelectionActivity.                                   *
     *****************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this, SelectionActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}