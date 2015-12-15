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

package com.intel.libgdxmissilecommand.common.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.intel.libgdxmissilecommand.common.Assets;

import java.security.SecureRandom;


public class Nave extends GameObject {

    private float velX = 0, velY = 0;

    public Nave(int wave) {
        super(Assets.navesTextureRegion);

        SecureRandom random = new SecureRandom();

        int posX = random.nextInt((int) Assets.screenWidth);

        setPosition(posX, Assets.screenHeight);
        float angle;
        if (posX < 400) {
            angle = -random.nextFloat() * 45;
            velX = (float) (Math.sin(angle * Math.PI/180) * (wave / 2));
            velY = (float) (Math.cos(angle * Math.PI/180) * (wave / 2));
        } else {
            angle = random.nextFloat() * 45;
            velX = (float) (Math.sin(angle * Math.PI/180) * (wave / 2));
            velY = (float) (Math.cos(angle * Math.PI/180) * (wave / 2));
        }

        setRotation(-angle);
    }

    @Override
    public void update(float deltaTime) {
        if (getY() < 30 || explosionTime >= 0) {
            explosionTime += deltaTime;
            return;
        }
        setPosition(getX() - velX, getY() - velY);
    }

    @Override
    public void draw(Batch batch) {
        if (getY() < 30 || Float.compare(explosionTime, -1) != 0) {
            if (explosionTime < 0) {
                explosionTime = 0;
                Assets.playSound(Assets.explosionSound);
            }
            TextureRegion keyFrame = Assets.explosionAnimation.getKeyFrame(explosionTime, false);
            batch.draw(keyFrame, getX() - 74 / 2, getY() - 76 / 2, 74, 76);
            if (Assets.explosionAnimation.isAnimationFinished(explosionTime))
                alive = false;
        } else {
            super.draw(batch);
        }
    }
}
