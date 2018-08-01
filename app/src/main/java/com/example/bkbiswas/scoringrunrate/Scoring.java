package com.example.bkbiswas.scoringrunrate;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Scoring extends AppCompatActivity {

    public int rotate=0;
    public int innings = 1;
    public int overball=0;
    public int player[] = new int[3];
    public int playerball[] = new int[3];
    public int wicket = 0;
    public int totalrun = 0;
    public int totalover = 0;
    public int overrun;
    public int extracounter = 0;
    public int noofover;
    public int noofwicket;
    public int battingfirst;
    public int firstinningsrun;
    public int firstinningswicket;
    public String s;
    public String team1;
    public String team2;
    public String player1nameMain;
    public String player2nameMain;

    public TextView over;
    public TextView score1;
    public TextView score2;
    public TextView score;
    public TextView target;
    public TextView targetrun;
    public TextView teamname;
    public TextView thisover;
    public TextView name1;
    public TextView name2;

    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Button b5;
    public Button b6;
    public Button b7;
    public Button b0;
    public Button bbye;
    public Button bwkt;
    public Button bwd;
    public Button bnb;
    public Button bfinish;





    public void checkButton(View v){

    }





    public void init(){
        score.setText("  0/0");
        score1.setText("0* (0)");
        score2.setText("0 (0)");
        s = "This Over:";
        rotate = 0;

        if(battingfirst == 1 && innings ==1){
            target.setText("First");
            targetrun.setText("Innings");
            teamname.setText("   " + team1);
            over.setText("0.0 Over");
            name1.setText(player1nameMain);
            name2.setText(player2nameMain);
            totalrun = 0;
        }

        else if(battingfirst == 2 && innings == 1){
            target.setText("First");
            targetrun.setText("Innings");
            teamname.setText("   " + team2);
            over.setText("0.0 Over");
            name1.setText(player1nameMain);
            name2.setText(player2nameMain);
            totalrun = 0;
        }

        if(innings == 2){
            thisover.setText("This Over: ");
            firstinningsrun = totalrun;
            totalrun++;
            target.setText("Target");
            targetrun.setText(totalrun + "");
            totalrun = 0;
            if(battingfirst == 1)   teamname.setText("  " + team2);
            else if(battingfirst == 2)  teamname.setText("  " + team1);
            over.setText("0.0 Over");
            overrun = 0;
            overball = 0;
            player[1] = 0;
            playerball[1] = 0;
            player[2] = 0;
            playerball[2] = 0;
            rotate = 0;
            wicket = 0;
            totalover = 0;
            extracounter = 0;
            firstinningswicket = wicket;



            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Scoring.this);
            mBuilder.setCancelable(false);
            final EditText p1,p2;
            final TextView t;

            View mView = getLayoutInflater().inflate(R.layout.secondinnings, null);
            t = (TextView) mView.findViewById(R.id.t);
            p1 = (EditText) mView.findViewById(R.id.p1);
            p2 = (EditText) mView.findViewById(R.id.p2);

            if(battingfirst == 1)   t.setText(team2);
            else if(battingfirst == 2)  t.setText(team1);

            mBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    player1nameMain = p1.getText().toString().trim();
                    player2nameMain = p2.getText().toString().trim();

                    if(player1nameMain.isEmpty() == true)   player1nameMain = "Player 1";
                    if(player2nameMain.isEmpty() == true)   player2nameMain = "Player 2";

                    name1.setText(player1nameMain);
                    name2.setText(player2nameMain);
                }
            });

            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
        }

        innings++;
    }




