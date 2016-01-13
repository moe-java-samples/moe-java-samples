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

package com.intel.inde.moe.samples.snake.ios;

import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.samples.snake.common.Snake;
import com.intel.inde.moe.samples.snake.common.eMode;
import com.intel.inde.moe.samples.snake.common.eMoveDirection;
import com.intel.inde.moe.samples.snake.common.eTileTypes;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.coregraphics.c.CoreGraphics;
import ios.coregraphics.struct.CGRect;
import ios.foundation.NSRunLoop;
import ios.foundation.NSTimer;
import ios.foundation.c.Foundation;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UIImage;
import ios.uikit.UIImageView;
import ios.uikit.UIScreen;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIControlState;
import ios.uikit.enums.UIInterfaceOrientation;

@ObjCClassName("GameController")
public class GameController extends UIViewController {

    protected GameController(Pointer peer) {
        super(peer);
    }

    private static UIImageView[][] uiImageGrid;
    private static eTileTypes[][] lastTileState;


    private static UIButton tailUp = null;
    private static UIButton tailDown = null;
    private static UIButton tailLeft = null;
    private static UIButton tailRight = null;
    private static UIView gameView = null;
    private boolean isPaused = false;
    double yStartPoint = 0;
    private static UIImage greenStar, redStar, yellowStar, pauseImg;
    CGRect gameBounds = null;
    private static Snake snake = null;

    private static NSTimer timer;
    private static boolean waitUserReaction = false;

    @Owned
    @Selector("alloc")
    public static native GameController alloc();

    @Selector("init")
    public native GameController init();

    public void viewDidLoad()
    {
        super.viewDidLoad();
        CGRect bounds = UIScreen.mainScreen().bounds();
        yStartPoint = bounds.size().width()/5;
        gameBounds = CoreGraphics.CGRectMake(0 , yStartPoint,bounds.size().width(), bounds.size().height() -  yStartPoint );
//        CGRect navigationFrame = CoreGraphics.CGRectMake(0, 0, bounds.size().width(), bounds.size().width() / 5);
//        navigationController().navigationBar().setHidden(false);
//        navigationController().navigationBar().setFrame(navigationFrame);

        UIBarButtonItem bmbutton = UIBarButtonItem.alloc()
                .initWithTitleStyleTargetAction("Back to main menu",
                        UIBarButtonItemStyle.Plain, this,
                        new SEL("backAction"));
        navigationItem().setLeftBarButtonItem(bmbutton);

        final double taileSize = gameBounds.size().width()/10;
        snake = new Snake((int)gameBounds.size().width(), (int)gameBounds.size().height(), (int)taileSize);

        // gameView = UIView.alloc().initWithFrame(gameBounds);

        final double tileW = taileSize;
        final double tileH = taileSize;
        double w = bounds.size().width();
        double h = bounds.size().height();

        greenStar = UIImage.imageNamed("greenstar.png");
        yellowStar = UIImage.imageNamed("yellowstar.png");
        redStar = UIImage.imageNamed("redstar.png");
        // pauseImg = UIImage.imageNamed("pause_green.png");


        tailUp = UIButton.alloc().init();
        tailUp.setFrame(CoreGraphics.CGRectMake(w / 2 - tileW / 2, tileH + yStartPoint, taileSize, taileSize));
        tailUp.setImageForState(UIImage.imageNamed("dpad_up.png"), UIControlState.Normal);

        tailDown = UIButton.alloc().init();
        tailDown.setFrame(CoreGraphics.CGRectMake(w / 2 - tileW / 2, h - 2 * tileH, taileSize, taileSize));
        tailDown.setImageForState(UIImage.imageNamed("dpad_down.png"), UIControlState.Normal);

        //tailDown.setTransform(tailDown.getTransform().rotate(3.15));	TODO CGAffineTransform functions

        tailLeft = UIButton.alloc().init();
        tailLeft.setFrame(CoreGraphics.CGRectMake(tileW, h / 2 - tileH / 2, taileSize, taileSize));
        tailLeft.setImageForState(UIImage.imageNamed("dpad_left.png"), UIControlState.Normal);

        // tailLeft.setTransform(tailLeft.getTransform().rotate(3.15 + 3.15/2.0));		TODO CGAffineTransform functions

        tailRight = UIButton.alloc().init();
        tailRight.setFrame(CoreGraphics.CGRectMake(w - tileW * 2, h / 2 - tileH / 2, taileSize, taileSize));
        tailRight.setImageForState(UIImage.imageNamed("dpad_right.png"), UIControlState.Normal);

        //tailRight.setTransform(tailRight.getTransform().rotate(3.15/2.0));			TODO CGAffineTransform functions

        tailUp.addTargetActionForControlEvents(this, Foundation.NSSelectorFromString("onUp:"), UIControlEvents.TouchUpInside);
        tailDown.addTargetActionForControlEvents(this, Foundation.NSSelectorFromString("onDown:"), UIControlEvents.TouchUpInside);
        tailLeft.addTargetActionForControlEvents(this, Foundation.NSSelectorFromString("onLeft:"), UIControlEvents.TouchUpInside);
        tailRight.addTargetActionForControlEvents(this, Foundation.NSSelectorFromString("onRight:"), UIControlEvents.TouchUpInside);

        dialogNewGame();
    }


    @Selector("backAction")
    public void back() {
        timer.invalidate();
        timer = null;
        navigationController().popViewControllerAnimated(true);
        gameView.removeFromSuperview();
        tailUp.removeFromSuperview();
        tailDown.removeFromSuperview();
        tailLeft.removeFromSuperview();
        tailRight.removeFromSuperview();
    }

