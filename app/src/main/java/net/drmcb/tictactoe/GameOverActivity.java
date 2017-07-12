package net.drmcb.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Daniel on 7/4/2017.
 */

public class GameOverActivity extends AppCompatActivity{
    int winner;
    final String RETURN_STRING = "ret";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        TextView title = (TextView) findViewById(R.id.gameover_title);
        TextView playAgain = (TextView) findViewById(R.id.gameover_again);
        TextView backToMenu = (TextView) findViewById(R.id.gameover_back);
        winner = getIntent().getIntExtra("Winner", 0);
        if(winner != 0) {
            if(winner == 1) {
                title.setText("Player 1 Wins!");
            } else {
                title.setText("Player 2 Wins!");
            }
        } else {
            title.setText("Cat's Game!");
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RETURN_STRING,"play");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RETURN_STRING,"menu");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }
}
