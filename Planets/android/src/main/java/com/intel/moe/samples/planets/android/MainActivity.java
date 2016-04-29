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

package com.intel.moe.samples.planets.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.intel.moe.samples.planets.common.Planet;
import com.intel.moe.samples.planets.common.Simulation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CoreGraphicsBackend(this));
    }

    public class CoreGraphicsBackend extends View {
        private Simulation simulation = null;

        public CoreGraphicsBackend(Context context) {
            super(context);
            movePlanetsRunnable.run();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLACK);
            canvas.drawPaint(paint);
            if (simulation == null)
                return;
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#ffa505"));
            for (Planet p : simulation.getPlanets()) {
                canvas.drawCircle((float) (p.getLocation().getX()), (float) (p.getLocation().getY()), (float) p.getRadius(), paint);
            }
        }

        Handler handler = new Handler(Looper.getMainLooper());
        Runnable movePlanetsRunnable = new Runnable() {
            public void run(){
                if (getWidth() != 0 && getHeight() != 0) {
                    if (simulation == null) {
                        Planet.MAX_LOCATION.setSize(getWidth(), getHeight());
                        simulation = new Simulation(30);
                    }
                    simulation.tick(1.0 / 30.0);
                }
                invalidate(); //will trigger the onDraw
                handler.postDelayed(this, 1000 / 30); //in 5 sec player0 will move again
            }
        };
    }

}
