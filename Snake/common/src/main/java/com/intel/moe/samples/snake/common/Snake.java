package com.intel.moe.samples.snake.common;

import java.security.SecureRandom;
import java.util.ArrayList;


public class Snake extends Tile{
	
	public Snake(int w, int h, int step){
		mTileSize = step;
		onSizeChanged(w, h);
	}
    
    private eMode mMode = eMode.READY;
    public void setMode(eMode mode){
    	mMode = mode;
    }

    private eDirection mDirection = eDirection.NORTH;
    private eDirection mNextDirection = eDirection.NORTH;
    private long mScore = 0;
    private long mMoveDelay = 600;
    private long mLastMove;
    private ArrayList<Coordinate> mSnakeTrail = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> mAppleList = new ArrayList<Coordinate>();

    private static final SecureRandom RNG = new SecureRandom();

    public void initNewGame() {
        mSnakeTrail.clear();
        mAppleList.clear();

        mSnakeTrail.add(new Coordinate(7, 7));
        mSnakeTrail.add(new Coordinate(6, 7));
        mSnakeTrail.add(new Coordinate(5, 7));
        mSnakeTrail.add(new Coordinate(4, 7));
        mSnakeTrail.add(new Coordinate(3, 7));
        mSnakeTrail.add(new Coordinate(2, 7));
        mNextDirection = eDirection.NORTH;

        addRandomApple();

        mMoveDelay = 600;
        mScore = 0;
        
        setMode(eMode.RUNNING);

        update();
    }


    //AI logic for snake to move self without player
    public void moveSelf()
    {
        boolean withWall = false;
        Coordinate head = mSnakeTrail.get(0);
        Coordinate newHead;
        eDirection newDirection = null;

        newHead = getNewHead(head, mDirection);

        if (newHead == null)
            return;

        // Look for apples
        int applecount = mAppleList.size();

        for (int appleindex = 0; appleindex < applecount; appleindex++) {
            Coordinate c = mAppleList.get(appleindex);
            if (c.x > newHead.x && (mDirection == eDirection.NORTH ||  mDirection == eDirection.SOUTH)) {
                 newHead = getNewHead(head, eDirection.EAST);
                 newDirection = eDirection.EAST;
                 //moveSnake(eMoveDirection.MOVE_RIGHT);
            } else if (c.x < newHead.x && (mDirection == eDirection.NORTH ||  mDirection == eDirection.SOUTH)) {
                newHead = getNewHead(head, eDirection.WEST);
                newDirection = eDirection.WEST;
               // moveSnake(eMoveDirection.MOVE_LEFT);
            } else if (c.y > newHead.y && (mDirection == eDirection.EAST || mDirection == eDirection.WEST)) {
                newHead = getNewHead(head, eDirection.SOUTH);
                newDirection = eDirection.SOUTH;
                //moveSnake(eMoveDirection.MOVE_DOWN);
            } else if (c.y < newHead.y && (mDirection == eDirection.EAST || mDirection == eDirection.WEST)) {
                newHead = getNewHead(head, eDirection.NORTH);
                newDirection = eDirection.NORTH;
               // moveSnake(eMoveDirection.MOVE_UP);
            } else {
                newDirection = mDirection;
            }

            if (newHead == null)
                return;
        }

        if ((newHead.x < 1) || (newHead.y < 1) || (newHead.x > mXTileCount - 2)
                || (newHead.y > mYTileCount - 2)) {
            if(mDirection == eDirection.EAST || mDirection == eDirection.WEST)
            {
                newDirection = eDirection.NORTH;
                newHead = getNewHead(head, eDirection.NORTH);
            }
            else
            {
                newHead = getNewHead(head, eDirection.WEST);
                newDirection = eDirection.WEST;
            }
            withWall = true;
        }

        if (newHead == null)
            return;

        // Look for collisions with itself
        int snakelength = mSnakeTrail.size();
        for (int snakeindex = 0; snakeindex < snakelength; snakeindex++) {
            Coordinate c = mSnakeTrail.get(snakeindex);
            if (c.equals(newHead)) {
                if (withWall) {
                    if (mDirection == eDirection.EAST || mDirection == eDirection.WEST)
                        moveSnake(eMoveDirection.MOVE_DOWN);
                    else moveSnake(eMoveDirection.MOVE_RIGHT);
                } else {
                    if (mDirection == eDirection.EAST || mDirection == eDirection.WEST)
                        moveSnake(eMoveDirection.MOVE_UP);
                    else moveSnake(eMoveDirection.MOVE_LEFT);
                }
                return;
            } else if(withWall) {
                if (mDirection == eDirection.EAST || mDirection == eDirection.WEST)
                    moveSnake(eMoveDirection.MOVE_UP);
                else moveSnake(eMoveDirection.MOVE_LEFT);
                return;
            }
        }

        switch(newDirection){
         case EAST: {
             moveSnake(eMoveDirection.MOVE_RIGHT);
             break;
         }
          case WEST: {
              moveSnake(eMoveDirection.MOVE_LEFT);
             break;
          }
          case NORTH: {
              moveSnake(eMoveDirection.MOVE_UP);
              break;
          }
          case SOUTH: {
              moveSnake(eMoveDirection.MOVE_DOWN);
              break;
         }
        }


    }

