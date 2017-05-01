package com.hellwebstudios.zweber.diceout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Global vars.
    int score, die1, die2, die3;
    TextView txtRollResult;
    Button btnRoll;
    Random rand;
    ArrayList<Integer> dice;


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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Set initial score
        score = 0;

        txtRollResult = (TextView) findViewById(R.id.txtRollResult);

        btnRoll = (Button) findViewById(R.id.btnRoll);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //Create greeting
        Toast.makeText(getApplicationContext(), "Welcome to DiceOut!", Toast.LENGTH_SHORT).show();

        //Initialize the RNG.
        rand = new Random();

        //Create ArrayList container for the dice values.
        dice = new ArrayList<>();

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

        //Output
        String msg = "You rolled a " + die1 + ", a " + die2 + ", and a " + die3;

        txtRollResult.setText(msg);
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
