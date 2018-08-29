package com.saadshahzad97.connectgame;

import android.support.v7.widget.GridLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * @ author:  Saad Shahzad
     * @ Project: Tic Tac Toe (Connect)
     * @ This project is based on tic tac toe but instead of the normal x's and o's
     * i have used emoji's
     */

    // 0 = smile face(Pink color) and 1 = crazy face (Blue color)
    int Player = 0;

    //To allow if the game is active or not i.e. when the player has won, it will disable the user to tap on the icons to keep on changing
    boolean activeGame = true;

    //The number 2 means it is un-played meaning there is nothing is in the slot
    int [] state_of_the_Game = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [] [] position_For_Winning = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    //Name of the method
    public void ImageDrop(View view) {

        ImageView img = (ImageView) view;

        int tagOfImage = Integer.parseInt(img.getTag().toString());

        //if the tapped icon i.e the tag of the image is equal to the state of the game
        if (state_of_the_Game[tagOfImage] == 2 && activeGame) {

            //To check which player is playing
            state_of_the_Game[tagOfImage] = Player;

            img.setTranslationY(-1000f);

            //For first player turn
            if (Player == 0) {
                img.setImageResource(R.drawable.smile);

                Player = 1;
            }
            //For second Player turn
            else {
                img.setImageResource(R.drawable.crazy);

                Player = 0;
            }

            img.animate().translationYBy(1000f).setDuration(300);

            //For winning position i.e. when the same icons are in a 3 diagonal form then the player has won
            for(int [] winningPosition : position_For_Winning){

                if (state_of_the_Game[winningPosition[0]] == state_of_the_Game[winningPosition[1]] &&
                        state_of_the_Game[winningPosition[1]] == state_of_the_Game[winningPosition[2]] &&
                        state_of_the_Game[winningPosition[0]] != 2) {

                    activeGame = false;

                    String winner = "Crazy face";

                    if (state_of_the_Game[winningPosition[0]] == 0) {

                        winner = "Smiley face";
                    }

                    TextView winMessage_TextView = (TextView) findViewById(R.id.winMessage_TextView);

                    winMessage_TextView.setText(winner + " has won!");

                    LinearLayout playAgain_Layout = (LinearLayout)findViewById(R.id.playAgain_Layout);

                    playAgain_Layout.setVisibility(View.VISIBLE);

                    break;

                }

                //if it is a draw
                else {

                    boolean gameIsOver = true;

                    for(int gameState : state_of_the_Game){

                        if(gameState == 2) gameIsOver = false;
                    }

                    if(gameIsOver){

                        TextView winMessage_TextView = (TextView) findViewById(R.id.winMessage_TextView);

                        winMessage_TextView.setText(" It is a draw!");

                        LinearLayout playAgain_Layout = (LinearLayout)findViewById(R.id.playAgain_Layout);

                        playAgain_Layout.setVisibility(View.VISIBLE);

                    }

                }

            }

        }
    }
    //To play again the game after winning
    public void playOnClick(View view) {

        activeGame = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgain_Layout);

        layout.setVisibility(View.INVISIBLE);

        Player = 0;

        for (int i = 0; i < state_of_the_Game.length; i++) {

            state_of_the_Game[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageDrawable(null);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
