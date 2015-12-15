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

package com.intel.libgdxmissilecommand.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static final float screenWidth = 800;
    public static final float screenHeight = 480;

    // Backgrounds
    public static TextureRegion backgroundRegionMenu;
    public static TextureRegion backgroundLoadGame1TextureRegion;
    public static TextureRegion backgroundLoadGame2TextureRegion;
    public static TextureRegion[] levelsBackgroundsTextureRegions = new TextureRegion[4];

    // Buttons
    public static TextureRegion backButtonTextureRegion;
    public static TextureRegion pauseButtonTextureRegion;
    public static TextureRegion startButtonTextureRegion;
    public static TextureRegion creditsButtonTextureRegion;

    // Player
    public static TextureRegion playerBaseTextureRegion;
    public static TextureRegion playerGunTextureRegion;

    // Missiles
    public static TextureRegion missileTextureRegion;
    public static Sound missileSound;

    // Explosions
    public static Animation explosionAnimation;
    public static Sound explosionSound;

    // Cities
    public static Animation citiesDestroyAnimation;
    public static TextureRegion[] citiesTextureRegions = new TextureRegion[6];

    // Naves
    public static TextureRegion navesTextureRegion;

    // Font file
    public static FileHandle fontFile;

    public static Sound alarmSound;


    private static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        Texture backgroundMenuTexture = loadTexture("Splash2.png");
        backgroundRegionMenu = new TextureRegion(backgroundMenuTexture, 0, 0, 800, 480);

        Texture backgroundLoadGame1Texture = loadTexture("aviso1.png");
        backgroundLoadGame1TextureRegion = new TextureRegion(backgroundLoadGame1Texture, 0, 0, 800, 480);

        Texture backgroundLoadGame2Texture = loadTexture("aviso2.png");
        backgroundLoadGame2TextureRegion = new TextureRegion(backgroundLoadGame2Texture, 0, 0, 800, 480);

        for (int i = 0; i < levelsBackgroundsTextureRegions.length; i++) {
            Texture backgroundLevelTexture = loadTexture("bg" + String.valueOf(i + 1) + ".png");
            levelsBackgroundsTextureRegions[i] = new TextureRegion(backgroundLevelTexture, 0, 0, 800, 480);
        }

        Texture startButtonTexture = loadTexture("btStart.png");
        startButtonTextureRegion = new TextureRegion(startButtonTexture, 0, 0, 120, 43);

        Texture creditsButtonTexture = loadTexture("btCredits.png");
        creditsButtonTextureRegion = new TextureRegion(creditsButtonTexture, 0, 0, 144, 43);

        Texture backButtonTexture = loadTexture("btBack.png");
        backButtonTextureRegion = new TextureRegion(backButtonTexture, 0, 0, 106, 43);

        Texture pauseButtonTexture = loadTexture("btPause.png");
        pauseButtonTextureRegion = new TextureRegion(pauseButtonTexture, 0, 0, 81, 22);

        Texture playerBaseTexture = loadTexture("canhaopart1.png");
        playerBaseTextureRegion = new TextureRegion(playerBaseTexture, 0, 0, 75, 45);

        Texture playerGunTexture = loadTexture("canhaopart2.png");
        playerGunTextureRegion = new TextureRegion(playerGunTexture, 0, 0, 8, 45);

        Texture missileTexture = loadTexture("missil.png");
        missileTextureRegion = new TextureRegion(missileTexture, 0, 0, 5, 9);

        for (int i = 0; i < citiesTextureRegions.length; i++) {
            Texture cityTexture = loadTexture("city" + String.valueOf(i + 1) + ".png");
            citiesTextureRegions[i] = new TextureRegion(cityTexture, 0, 0, 80, 80);
        }
        Texture citiesDestroy = loadTexture("cidade.png");
        citiesDestroyAnimation = new Animation(0.2f, new TextureRegion(citiesDestroy,2,53,51,59),
                new TextureRegion(citiesDestroy,55,2,51,15));

        fontFile = Gdx.files.internal("arial.fnt");

        alarmSound = Gdx.audio.newSound(Gdx.files.internal("alarm.mp3"));
        missileSound = Gdx.audio.newSound(Gdx.files.internal("missil.mp3"));

        Texture explosion = loadTexture("explosao1.png");
        explosionAnimation = new Animation(0.2f, new TextureRegion(explosion,8,8,26,22),
                new TextureRegion(explosion,35,8,40,38), new TextureRegion(explosion,76,8,60,56),
                new TextureRegion(explosion,137,8,74,76), new TextureRegion(explosion,8,85,74,68),
                new TextureRegion(explosion,83,85,72,68), new TextureRegion(explosion,156,85,68,62),
                new TextureRegion(explosion,8,154,62,60), new TextureRegion(explosion,71,154,58,56));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));

        Texture nave = loadTexture("naves.png");
        navesTextureRegion = new TextureRegion(nave, 12, 20);
    }

    public static void playSound(Sound sound) {
        sound.play(1);
    }
}
