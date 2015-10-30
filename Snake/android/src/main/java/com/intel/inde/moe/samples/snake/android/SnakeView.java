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

package com.intel.inde.moe.samples.snake.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.intel.inde.moe.samples.snake.common.Snake;
import com.intel.inde.moe.samples.snake.common.eMode;
import com.intel.inde.moe.samples.snake.common.eTileTypes;
import com.intel.snake_xos.R;


public class SnakeView extends SurfaceView {

    private final Paint mPaint = new Paint();

    /**
     * A two-dimensional array of integers in which the number represents the index of the tile that
     * should be drawn at that locations
     */

    private Snake snake;
    private Context app_context;
    private RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            SnakeView.this.update();
            SnakeView.this.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        app_context = context;
    }

    public SnakeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        app_context = context;
    }

    public SnakeView(Context context) {
        super(context);
        setFocusable(true);
        app_context = context;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
System.out.println("On draw called");
        if (snake == null) return;
        eTileTypes[][] grid = snake.GetTileGrid();
        for (int x = 0; x < snake.GetXTileCount(); x += 1) {
            for (int y = 0; y < snake.GetYTileCount(); y += 1) {
                if (grid[x][y] != eTileTypes.NULL_STAR) {
                    canvas.drawBitmap(createTile(grid[x][y]), snake.GetXOffset() + x * snake.GetTileSize(),
                            snake.GetYOffset() + y * snake.GetTileSize(), mPaint);
                }
            }
        }

    }

    public void update() {
        if (snake == null) return;
        if (snake.getGameState() == eMode.RUNNING) {
            snake.update();
            if (snake.getGameState() == eMode.LOSE) {
                AlertDialog.Builder builder = new AlertDialog.Builder(app_context)
                        .setTitle("Game over")
                        .setCancelable(false)
                        .setMessage("Do you want to start the game again?")
                        .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                snake.initNewGame();
                                update();
                            }
                        });
				try {
					AlertDialog dlg = builder.create();
					dlg.show();
				} catch (WindowManager.BadTokenException e) {
					snake.initNewGame();
                    update();
				}
            }
            mRedrawHandler.sleep(snake.getMoveDelay());
        }

    }

    public void updateSnake() {
        if (snake == null) return;
        if (snake.getGameState() == eMode.RUNNING) {
            snake.updateSnakeOnly();
            if (snake.getGameState() == eMode.LOSE) {
                AlertDialog.Builder builder = new AlertDialog.Builder(app_context)
                        .setTitle("Game over")
                        .setCancelable(false)
                        .setMessage("Do you want to start the game again?")
                        .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                snake.initNewGame();
                                update();
                            }
                        });
                AlertDialog dlg = builder.create();
                dlg.show();
            }
            mRedrawHandler.sleep(snake.getMoveDelay());
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (snake == null) return;
        snake.SetXTileCount((int) Math.floor(w / snake.GetTileSize()));
        snake.SetYTileCount((int) Math.floor(h / snake.GetTileSize()));

        snake.SetXOffset(((w - (snake.GetTileSize() * snake.GetXTileCount())) / 2));
        snake.SetYOffset(((h - (snake.GetTileSize() * snake.GetYTileCount())) / 2));

        snake.clearAllTiles();
    }

    private Bitmap createTile(eTileTypes type){

        if (snake == null) return null;
        Bitmap img = null;

        switch(type) {
            case GREEN_STAR:
                img = BitmapFactory.decodeResource(getResources(), R.drawable.greenstar);
                break;
            case YELLOW_STAR:
                img = BitmapFactory.decodeResource(getResources(), R.drawable.yellowstar);
                break;
            case RED_STAR:
                img = BitmapFactory.decodeResource(getResources(), R.drawable.redstar);
                break;
            default:
                return null;
        }

        BitmapDrawable tile = new BitmapDrawable(getResources(), img);

        Bitmap bitmap = Bitmap.createBitmap(snake.GetTileSize(), snake.GetTileSize(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, snake.GetTileSize(), snake.GetTileSize());
        tile.draw(canvas);

        return bitmap;
    }
}