    @Selector("onUp:")
    public void onUp(UIButton button) {
        snake.moveSnake(eMoveDirection.MOVE_UP);
    }

    @Selector("timerFireMethod:")
    public void timerFireMethod(NSTimer timer) {
        if(isPaused) return;
        if (snake.getGameState() == eMode.LOSE) {
            dialogNewGame(); return;
        } else if(snake.getGameState() == eMode.NEWGAME) {
            dialogNewGame(); return;
        } else {
            waitUserReaction = false;
        }
        if(Main.isAIGame) snake.moveSelf();

        snake.updateSnakeOnly();

        reDrawSnakeTails();

         // renderSubView();
    }

    @Selector("onDown:")
    public void onDown(UIButton button) {
        snake.moveSnake(eMoveDirection.MOVE_DOWN);
    }

    @Selector("onLeft:")
    public void onLeft(UIButton button) {
        snake.moveSnake(eMoveDirection.MOVE_LEFT);
    }

    @Selector("onRight:")
    public void onRight(UIButton button) {
        snake.moveSnake(eMoveDirection.MOVE_RIGHT);
    }


    private void dialogNewGame() {
        if(timer != null) {
            timer.invalidate();
            timer = null;
        }

        snake.initNewGame();
        renderSubView();


            timer = NSTimer.scheduledTimerWithTimeIntervalTargetSelectorUserInfoRepeats(0.25,
                    this, Foundation.NSSelectorFromString("timerFireMethod:"), null, true);


            NSRunLoop.currentRunLoop().addTimerForMode(timer, Foundation.NSDefaultRunLoopMode());
        waitUserReaction = false;

    }

    private void renderSubView() {
        if(gameView != null) gameView.removeFromSuperview();
        tailUp.removeFromSuperview();
        tailDown.removeFromSuperview();
        tailLeft.removeFromSuperview();
        tailRight.removeFromSuperview();

        gameView = draw();

        Main.gameWindow.addSubview(gameView);
        gameView.setBackgroundColor(UIColor.lightGrayColor());
        if(!Main.isAIGame) {
            Main.gameWindow.addSubview(tailUp);
            Main.gameWindow.addSubview(tailDown);
            Main.gameWindow.addSubview(tailLeft);
            Main.gameWindow.addSubview(tailRight);
            Main.gameWindow.bringSubviewToFront(tailUp);
            Main.gameWindow.bringSubviewToFront(tailDown);
            Main.gameWindow.bringSubviewToFront(tailLeft);
            Main.gameWindow.bringSubviewToFront(tailRight);
        }
        //window.makeKeyAndVisible();
    }


    private  UIView draw()
    {
        uiImageGrid = new UIImageView[snake.getXTileCount()][snake.getYTileCount()];
        lastTileState = new eTileTypes[snake.getXTileCount()][snake.getYTileCount()];
        //CGRect drawBounds = CoreGraphics.CGRectMake(0, 0, snake.getTileSize()*snake.getXTileCount(), snake.getTileSize()*snake.getYTileCount());
        UIView view = UIView.alloc().initWithFrame(gameBounds);
        //view.setBounds(gameBounds);
        view.removeFromSuperview();

        eTileTypes[][] grid = snake.getTileGrid();
        int size = snake.getTileSize();
        int numOfXTiles = snake.getXTileCount();
        int numOfYTiles = snake.getYTileCount();

        for(int i=0; i < numOfXTiles; i++){
            for(int j=0; j < numOfYTiles; j++){
                UIImageView tail = UIImageView.alloc().init();
                tail.setFrame(CoreGraphics.CGRectMake(i*size, j*size, size, size));
                tail.setImage(createTile(grid[i][j]));
                view.addSubview(tail);
                uiImageGrid[i][j] = tail;
                lastTileState[i][j] = grid[i][j];
            }
        }
        return view;
    }

    private  void reDrawSnakeTails()
    {
        eTileTypes[][] grid = snake.getTileGrid();
        UIImageView currentView;
        int numOfXTiles = snake.getXTileCount();
        int numOfYTiles = snake.getYTileCount();
        for(int i=0; i<numOfXTiles; i++){
            for(int j=0; j<numOfYTiles; j++){
                if(lastTileState[i][j] == grid[i][j] ) continue;
                currentView = uiImageGrid[i][j];
                currentView.setImage(createTile(grid[i][j]));
                lastTileState[i][j] = grid[i][j];
            }
        }
    }


    private static UIImage createTile(eTileTypes type){
        UIImage img = null;
        switch(type){
            case GREEN_STAR:
                img = greenStar;
                break;
            case YELLOW_STAR:
                img = yellowStar;
                break;
            case RED_STAR:
                img = redStar;
                break;
            case NULL_STAR:
                img = null;
                break;
//            case PAUSE:
//                img = pauseImg;
            default:
                break;
        }
        return img;
    }

    public boolean shouldAutorotate()
    {
        return false;
    }


    public boolean shouldAutorotateToInterfaceOrientation(long toInterfaceOrientation)
    {
        if (toInterfaceOrientation == UIInterfaceOrientation.Portrait){
            return true;
        }

        return false;
    }


    public long preferredInterfaceOrientationForPresentation()
    {
        return UIInterfaceOrientation.Portrait;
    }

    public long supportedInterfaceOrientations()
    {
        return UIInterfaceOrientation.Portrait;
    }

}
