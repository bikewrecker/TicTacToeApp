package net.drmcb.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView playButton = (TextView) findViewById(R.id.play_item);
        TextView helpButton = (TextView) findViewById(R.id.help_item);
        TextView settingsButton = (TextView) findViewById(R.id.settings_item);
        TextView aboutButton = (TextView) findViewById(R.id.about_item);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGame = new Intent(MainActivity.this, PlayActivity.class);
                startActivity(playGame);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpMenu = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(helpMenu);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsMenu = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settingsMenu);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutInfo = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutInfo);
            }
        });
    }
}