    public eDirection opossiteDirection(eDirection direction)
    {
        switch (direction) {
            case EAST: {
                direction = eDirection.WEST;
                break;
            }
            case WEST: {
                direction = eDirection.EAST;
                break;
            }
            case NORTH: {
                direction = eDirection.SOUTH;
                break;
            }
            case SOUTH: {
                direction = eDirection.NORTH;
                break;
            }
        }
        return direction;
    }

    public Coordinate getNewHead(Coordinate currentHead, eDirection newDirection)
    {
        Coordinate newHead = null;
        switch (newDirection) {
            case EAST: {
                newHead = new Coordinate(currentHead.x + 1, currentHead.y);
                break;
            }
            case WEST: {
                newHead = new Coordinate(currentHead.x - 1, currentHead.y);
                break;
            }
            case NORTH: {
                newHead = new Coordinate(currentHead.x, currentHead.y - 1);
                break;
            }
            case SOUTH: {
                newHead = new Coordinate(currentHead.x, currentHead.y + 1);
                break;
            }
        }
        return newHead;
    }
   
    public void moveSnake(eMoveDirection direction) {

        if (direction == eMoveDirection.MOVE_UP) {

            if (mMode == eMode.PAUSE) {
                /*
                 * If the game is merely paused, we should just continue where we left off.
                 */
                setMode(eMode.RUNNING);
                update();
                return;
            }

            if (mDirection != eDirection.SOUTH) {
                mNextDirection = eDirection.NORTH;
            }
            return;
        }

        if (direction == eMoveDirection.MOVE_DOWN) {
            if (mDirection != eDirection.NORTH) {
                mNextDirection = eDirection.SOUTH;
            }
            return;
        }

        if (direction == eMoveDirection.MOVE_LEFT) {
            if (mDirection != eDirection.EAST) {
                mNextDirection = eDirection.WEST;
            }
            return;
        }

        if (direction == eMoveDirection.MOVE_RIGHT) {
            if (mDirection != eDirection.WEST) {
                mNextDirection = eDirection.EAST;
            }
            return;
        }
    }

    public long getMoveDelay() {
        return mMoveDelay;
    }

    public eMode getGameState() {
        return mMode;
    }

    private void addRandomApple() {
        if(mAppleList  != null && !mAppleList.isEmpty()) return;
        Coordinate newCoord = null;
        boolean found = false;
        while (!found) {

            int newX = 1 + RNG.nextInt(mXTileCount - 2);
            int newY = 1 + RNG.nextInt(mYTileCount - 2);
            newCoord = new Coordinate(newX, newY);

           
            boolean collision = false;
            int snakelength = mSnakeTrail.size();
            for (int index = 0; index < snakelength; index++) {
                if (mSnakeTrail.get(index).equals(newCoord)) {
                    collision = true;
                }
            }
     
            found = !collision;
        }
        if (newCoord == null) {
           System.out.print("WHHHHTTTT New coor is null");
        }
        mAppleList.add(newCoord);
    }

  
    public void update() {
                clearAllTiles();
                updateWalls();
                updateSnake();
                updateApples();

    }
    
