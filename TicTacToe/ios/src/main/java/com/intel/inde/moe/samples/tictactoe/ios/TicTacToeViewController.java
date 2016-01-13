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

package com.intel.inde.moe.samples.tictactoe.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.samples.tictactoe.common.TicTacToe;

import ios.NSObject;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UILabel;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIControlState;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("TicTacToeViewController")
@RegisterOnStartup
public class TicTacToeViewController extends UIViewController {

    @Owned
    @Selector("alloc")
    public static native TicTacToeViewController alloc();

    @Selector("init")
    public native TicTacToeViewController init();

    protected TicTacToeViewController(Pointer peer) {
        super(peer);
    }

    private TicTacToe ticTacToeGame;

    public UILabel statusText = null;
    public UIButton newGameButton = null;

    public UIButton gameButton0 = null;
    public UIButton gameButton1 = null;
    public UIButton gameButton2 = null;

    public UIButton gameButton3 = null;
    public UIButton gameButton4 = null;
    public UIButton gameButton5 = null;

    public UIButton gameButton6 = null;
    public UIButton gameButton7 = null;
    public UIButton gameButton8 = null;

    @Override
    public void viewDidLoad() {
        ticTacToeGame = new TicTacToe();
        statusText = getStatusTextLabel();
        newGameButton = getNewGameButton();

        gameButton0 = getGameButton0();
        gameButton1 = getGameButton1();
        gameButton2 = getGameButton2();

        gameButton3 = getGameButton3();
        gameButton4 = getGameButton4();
        gameButton5 = getGameButton5();

        gameButton6 = getGameButton6();
        gameButton7 = getGameButton7();
        gameButton8 = getGameButton8();

        startNewGame();
    }

    @Selector("statusText")
    @Property
    public native UILabel getStatusTextLabel();

    @Selector("newGameButton")
    @Property
    public native UIButton getNewGameButton();

    @Selector("gameButton0")
    @Property
    public native UIButton getGameButton0();

    @Selector("gameButton1")
    @Property
    public native UIButton getGameButton1();

    @Selector("gameButton2")
    @Property
    public native UIButton getGameButton2();

    @Selector("gameButton3")
    @Property
    public native UIButton getGameButton3();

    @Selector("gameButton4")
    @Property
    public native UIButton getGameButton4();

    @Selector("gameButton5")
    @Property
    public native UIButton getGameButton5();

    @Selector("gameButton6")
    @Property
    public native UIButton getGameButton6();

    @Selector("gameButton7")
    @Property
    public native UIButton getGameButton7();

    @Selector("gameButton8")
    @Property
    public native UIButton getGameButton8();

    @Selector("BtnPressedCancel_newGameButton:")
    public void btnPressedCancel_newGameButton(NSObject sender){
        startNewGame();
    }

