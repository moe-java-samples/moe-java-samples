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

package com.intel.inde.moe.samples.openglbox.android;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import com.intel.inde.moe.samples.openglbox.common.Geometry;
import com.intel.inde.moe.samples.openglbox.common.Parameters;
import com.intel.inde.moe.samples.openglbox.common.Shaders;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BoxRenderer implements GLSurfaceView.Renderer {

    static final int FLOAT_SIZE = 4;

    private boolean isPaused = false;
    private long lastTime;

    private final ByteBuffer indices;

    private ShaderProgram shaderProgram = new ShaderProgram();

    private Bitmap bitmap;
    private int texture = -1;

    private int[] vertexBuffer = new int[1];
    private int[] indexBuffer= new int[1];

    private float rotation;

    private float[] modelMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private float[] projectionMatrix = new float[16];
    private float[] MVPMatrix = new float[16];

    public BoxRenderer() {
        indices = ByteBuffer.allocateDirect(Geometry.INDICES.length).order(ByteOrder.nativeOrder());
        indices.put(Geometry.INDICES).position(0);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background color
        GLES20.glClearColor(Parameters.BACKGROUND[0],
                Parameters.BACKGROUND[1],
                Parameters.BACKGROUND[2],
                Parameters.BACKGROUND[3]);

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);

        shaderProgram.create(Shaders.VERTEXT_SHADER, Shaders.FRAGMENT_SHADER);

        Matrix.setLookAtM(viewMatrix, 0,
                0.0f, 0.0f, Parameters.EYE_Z, // eye
                0.0f, 0.0f, 0.0f, // center
                0.0f, 1.0f, 0.0f // up-vector
        );
        Matrix.rotateM(viewMatrix, 0, Parameters.PITCH, 1, 0, 0);

        texture = loadGLTexture(bitmap);

        setupGeometry();

        lastTime = System.nanoTime();
    }

    private void setupGeometry() {
        GLES20.glGenBuffers(1, vertexBuffer, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBuffer[0]);
        int sizeOfVertices = Geometry.VERTICES.length * FLOAT_SIZE;
        FloatBuffer vertices = ByteBuffer.allocateDirect(sizeOfVertices).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertices.put(Geometry.VERTICES).position(0);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, sizeOfVertices, vertices,
                GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        GLES20.glGenBuffers(1, indexBuffer, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer[0]);
        int sizeOfIndices = Geometry.INDICES.length;
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, sizeOfIndices,
                indices, GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void onDrawFrame(GL10 unused) {
        update();
        drawBox();
    }

    double timeSinceLastUpdate() {
        long time = System.nanoTime();
        double delta = (double) (time - lastTime) / 1000000000;
        lastTime = time;
        return delta;
    }

    private void update() {
        if (isPaused)
            return;

        rotation += Parameters.DEGREES_PER_SECOND * timeSinceLastUpdate();

        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.rotateM(modelMatrix, 0, rotation, 0, 1, 0);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        final float aspectRatio = (float) width / height;

        Matrix.perspectiveM(projectionMatrix,
                0, // offset
                Parameters.FOV_Y,
                aspectRatio,
                Parameters.Z_NEAR,
                Parameters.Z_FAR
        );
    }

    private void drawBox()
    {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        shaderProgram.use();

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);

        Matrix.multiplyMM(MVPMatrix, 0, viewMatrix, 0, modelMatrix, 0);
        Matrix.multiplyMM(MVPMatrix, 0, projectionMatrix, 0, MVPMatrix, 0);

        int uMVPMatrix = shaderProgram.getUniformLocation("uMVPMatrix");
        GLES20.glUniformMatrix4fv(uMVPMatrix, 1, false, MVPMatrix, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertexBuffer[0]);

        int sizeOfVertex = (3 + 2) * FLOAT_SIZE;

        int aPosition = shaderProgram.getAttributeLocation("aPosition");
        GLES20.glEnableVertexAttribArray(aPosition);
        GLES20.glVertexAttribPointer(aPosition, 3,
                GLES20.GL_FLOAT, false, sizeOfVertex, 0);

        int aTexCoord = shaderProgram.getAttributeLocation("aTexCoord");
        GLES20.glEnableVertexAttribArray(aTexCoord);
        GLES20.glVertexAttribPointer(aTexCoord, 2,
                GLES20.GL_FLOAT, false, sizeOfVertex, 3 * FLOAT_SIZE);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer[0]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, Geometry.INDICES.length, GLES20.GL_UNSIGNED_BYTE, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        shaderProgram.unUse();
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static int loadGLTexture(Bitmap bitmap) {
        // Generate one texture pointer
        int[] textureIDs = new int[1];
        GLES20.glGenTextures(1, textureIDs, 0);

        // Bind this texture
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIDs[0]);

        // Create Nearest Filtered Texture
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);

        // Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

        return textureIDs[0];
    }

    void togglePaused() {
        isPaused = !isPaused;
        if (!isPaused) {
            lastTime = System.nanoTime();
        }
    }
}
