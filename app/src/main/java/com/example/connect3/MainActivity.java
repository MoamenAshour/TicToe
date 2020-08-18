package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Active Player = 0 to green , 1 to red
    int activePlayer = 0;

    // to play again with click on the button ( play again )
    boolean gameIsActive = true;

    // 2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000f);
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        //if condition will stopped player to play again
        if ( gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            //if condition will set who will start first
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.green);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);


            // loop to know who player is win
            for(int[] winningPosition : winningPositions){
               if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                       gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                       gameState[winningPosition[0]] != 2) {

                   gameIsActive = false;
                   String winner = "Red";

                   if (gameState[winningPosition[0]] == 0) {
                       winner = "Green";
                   }

                   TextView winnerMessage = (TextView) findViewById(R.id.WinnerMessage);
                   winnerMessage.setText(winner + " Has Won :) :) :)");
                   //someone has won :)
                   LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                   layout.setVisibility(View.VISIBLE);
               }else {
                   boolean gameIsOver = true;
               for(int counterState : gameState) {
                   if (counterState == 2) gameIsOver = false;
                  }
               if(gameIsOver){
                   TextView winnerMessage = (TextView) findViewById(R.id.WinnerMessage);
                   winnerMessage.setText("it's draw :) :) :)");
                   //someone has won :)
                   LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                   layout.setVisibility(View.VISIBLE);
                  }
               }
            }
        }
    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i =0;i <gameState.length; i++){
            gameState[i] =2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}