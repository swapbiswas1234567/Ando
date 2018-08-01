package com.example.bkbiswas.scoringrunrate;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MatchInfo extends AppCompatActivity {
    public String Team1 = "Team 1";
    public String Team2 = "Team 2";
    public String Over;
    public String Wicket;
    public String Player1name;
    public String Player2name;
    public int over;
    public int wicket;

    public EditText eteam1;
    public EditText eteam2;
    public EditText enoofover;
    public EditText enoofwicket;
    public EditText player1;
    public EditText player2;
    public Spinner ebattingfirst;
    public Button estart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        ebattingfirst = (Spinner) findViewById(R.id.battingFirst);
        estart = (Button) findViewById(R.id.start);
        eteam1 = (EditText) findViewById(R.id.team1);
        eteam2 = (EditText) findViewById(R.id.team2);
        enoofover = (EditText) findViewById(R.id.noofover);
        enoofwicket = (EditText) findViewById(R.id.noofwicket);
        player1 = (EditText) findViewById(R.id.player1);
        player2 = (EditText) findViewById(R.id.player2);

        //INIT


        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Team 1");
        list.add("Team 2");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ebattingfirst.setAdapter(adapter);

        estart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Team1 = eteam1.getText().toString().trim();
                Team2 = eteam2.getText().toString().trim();
                Over = enoofover.getText().toString().trim();
                Wicket = enoofwicket.getText().toString().trim();
                Player1name = player1.getText().toString().trim();
                Player2name = player2.getText().toString().trim();

                if(Team1.isEmpty() == true)   Team1 = "Team 1";
                if(Team2.isEmpty() == true)   Team2 = "Team 2";
                if(Over.isEmpty() == true)    Over = "10";
                if(Wicket.isEmpty() == true)  Wicket = "11";
                if(Player1name.isEmpty() == true) Player1name = "Player 1";
                if(Player2name.isEmpty()== true) Player2name = "Player 2";

                int i = ebattingfirst.getSelectedItemPosition();
                i++;

                Intent intent;
                intent = new Intent(MatchInfo.this, Scoring.class);
                intent.putExtra("BATTINGFIRST",i + "");
                intent.putExtra("TEAM1",Team1);
                intent.putExtra("TEAM2",Team2);
                intent.putExtra("NOOFOVER",Over);
                intent.putExtra("NOOFWICKET",Wicket);
                intent.putExtra("PLAYER1NAME", Player1name);
                intent.putExtra("PLAYER2NAME", Player2name);
                startActivity(intent);


            }
        });
    }
}
