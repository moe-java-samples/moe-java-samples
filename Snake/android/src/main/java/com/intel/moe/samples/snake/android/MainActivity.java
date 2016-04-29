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

package com.intel.moe.samples.snake.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.intel.moe.samples.snake.common.Snake;
import com.intel.moe.samples.snake.common.eMode;
import com.intel.moe.samples.snake.common.eMoveDirection;
import com.intel.snake_xos.R;


public class MainActivity extends Activity {

    private Snake snake = null;
    private SnakeView gamePlace;
    private ImageView tailUp;
    private ImageView tailDown;
    private ImageView tailLeft;
    private ImageView tailRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        gamePlace = (SnakeView)findViewById(R.id.gamePlaceView);

        gamePlace.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (snake == null)
                    return false;
                if (snake.getGameState() == eMode.RUNNING) {
                    // Normalize x,y between 0 and 1
                    float x = event.getX() / v.getWidth();
                    float y = event.getY() / v.getHeight();

                    // Direction will be [0,1,2,3] depending on quadrant
                    int direction = 0;
                    direction = (x > y) ? 1 : 0;
                    direction |= (x > 1 - y) ? 2 : 0;

                    eMoveDirection move = eMoveDirection.MOVE_UP;

                    switch(direction) {
                    case 0:
                        move = eMoveDirection.MOVE_LEFT;
                        break;
                    case 1:
                        move = eMoveDirection.MOVE_UP;
                        break;
                    case 2:
                        move = eMoveDirection.MOVE_DOWN;
                        break;
                    case 3:
                        move = eMoveDirection.MOVE_RIGHT;
                        break;
                    default:
                        break;
                    }

                    // Direction is same as the quadrant which was clicked
                    snake.moveSnake(move);
                    gamePlace.updateSnake();
                } else {
                    // If the game is not running then on touching any part of the screen
                    // we start the game by sending MOVE_UP signal to SnakeView
                    snake.moveSnake(eMoveDirection.MOVE_UP);
                    gamePlace.update();
                }
                return false;
            }
        });

        tailUp = (ImageView)findViewById(R.id.imageUp);
        tailDown = (ImageView)findViewById(R.id.imageDown);
        tailLeft = (ImageView)findViewById(R.id.imageLeft);
        tailRight = (ImageView)findViewById(R.id.imageRight);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (snake == null) {
            snake = new Snake(gamePlace.getWidth(), gamePlace.getHeight(), 64);
            gamePlace.setSnake(snake);
            snake.initNewGame();
            gamePlace.update();

            tailUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snake.moveSnake(eMoveDirection.MOVE_UP);
                    gamePlace.update();
                }
            });

            tailDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snake.moveSnake(eMoveDirection.MOVE_DOWN);
                    gamePlace.update();
                }
            });

            tailLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snake.moveSnake(eMoveDirection.MOVE_LEFT);
                    gamePlace.update();
                }
            });

            tailRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snake.moveSnake(eMoveDirection.MOVE_RIGHT);
                    gamePlace.update();
                }
            });


        }
    }
}
