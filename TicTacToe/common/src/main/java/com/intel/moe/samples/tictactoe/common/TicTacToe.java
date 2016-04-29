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

package com.intel.moe.samples.tictactoe.common;

import java.util.List;
import java.util.ArrayList;

/*
 * There is the algorithm of the game.
 * In order to make the game unbeatable we used Minimax algorithm.
 */
public class TicTacToe {
    public static final int playerX = 1;
    public static final int player0 = -1;
    private int board[];
    private int computersMove;

    public TicTacToe() {
        board = new int[9];
        startNewGame();
    }

    public void startNewGame() {
        for (int i = 0; i < 9; ++i) {
            board[i] = 0;
        }
    }

    public boolean isGameOver() {
        return (wonX() || won0() || getAvailableCells().isEmpty());
    }

    public boolean won0() {
        if (diagonalWin(player0))
            return true;
        if (rowWin(player0) || colWin(player0))
            return true;
        return false;
    }

    public boolean wonX() {
        if (diagonalWin(playerX))
            return true;
        if (rowWin(playerX) || colWin(playerX))
            return true;
        return false;
    }

    private boolean diagonalWin(int player) {
        // Main diagonal
        if (board[0] == board[4] && board[0] == board[8] && board[0] == player)
            return true;
        // Secondary diagonal
        if (board[2] == board[4] && board[2] == board[6] && board[2] == player)
            return true;
        return false;
    }

    private boolean rowWin(int player) {
        for (int i = 0; i < 3; ++i) {
            if (board[3*i] == board[3*i + 1] && board[3*i] == board[3*i + 2] && board[3*i] == player)
                return true;
        }
        return false;
    }

    private boolean colWin(int player) {
        for (int i = 0; i < 3; ++i) {
            if (board[i] == board[i+3] && board[i] == board[i+6] && board[i] == player)
                return true;
        }
        return false;
    }

    public List<Integer> getAvailableCells() {
        List<Integer> availableCells = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {
            if (board[i] == 0) {
                availableCells.add(i);
            }
        }
        return availableCells;
    }

    public void newTurn(int index, int player) {
        board[index] = player;
    }

    public int minimax(int depth, int turn) {
        if (won0())
            return +1;
        if (wonX())
            return -1;

        List<Integer> pointsAvailable = getAvailableCells();
        if (pointsAvailable.isEmpty())
            return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            int point = pointsAvailable.get(i);
            if (turn == player0) {
                newTurn(point, player0);
                int currentScore = minimax(depth + 1, playerX);
                if (currentScore > max)
                    max = currentScore;

                if (currentScore >= 0) {
                    if(depth == 0)
                        computersMove = point;
                }
                if (currentScore == 1) {
                    board[point] = 0;
                    break;
                }
                if (i == pointsAvailable.size()-1 && max < 0) {
                    if(depth == 0)
                        computersMove = point;
                }
            }
            else if (turn == playerX) {
                newTurn(point, playerX);
                int currentScore = minimax(depth + 1, player0);
                if (currentScore < min)
                    min = currentScore;
                if (min == -1) {
                    board[point] = 0;
                    break;
                }
            }
            board[point] = 0;
        }

        return (turn == player0) ? max : min;
    }

    public int getComputersMove() {
        return computersMove;
    }
}