//score1.setText(player[1] + "(" + playerball[1] + ")");
// score2.setText(player[2] + "* (" + playerball[2] + ")");


    public void setTotalrun(int index){
        if(overball == 0 && extracounter==0){
            overrun = 0;
            thisover.setText(s);
        }

        overrun+=index;
        totalrun+=index;
        overball++;

        if(innings == 3 && totalrun > firstinningsrun){
            result();
            return;
        }

        s = s + "   " + index;

        thisover.setText(s + " = " + overrun);

        if(totalrun>=0 && totalrun<=9) score.setText("  " + totalrun + "/" + wicket);
        else if(totalrun>=10 && totalrun<=99) score.setText(" " + totalrun + "/" + wicket);
        else if(totalrun>99) score.setText(totalrun + "/" + wicket);

        if(rotate%2 == 0){
            player[1]+=index;
            playerball[1]++;

            if(index%2 == 1){
                rotate++;

                score1.setText(player[1] + "(" + playerball[1] + ")");
                score2.setText(player[2] + "* (" + playerball[2] + ")");
            }

            else{
                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }
        }

        else{
            player[2]+=index;
            playerball[2]++;

            if(index % 2 == 1){
                rotate++;

                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }

            else{
                score1.setText(player[1] + "(" + playerball[1] + ")");
                score2.setText(player[2] + "* (" + playerball[2] + ")");
            }
        }


        if(overball == 6){
            overball = 0;
            rotate++;
            totalover++;

            if(innings == 2 && totalover == noofover)   init();

            if(innings == 3 && totalover == noofover){
                result();
                return;
            }

            extracounter = 0;
            s = "This Over:";
            overrun = 0;

            if(rotate % 2 == 0){
                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }

            else{
                score2.setText(player[2] + "* (" + playerball[2] + ")");
                score1.setText(player[1] + "(" + playerball[1] + ")");
            }
        }

        over.setText(totalover + "." + overball + " Over");

        if(totalover == noofover)   init();
    }












    public void extrarun(int index, int wnb, int flag){
        overrun += index;
        if(wnb!=2) overrun++;
        totalrun+=index;
        if(wnb!=2)  totalrun++;

        if(innings == 3 && totalrun > firstinningsrun){
            result();
            return;
        }

        extracounter++;

        if(wnb == 0)   s = s + "   " + index + "wd";
        else if(wnb == 1)   s = s + "   " + index + "nb";
        else    s = s + "   " + index + "bye";

        thisover.setText(s + " = " + overrun);

        if(totalrun>=0 && totalrun<=9) score.setText("  " + totalrun + "/" + wicket);
        else if(totalrun>=10 && totalrun<=99) score.setText(" " + totalrun + "/" + wicket);
        else if(totalrun>99) score.setText(totalrun + "/" + wicket);


        if(rotate%2 == 0  && (wnb ==0 || wnb == 2 || flag == 0)){
            if(index%2 == 1){
                rotate++;

                if(flag == 0)   playerball[1]++;

                score1.setText(player[1] + "(" + playerball[1] + ")");
                score2.setText(player[2] + "* (" + playerball[2] + ")");
            }

            else{
                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }
        }

        else if(rotate %2 == 1 && (wnb ==0 || wnb == 2 || flag == 0)){
            if(index % 2 == 1){
                rotate++;

                if(flag == 0)   playerball[2]++;

                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }


        }






        if(rotate%2 == 0 && flag == 1){
            player[1]+=index;
            playerball[1]++;

            if(index%2 == 1){
                rotate++;

                score1.setText(player[1] + "(" + playerball[1] + ")");
                score2.setText(player[2] + "* (" + playerball[2] + ")");
            }

            else{
                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }
        }

        else if(rotate%2 == 1 && flag == 1){
            player[2]+=index;
            playerball[2]++;

            if(index % 2 == 1){
                rotate++;

                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }

            else{
                score1.setText(player[1] + "(" + playerball[1] + ")");
                score2.setText(player[2] + "* (" + playerball[2] + ")");
            }
        }
    }








    public void wicketFall(int strike){
        overball++;
        s = s + "   " + "wkt";

        thisover.setText(s + " = " + overrun);

        if(totalrun>=0 && totalrun<=9) score.setText("  " + totalrun + "/" + wicket);
        else if(totalrun>=10 && totalrun<=99) score.setText(" " + totalrun + "/" + wicket);
        else if(totalrun>99) score.setText(totalrun + "/" + wicket);

        if(strike == 1) rotate = 0;
        else if(strike == 2)    rotate = 1;

        if(strike == 1){
            score1.setText(player[1] + "* (" + playerball[1] + ")");
            score2.setText(player[2] + "(" + playerball[2] + ")");
        }

        else if(strike == 2){
            score1.setText(player[1] + "(" + playerball[1] + ")");
            score2.setText(player[2] + "* (" + playerball[2] + ")");
        }


        if(overball == 6){
            overball = 0;
            totalover++;
            extracounter = 0;
            s = "This Over:";
            overrun = 0;

            if(rotate % 2 == 0){
                score1.setText(player[1] + "* (" + playerball[1] + ")");
                score2.setText(player[2] + "(" + playerball[2] + ")");
            }

            else{
                score2.setText(player[2] + "* (" + playerball[2] + ")");
                score1.setText(player[1] + "(" + playerball[1] + ")");
            }
        }

        over.setText(totalover + "." + overball + " Over");
    }








    void result(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Scoring.this);
        mBuilder.setCancelable(false);
        final TextView t1,t2,t3;

        View mView = getLayoutInflater().inflate(R.layout.result, null);
        t1 = (TextView) mView.findViewById(R.id.t1);
        t2 = (TextView) mView.findViewById(R.id.t2);
        t3 = (TextView) mView.findViewById(R.id.t3);

        t1.setText("     " + team1 + "          " + firstinningsrun + "/" + firstinningswicket);
        t2.setText("     " + team2 + "          " + totalrun + "/" + wicket);

        String h1 = null,h2 = null;

        if(battingfirst == 2)  {
            h1 = team2;
            h2 = team1;
        }

        else if(battingfirst == 1){
            h1 = team1;
            h2 = team2;
        }


        if(firstinningsrun == totalrun){
            t3.setText("     MATCH DRAWN");
        }

        else if(firstinningsrun > totalrun){
            t3.setText("     " + h1 + " won by " + (firstinningsrun - totalrun) + " runs");
        }

        else if(firstinningsrun < totalrun){
            t3.setText("     " + h2 + " won by " + (noofwicket - wicket) + " wickets");
        }

        mBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                View mView = getLayoutInflater().inflate(R.layout.activity_match_info, null);
                final EditText eteam1 = (EditText) mView.findViewById(R.id.team1);
                final EditText eteam2 = (EditText) mView.findViewById(R.id.team2);
                final EditText enoofover = (EditText) mView.findViewById(R.id.noofover);
                final EditText enoofwicket = (EditText) mView.findViewById(R.id.noofwicket);
                final EditText player1 = (EditText) mView.findViewById(R.id.player1);
                final EditText player2 = (EditText) mView.findViewById(R.id.player2);

                eteam1.setText("");
                eteam2.setText("");
                enoofover.setText("");
                enoofwicket.setText("");
                player1.setText("");
                player2.setText("");

                Intent intent = new Intent(Scoring.this, MatchInfo.class);
                startActivity(intent);
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        final Intent intent = getIntent();
        team1 = intent.getStringExtra("TEAM1");
        team2 = intent.getStringExtra("TEAM2");
        noofover = Integer.parseInt(intent.getStringExtra("NOOFOVER"));
        noofwicket = Integer.parseInt(intent.getStringExtra("NOOFWICKET"));
        player1nameMain = intent.getStringExtra("PLAYER1NAME");
        player2nameMain = intent.getStringExtra("PLAYER2NAME");
        battingfirst = Integer.parseInt(intent.getStringExtra("BATTINGFIRST"));

        noofwicket--;


        over = (TextView) findViewById(R.id.over);
        score1 = (TextView)findViewById(R.id.score1);
        score2 = (TextView)findViewById(R.id.score2);
        score = (TextView)findViewById(R.id.score);
        target = (TextView)findViewById(R.id.target);
        targetrun = (TextView)findViewById(R.id.targetrun);
        teamname = (TextView)findViewById(R.id.teamname);
        thisover = (TextView)findViewById(R.id.thisover);
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);

        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        b7 = (Button)findViewById(R.id.b7);
        b0 = (Button)findViewById(R.id.b0);
        bbye = (Button)findViewById(R.id.bBye);
        bwkt = (Button)findViewById(R.id.bWkt);
        bwd = (Button)findViewById(R.id.bWide);
        bnb = (Button)findViewById(R.id.bNB);
        bfinish = (Button)findViewById(R.id.bfinish);

        init();



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(4);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(5);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(6);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(7);
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTotalrun(0);
            }
        });

        bwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Scoring.this);
                mBuilder.setCancelable(false);

                final RadioGroup radioGroup;

                View mView = getLayoutInflater().inflate(R.layout.wide_alert, null);

                radioGroup = (RadioGroup) mView.findViewById(R.id.radioGroup);

                mBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton = radioGroup.findViewById(radioId);
                        radioId = radioGroup.indexOfChild(radioButton);

                        extrarun(radioId,0,5);
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });








        bnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Scoring.this);
                mBuilder.setCancelable(false);

                final RadioGroup radioGroup;
                final RadioGroup radioGroup2;
                View mView = getLayoutInflater().inflate(R.layout.nb_alert, null);
                radioGroup = (RadioGroup) mView.findViewById(R.id.radioGroup);
                radioGroup2 = (RadioGroup) mView.findViewById(R.id.radioGroup2);

                mBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton = radioGroup.findViewById(radioId);
                        radioId = radioGroup.indexOfChild(radioButton);

                        int batsorbye = radioGroup2.getCheckedRadioButtonId();
                        RadioButton radioButton2 = radioGroup2.findViewById(batsorbye);
                        batsorbye = radioGroup2.indexOfChild(radioButton2);

                        if(batsorbye == 0)  extrarun(radioId,1, 1);
                        else    extrarun(radioId,1,0);
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });



        

        bwkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wicket++;
                if(innings == 3 && wicket == noofwicket){
                    result();
                    return;
                }

                if(innings == 2 && wicket == noofwicket){
                    init();
                    return;
                }

                if(rotate % 2 == 0) playerball[1]++;
                else if(rotate % 2 == 1)    playerball[2]++;

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Scoring.this);
                mBuilder.setCancelable(false);

                final RadioGroup radioGroup;
                final RadioGroup radioGroup2;
                final RadioButton radioButton0;
                final RadioButton radioButton1;
                final EditText newplayer;

                View mView = getLayoutInflater().inflate(R.layout.wkt_alert, null);

                radioGroup = (RadioGroup) mView.findViewById(R.id.radioGroup);
                radioGroup2 = (RadioGroup) mView.findViewById(R.id.radioGroup2);
                radioButton0 = (RadioButton) mView.findViewById(R.id.zero);
                radioButton1 = (RadioButton) mView.findViewById(R.id.one);
                newplayer = (EditText) mView.findViewById(R.id.newplayer);

                radioButton0.setText(player1nameMain);
                radioButton1.setText(player2nameMain);


                mBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton = radioGroup.findViewById(radioId);
                        radioId = radioGroup.indexOfChild(radioButton);
                        int x = 0;

                        String h = newplayer.getText().toString().trim();
                        if(h.isEmpty() == true) h = "Player " + (wicket + 2) + "";

                        if(radioId == 0){
                            player1nameMain = h;
                            playerball[1] = 0;
                            player[1] = 0;
                            name1.setText(h);
                        }

                        else{
                            name2.setText(h);
                            player2nameMain = h;
                            player[2] = 0;
                            playerball[2] = 0;
                        }

                        int batsorbye = radioGroup2.getCheckedRadioButtonId();
                        RadioButton radioButton2 = radioGroup2.findViewById(batsorbye);
                        batsorbye = radioGroup2.indexOfChild(radioButton2);

                        if((radioId == 0 && batsorbye == 0) || (radioId ==1 && batsorbye == 1)) x=1;

                        else if((radioId == 1 && batsorbye == 0) || (radioId == 0 && batsorbye ==1))    x =2;

                        wicketFall(x);
                    }
                });



                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });





        bbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Scoring.this);
                mBuilder.setCancelable(false);

                final RadioGroup radioGroup;

                View mView = getLayoutInflater().inflate(R.layout.bye_alert, null);

                radioGroup = (RadioGroup) mView.findViewById(R.id.radioGroup);

                mBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton = radioGroup.findViewById(radioId);
                        radioId = radioGroup.indexOfChild(radioButton);

                        extrarun(radioId, 2, 5);
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        bfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(innings == 3){
                    result();
                    return;
                }
                init();

            }
        });
    }
}
