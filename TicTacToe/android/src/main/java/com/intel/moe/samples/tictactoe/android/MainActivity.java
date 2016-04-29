// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.intel.moe.samples.tictactoe.android;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.intel.moe.samples.tictactoe.common.TicTacToe;
import com.intel.sample.tictactoe.R;


public class MainActivity extends Activity implements OnClickListener {

    private Button gameButtons[];
    private TextView statusText;
    private TicTacToe ticTacToeGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = (TextView) findViewById(R.id.statusText);

        Button newGameButton = (Button) findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(this);

        gameButtons = new Button[9];
        for(int i = 0; i < 9; ++i) {
            String buttonID = "gameButton" + i;
            int resID = getResources().getIdentifier(buttonID, "id", "com.intel.moe.samples.tictactoe.android");
            gameButtons[i] = ((Button) findViewById(resID));
            gameButtons[i].setOnClickListener(this);
        }

        for (int i = 0; i < 9; ++i) {
            gameButtons[i].setOnClickListener(this);
        }

        ticTacToeGame = new TicTacToe();
        startNewGame();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.newGameButton) {
            startNewGame();
            return;
        }
        for(int i = 0; i < 9; ++i) {
            String buttonID = "gameButton" + i;
            Resources res = getResources();
            int resID = res.getIdentifier(buttonID, "id", this.getPackageName());
            int lookupID = v.getId();
            if (lookupID == resID) {
                gameButtons[i].setText("X");
                gameButtons[i].setClickable(false);
                ticTacToeGame.newTurn(i, TicTacToe.playerX);
                ticTacToeGame.minimax(0, TicTacToe.player0);
                ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
                gameButtons[ticTacToeGame.getComputersMove()].setText("0");
                gameButtons[ticTacToeGame.getComputersMove()].setClickable(false);
                if (ticTacToeGame.isGameOver()) {
                    for (int j = 0; j < 9; ++j) {
                        gameButtons[j].setClickable(false);
                    }
                    if (ticTacToeGame.wonX()) {
                        statusText.setText("Status: Congratulation! You win!");
                        statusText.setTextColor(Color.parseColor("#32CD32"));
                    }
                    else if (ticTacToeGame.won0()) {
                        statusText.setText("Status: Unfortunately, you lost!");
                        statusText.setTextColor(Color.RED);
                    }
                    else {
                        statusText.setText("Status: It's a draw!");
                        statusText.setTextColor(Color.parseColor("#DAA520"));
                    }
                }
                else {
                    statusText.setText("Status: Your turn...");
                    statusText.setTextColor(Color.BLACK);
                }
                return;
            }
        }
    }

    private void startNewGame() {
        statusText.setText("Status: New Game, your turn...");
        statusText.setTextColor(Color.BLACK);
        ticTacToeGame.startNewGame();
        for(int i = 0; i < 9; ++i) {
            gameButtons[i].setText("");
            gameButtons[i].setClickable(true);
        }
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
