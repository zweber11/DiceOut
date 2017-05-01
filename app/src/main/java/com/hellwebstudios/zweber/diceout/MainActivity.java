package com.hellwebstudios.zweber.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Global vars.
    int score, die1, die2, die3;
    TextView txtRollResult, txtScore;
    Random rand;
    ArrayList<Integer> dice;
    ArrayList<ImageView> diceIVs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //Set initial score
        score = 0;

        txtRollResult = (TextView) findViewById(R.id.txtRollResult);
        txtScore = (TextView) findViewById(R.id.txtScore);

        //Create greeting
        Toast.makeText(getApplicationContext(), "Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        //Initialize the RNG.
        rand = new Random();

        //Create ArrayList container for the dice values.
        dice = new ArrayList<>();

        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);

        diceIVs = new ArrayList<>();
        diceIVs.add(die1Image);
        diceIVs.add(die2Image);
        diceIVs.add(die3Image);
    }

    public void rollDice(View v) {
        txtRollResult.setText("Rolling!");

        //Roll dice
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        //Set dice values into an ArrayList
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++) {
            String imageName = "die_" + dice.get(dieOfSet) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceIVs.get(dieOfSet).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Output
        String msg;

        if (die1 == die2 && die1 == die3) {
            //Triples
            int scoreDelta = die1 * 100;
            msg = "You rolled a triple " + die1 + "! You score " + scoreDelta + " points!";
            score += scoreDelta;
        } else if (die1 == die2 || die1 == die3 || die2 == die3) {
            //Doubles
            msg = "You rolled doubles for 50 points!";
            score += 50;
        } else {
            msg = "You didn't score this roll. Try again!";
        }

        txtRollResult.setText(msg);
        txtScore.setText("Score: " + score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
