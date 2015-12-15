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

package com.intel.libgdxmissilecommand.common.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.intel.libgdxmissilecommand.common.Assets;
import com.intel.libgdxmissilecommand.common.MissileCommand;
import com.intel.libgdxmissilecommand.common.models.City;
import com.intel.libgdxmissilecommand.common.models.GameObject;
import com.intel.libgdxmissilecommand.common.models.Missile;
import com.intel.libgdxmissilecommand.common.models.Nave;
import com.intel.libgdxmissilecommand.common.models.Player;

import java.util.ArrayList;

public class MissileCommandGameScreen extends ScreenAdapter {

    private MissileCommand game;
    private OrthographicCamera guiCam;
    private BitmapFont fontTime;
    private BitmapFont fontTitle;
    private int levelBackgroung = 0;
    private long lastAttackTime = 0;
    private float time = 0;
    private boolean playing = true;
    private Sprite pauseButton;
    private Sprite backButton;
    private Vector3 touchPoint;
    private int wave = 1;
    private long verNaves = 0;
    private long score = 0;
    private ArrayList<Missile> missiles = new ArrayList<>();

    private ArrayList<Nave> naves = new ArrayList<>();

    City[] cities = new City[6];

    Player player;

    public MissileCommandGameScreen(MissileCommand game) {
        this.game = game;

        fontTime = new BitmapFont(Assets.fontFile);
        fontTime.getData().setScale(0.5f);
        fontTitle = new BitmapFont(Assets.fontFile);
        fontTitle.getData().setScale(0.7f);

        guiCam = new OrthographicCamera(Assets.screenWidth, Assets.screenHeight);
        guiCam.position.set(Assets.screenWidth / 2, Assets.screenHeight / 2, 0);

        pauseButton = new Sprite(Assets.pauseButtonTextureRegion);
        pauseButton.setPosition(Assets.screenWidth - (pauseButton.getWidth() + 2), 5);
        backButton = new Sprite(Assets.backButtonTextureRegion);
        backButton.setPosition(Assets.screenWidth - (backButton.getWidth() + 2), 2);
        touchPoint = new Vector3();
        player = new Player();
        player.setPosition(Assets.screenWidth / 2, Assets.screenHeight / 24);

        City.resetCounter();

        for (int i = 0; i < cities.length; i++) {
            cities[i] = new City();
        }
    }

    private void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();

        game.batcher.setProjectionMatrix(guiCam.combined);

        game.batcher.begin();
        game.batcher.draw(Assets.levelsBackgroundsTextureRegions[levelBackgroung], 0, 0, Assets.screenWidth, Assets.screenHeight);
        game.batcher.end();

        game.batcher.enableBlending();

        if (playing)
            checkCollisions();

        game.batcher.begin();

        float dt = Gdx.graphics.getDeltaTime();
        for (City city : cities) {
            if (playing)
                city.update(dt);
            city.draw(game.batcher);
        }
        if (playing) {
            time -= dt;
            if (time > 20 / wave && (System.currentTimeMillis() - verNaves > 5000) && City.getCityCounter() > 0) {
                addNaves();
            }
        }

        int printTime = (int) time;
        if (printTime < 0)
            printTime = 0;
        fontTime.draw(game.batcher, "Time: " + String.valueOf(printTime) + "s",
                Assets.screenWidth - 150, Assets.screenHeight - 25);
        fontTime.draw(game.batcher, "Score: " + String.valueOf(score),
                2, Assets.screenHeight - 25);

        for(int i = 0; i < missiles.size(); i++) {
            Missile missile = missiles.get(i);
            if (missile.isAlive()) {
                if (playing)
                    missile.update(dt);
                missile.draw(game.batcher);
            } else {
                missiles.remove(i);
                i--;
            }
        }

        player.draw(game.batcher);

        for(int i = 0; i < naves.size(); i++) {
            Nave nave = naves.get(i);
            if (nave.isAlive()) {
                if (playing)
                    nave.update(dt);
                nave.draw(game.batcher);
            } else {
                naves.remove(i);
                i--;
            }
        }

        if (playing) {
            if (time <= 0) {
                fontTitle.draw(game.batcher, "Wave " + String.valueOf(wave) +
                        " Starts in " + String.valueOf((int)(3 + time)) + "s",
                        230, 240);
                if (time < -3) {
                    time = 60;
                    wave++;
                    levelBackgroung = (wave - 2) % 4;
                }
            }
            pauseButton.draw(game.batcher);
        } else {
            if (City.getCityCounter() > 0) {
                fontTitle.draw(game.batcher, "Touch the screen to resuming the game",
                        100, 320 + fontTitle.getLineHeight());
                fontTitle.draw(game.batcher, "Or press back button to exit in the main menu",
                        70, 320 - fontTitle.getLineHeight());
            } else {
                fontTitle.draw(game.batcher, "You Lose!",
                        320, 320 + fontTitle.getLineHeight());
                fontTitle.draw(game.batcher, "Score: " + String.valueOf(score),
                        320, 320 - fontTitle.getLineHeight());
            }
            backButton.draw(game.batcher);
        }
        fontTime.draw(game.batcher, "fps: " + Gdx.graphics.getFramesPerSecond(), 0, 0);

        game.batcher.end();
    }

    private void update() {
        if (City.getCityCounter() <= 0) {
            playing = false;
        }

        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (pauseButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y) && playing) {
                playing = false;
                return;
            }

            if (backButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new MainMenuScreen(game));
                return;
            }
            if (!playing) {
                if (City.getCityCounter() > 0)
                    playing = true;
                return;
            }

            if (System.currentTimeMillis() - lastAttackTime > 450 && touchPoint.y >= player.getY()) {
                lastAttackTime = System.currentTimeMillis();
                missiles.add(new Missile());
                missiles.get(missiles.size() - 1).detectTarget(touchPoint.x, touchPoint.y);
                player.setRotation(missiles.get(missiles.size() - 1).getRotation());
            }
        }
    }

    private void addNaves() {
        naves.add(new Nave(wave));
        naves.add(new Nave(wave));
        naves.add(new Nave(wave));
        verNaves = System.currentTimeMillis();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    private void checkCollisions() {
        for(int i = 0; i < naves.size(); i++) {
            for (City city : cities) {
                if (isCollision(naves.get(i), city)) {
                    naves.get(i).kill();
                    city.kill();
                }
            }

            for(int j = 0; j < missiles.size(); j++) {
                if (isCollision(naves.get(i), missiles.get(j)) && naves.get(i).kill()) {
                    score++;
                    missiles.get(j).kill();
                }
            }
        }
    }

    private <T extends GameObject> boolean isCollision(T obj1, T obj2) {
        Rectangle r1 = obj1.collisionRectangle();
        Rectangle r2 = obj2.collisionRectangle();
        return !(r1 == null || r2 == null) && r2.overlaps(r1);
    }

    @Override
    public void pause () {
        //Settings.save();
    }
}