    public void updateSnakeOnly() {
        if (mMode == eMode.RUNNING) {
            long now = System.currentTimeMillis();

            if (now - mLastMove > mMoveDelay) {
            	for(Coordinate cor : mSnakeTrail)
            	{
            		clearTile(cor.x, cor.y);
            	}
                updateSnake();

//                for(Coordinate cor : mAppleList)
//                {
//                    clearTile(cor.x, cor.y);
//                }
                updateApples();

                mLastMove = now;
            }
            //mRedrawHandler.sleep(mMoveDelay);
        }

    }

  
    private void updateWalls() {
        for (int x = 0; x < mXTileCount; x++) {
            setTile(eTileTypes.GREEN_STAR, x, 0);
            setTile(eTileTypes.GREEN_STAR, x, mYTileCount - 1);
        }
        for (int y = 1; y < mYTileCount - 1; y++) {
            setTile(eTileTypes.GREEN_STAR, 0, y);
            setTile(eTileTypes.GREEN_STAR, mXTileCount - 1, y);
        }

      //  setTile(eTileTypes.PAUSE, mXTileCount - 1, mYTileCount - 1);
    }

   
    private void updateApples() {
        boolean firstAppleUpdated = false;
        for (Coordinate c : mAppleList) {
            if(!firstAppleUpdated) {
                setTile(eTileTypes.YELLOW_STAR, c.x, c.y);
                firstAppleUpdated = true;
            }
            else
            {
                setTile(eTileTypes.NULL_STAR, c.x, c.y);

            }

        }
    }

    
    private void updateSnake() {
        boolean growSnake = false;

        // Grab the snake by the head
        Coordinate head = mSnakeTrail.get(0);
        Coordinate newHead = null;

        mDirection = mNextDirection;

        switch (mDirection) {
            case EAST: {
                newHead = new Coordinate(head.x + 1, head.y);
                break;
            }
            case WEST: {
                newHead = new Coordinate(head.x - 1, head.y);
                break;
            }
            case NORTH: {
                newHead = new Coordinate(head.x, head.y - 1);
                break;
            }
            case SOUTH: {
                newHead = new Coordinate(head.x, head.y + 1);
                break;
            }
        }

        if (newHead == null)
            return;

        // Collision detection
        // For now we have a 1-square wall around the entire arena
        if ((newHead.x < 1) || (newHead.y < 1) || (newHead.x > mXTileCount - 2)
                || (newHead.y > mYTileCount - 2)) {
            setMode(eMode.LOSE);
            return;

        }

        // Look for collisions with itself
        int snakelength = mSnakeTrail.size();
        for (int snakeindex = 0; snakeindex < snakelength; snakeindex++) {
            Coordinate c = mSnakeTrail.get(snakeindex);
            if (c.equals(newHead)) {
                setMode(eMode.LOSE);
                return;
            }
        }

        // Look for apples
        int applecount = mAppleList.size();
        for (int appleindex = 0; appleindex < applecount; appleindex++) {
            Coordinate c = mAppleList.get(appleindex);
            if (c.equals(newHead)) {
                mAppleList.remove(c);
                addRandomApple();
                updateApples();
                mScore++;
                mMoveDelay *= 0.9;

                growSnake = true;
            }
        }

        // push a new head onto the ArrayList and pull off the tail
        mSnakeTrail.add(0, newHead);
        // except if we want the snake to grow
        if (!growSnake) {
            mSnakeTrail.remove(mSnakeTrail.size() - 1);
        }

        int index = 0;
        for (Coordinate c : mSnakeTrail) {
            if (index == 0) {
                setTile(eTileTypes.YELLOW_STAR, c.x, c.y);
            } else {
                setTile(eTileTypes.RED_STAR, c.x, c.y);
            }
            index++;
        }

    }
  
    private class Coordinate {
        public int x;
        public int y;

        public Coordinate(int newX, int newY) {
            x = newX;
            y = newY;
        }

        public boolean equals(Coordinate other) {
            if (x == other.x && y == other.y) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Coordinate: [" + x + "," + y + "]";
        }
    }
}
