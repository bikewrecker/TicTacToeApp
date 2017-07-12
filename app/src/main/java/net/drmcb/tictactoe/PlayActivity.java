package net.drmcb.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Daniel on 7/3/2017.
 */

public class PlayActivity extends AppCompatActivity{
    final String RETURN_STRING = "ret";
    boolean[][] cellClicked = new boolean[3][3];
    int numMovesMade;
    int maxMoves;
    boolean player1 = false;
    boolean player2 = true;
    boolean currentPlayer;
    ImageView[][] cells = new ImageView[3][3];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        numMovesMade = 0;
        maxMoves = 9;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        currentPlayer = player1;
        cells[0][0] = (ImageView) findViewById(R.id.cell_0);
        cells[0][1] = (ImageView) findViewById(R.id.cell_1);
        cells[0][2] = (ImageView) findViewById(R.id.cell_2);
        cells[1][0] = (ImageView) findViewById(R.id.cell_3);
        cells[1][1] = (ImageView) findViewById(R.id.cell_4);
        cells[1][2] = (ImageView) findViewById(R.id.cell_5);
        cells[2][0] = (ImageView) findViewById(R.id.cell_6);
        cells[2][1] = (ImageView) findViewById(R.id.cell_7);
        cells[2][2] = (ImageView) findViewById(R.id.cell_8);
        for(int rowIter = 0; rowIter < 3; rowIter++) {
            for(int colIter = 0; colIter < 3; colIter++) {
                setListeners(rowIter, colIter);
            }
        }
    }//onCreate

    private void setListeners(int i, int j){
        final int row = i;
        final int col = j;
        cells[row][col].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cellClicked[row][col]) {
                    makeMove(cells[row][col], row, col);
                }
            }
        });
    }//setListeners

    private void makeMove(ImageView cell, int rowPos, int colPos){
        numMovesMade++;
        cellClicked[rowPos][colPos] = true;
        if(currentPlayer == player1) {
            cell.setImageResource(R.drawable.ic_close_black_24dp);
            cell.setTag(1);
        } else {
            cell.setImageResource(R.drawable.ic_panorama_fish_eye_black_24dp);
            cell.setTag(2);
        }
        boolean winner = checkForWinner(currentPlayer);
        if(winner){
            if(currentPlayer == player1) {
                gameOver(1);
            } else if(currentPlayer == player2){
                gameOver(2);
            }
        } else if(numMovesMade == maxMoves){
            gameOver(0);
        }
        togglePlayer();
    }//makeMove

    private void togglePlayer() {
        if(currentPlayer == player1){
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }//togglePlayer

    private boolean checkForWinner(boolean player){
        int value;
        boolean output = false;
        if(player == player1){
            value = 1;
        } else {
            value = 2;
        }
        for(int i = 0; i < 3; i++) {
            if(cells[i][0].getTag() == (Integer) value && cells[i][1].getTag() == (Integer) value && cells[i][2].getTag() == (Integer) value) {
                output = true;
            }//check rows
            if(cells[0][i].getTag() == (Integer) value && cells[1][i].getTag() == (Integer) value && cells[2][i].getTag() == (Integer) value) {
                output = true;
            }//check columns
        }
        //check if won by diagonal
        if(cells[1][1].getTag() == (Integer) value){
            if(cells[0][0].getTag() == (Integer) value && cells[2][2].getTag() == (Integer)value) {
                output = true;
            }
            if(cells[0][2].getTag() == (Integer) value &&  cells[2][0].getTag() == (Integer) value) {
                output = true;
            }
        }
        return output;
    }//checkForWinner

    private void gameOver(int player){
        Intent gameOverScreen = new Intent(PlayActivity.this, GameOverActivity.class);
        if(player != 0) {
            gameOverScreen.putExtra("Winner", player);
        }
        startActivityForResult(gameOverScreen, 1);
    } //gameOver

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra(RETURN_STRING);
                switch(result){
                    case "play":
                        //restart this activity;
                        recreate();
                        break;
                    case "menu":
                        //finish and return to main menu
                        finish();
                        break;
                    default:
                        Log.e("RETURN ERROR", "NO RETURN VALUE FROM GAMEOVERACTIVITY");
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.e("RESULT ERR", "NO RESULT RETURNED");
            }
        }
    }//onActivityResult
}//playActivity


