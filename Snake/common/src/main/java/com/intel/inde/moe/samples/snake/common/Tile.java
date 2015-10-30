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

package com.intel.inde.moe.samples.snake.common;

public class Tile {
	
    protected static int mTileSize;
    public int GetTileSize(){
    	return mTileSize;
    }
    public void SetTileSize(int TileSize) {
        mTileSize = TileSize;
    }

    protected static int mXTileCount;
    public int GetXTileCount(){
    	return mXTileCount;
    }
    public void SetXTileCount(int XTileCount) {
        mXTileCount = XTileCount;
    }
    
    protected static int mYTileCount;
    public int GetYTileCount(){
    	return mYTileCount;
    }
    public void SetYTileCount(int YTileCount) {
        mYTileCount = YTileCount;
    }

    private static int mXOffset;
    public int GetXOffset(){
    	return mXOffset;
    }
    public void SetXOffset(int XOffset) {
        mXOffset = XOffset;
    }
    private static int mYOffset;
    public int GetYOffset(){
    	return mYOffset;
    }
    public void SetYOffset(int YOffset) {
        mYOffset = YOffset;
    }

    private eTileTypes[][] mTileGrid;
    public eTileTypes[][] GetTileGrid(){
    	return mTileGrid;
    }

    public void clearAllTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
                setTile(eTileTypes.NULL_STAR, x, y);
            }
        }
    }
    
    public void clearTile(int x, int y) {
       setTile(eTileTypes.NULL_STAR, x, y);
    }

    public void setTile(eTileTypes tileindex, int x, int y) {
        mTileGrid[x][y] = tileindex;
    }

    protected void onSizeChanged(int w, int h) {
        mXTileCount = (int) Math.floor(w / mTileSize);
        mYTileCount = (int) Math.floor(h / mTileSize);

        mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
        mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

        mTileGrid = new eTileTypes[mXTileCount][mYTileCount];
    }
}
