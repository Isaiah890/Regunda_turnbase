package com.example.turnbase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button[][] buttons = new Button[3][3];
    private Boolean Player1Turn = true;

    private int roundCount;
    private int player1points;
    private int player2points;

    private TextView textviewPlayer1;
    private TextView textviewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewPlayer1  = findViewById(R.id.textview_P1);
        textviewPlayer2  = findViewById(R.id.textview_P2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Resetboard();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if(Player1Turn){
            ((Button)v).setText("X");
        }else {
            ((Button)v).setText("0");
        }
        roundCount++;

        if (Checkforwin()) {
            if(Player1Turn){
                player1wins();
            }else {
                player2wins();
            }
        }else if (roundCount == 9){
            draw();
        }else{
            Player1Turn = !Player1Turn;
        }
    }
    private Boolean Checkforwin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals("")) {
                return true;

            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals("")) {
            return true;

        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && field[0][2].equals("")) {
            return true;

        }
        return false;
    }

    private void player1wins() {
        player1points++;
        Toast.makeText(this,"Player1 wins", Toast.LENGTH_SHORT).show();
        UpdatePointsText();
        Resetboard();
    }
    private void player2wins() {
        player2points++;
        Toast.makeText(this,"Player2 wins", Toast.LENGTH_SHORT).show();
        UpdatePointsText();
        Resetboard();
    }
    private void draw() {
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        Resetboard();
    }
    private void UpdatePointsText() {
        textviewPlayer1.setText("Player 1" + player1points);
        textviewPlayer2.setText("Player 2" + player2points);
    }

    private void Resetboard() {

        for (int i = 0; i <3; i++){
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        Player1Turn = true;

    }

}