    @Selector("BtnPressedCancel_gameButton0:")
    public void btnPressedCancel_gameButton0(NSObject sender) {
        makeMove(0, "X");
        ticTacToeGame.newTurn(0, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton1:")
    public void btnPressedCancel_gameButton1(NSObject sender){
        makeMove(1, "X");
        ticTacToeGame.newTurn(1, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton2:")
    public void btnPressedCancel_gameButton2(NSObject sender){
        makeMove(2, "X");
        ticTacToeGame.newTurn(2, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton3:")
    public void btnPressedCancel_gameButton3(NSObject sender){
        makeMove(3, "X");
        ticTacToeGame.newTurn(3, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton4:")
    public void btnPressedCancel_gameButton4(NSObject sender){
        makeMove(4, "X");
        ticTacToeGame.newTurn(4, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton5:")
    public void btnPressedCancel_gameButton5(NSObject sender){
        makeMove(5, "X");
        ticTacToeGame.newTurn(5, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton6:")
    public void btnPressedCancel_gameButton6(NSObject sender){
        makeMove(6, "X");
        ticTacToeGame.newTurn(6, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton7:")
    public void btnPressedCancel_gameButton7(NSObject sender){
        makeMove(7, "X");
        ticTacToeGame.newTurn(7, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    @Selector("BtnPressedCancel_gameButton8:")
    public void btnPressedCancel_gameButton8(NSObject sender){
        makeMove(8, "X");
        ticTacToeGame.newTurn(8, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    public void startNewGame() {
        statusText.setText("Status: New Game, your turn...");
        statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 0 / 255.0, (float) 0 / 255.0, (float) 0 / 255.0, (float) 1.0));
        ticTacToeGame.startNewGame();

        gameButton0.setTitleForState("", UIControlState.Normal);
        gameButton0.setEnabled(true);
        gameButton1.setTitleForState("", UIControlState.Normal);
        gameButton1.setEnabled(true);
        gameButton2.setTitleForState("", UIControlState.Normal);
        gameButton2.setEnabled(true);

        gameButton3.setTitleForState("", UIControlState.Normal);
        gameButton3.setEnabled(true);
        gameButton4.setTitleForState("", UIControlState.Normal);
        gameButton4.setEnabled(true);
        gameButton5.setTitleForState("", UIControlState.Normal);
        gameButton5.setEnabled(true);

        gameButton6.setTitleForState("", UIControlState.Normal);
        gameButton6.setEnabled(true);
        gameButton7.setTitleForState("", UIControlState.Normal);
        gameButton7.setEnabled(true);
        gameButton8.setTitleForState("", UIControlState.Normal);
        gameButton8.setEnabled(true);
    }

    public void checkGameOver() {
        if (ticTacToeGame.isGameOver()) {
            gameButton0.setEnabled(false);
            gameButton1.setEnabled(false);
            gameButton2.setEnabled(false);

            gameButton3.setEnabled(false);
            gameButton4.setEnabled(false);
            gameButton5.setEnabled(false);

            gameButton6.setEnabled(false);
            gameButton7.setEnabled(false);
            gameButton8.setEnabled(false);

            if (ticTacToeGame.wonX()) {
                statusText.setText("Status: Congratulation! You win!");
                statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 50.0 / 255.0, (float) 205.0/255.0, (float) 50.0/255.0, (float) 1.0));
            }
            else if (ticTacToeGame.won0()) {
                statusText.setText("Status: Unfortunately, you lost!");
                statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 255.0/255.0, (float) 0/255.0, (float) 0/255.0, (float) 1.0));
            }
            else {
                statusText.setText("Status: It's a draw!");
                statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 218.0 / 255.0, (float) 165.0 / 255.0, (float) 32.0 / 255.0, (float) 1.0));
            }
        }
        else {
            statusText.setText("Status: Your turn...");
            statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0/255.0, (float) 0.0/255.0, (float) 0.0/255.0, (float) 1.0));
        }
    }

    private void makeMove(int index, String text) {
        switch (index) {
            case 0:
                gameButton0.setTitleForState(text, UIControlState.Normal);
                gameButton0.setEnabled(false);
                break;
            case 1:
                gameButton1.setTitleForState(text, UIControlState.Normal);
                gameButton1.setEnabled(false);
                break;
            case 2:
                gameButton2.setTitleForState(text, UIControlState.Normal);
                gameButton2.setEnabled(false);
                break;
            case 3:
                gameButton3.setTitleForState(text, UIControlState.Normal);
                gameButton3.setEnabled(false);
                break;
            case 4:
                gameButton4.setTitleForState(text, UIControlState.Normal);
                gameButton4.setEnabled(false);
                break;
            case 5:
                gameButton5.setTitleForState(text, UIControlState.Normal);
                gameButton5.setEnabled(false);
                break;
            case 6:
                gameButton6.setTitleForState(text, UIControlState.Normal);
                gameButton6.setEnabled(false);
                break;
            case 7:
                gameButton7.setTitleForState(text, UIControlState.Normal);
                gameButton7.setEnabled(false);
                break;
            case 8:
                gameButton8.setTitleForState(text, UIControlState.Normal);
                gameButton8.setEnabled(false);
                break;
        }
    }
}
