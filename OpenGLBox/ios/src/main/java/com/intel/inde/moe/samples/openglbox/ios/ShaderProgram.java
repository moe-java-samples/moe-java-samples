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

package com.intel.inde.moe.samples.openglbox.ios;

import com.intel.inde.moe.natj.general.ptr.BytePtr;
import com.intel.inde.moe.natj.general.ptr.IntPtr;
import com.intel.inde.moe.natj.general.ptr.impl.PtrFactory;
import com.intel.inde.moe.samples.openglbox.common.ShaderProgramBase;

import ios.opengles.c.OpenGLES;
import ios.opengles.enums.ES2;

public class ShaderProgram extends ShaderProgramBase {

    public ShaderProgram() {
    }

    @Override
    public void create(String vertexCode, String fragmentCode) {
        int vertexShader = INVALID_VALUE;
        if (vertexCode != null) {
            vertexShader = loadShader(ES2.VERTEX_SHADER, vertexCode);
        }
        if (vertexShader == 0) {
            programHandle = 0;
            return;
        }

        int fragmentShader = INVALID_VALUE;
        if (fragmentCode != null) {
            fragmentShader = loadShader(ES2.FRAGMENT_SHADER, fragmentCode);
        }
        if (fragmentShader == 0) {
            programHandle = 0;
            return;
        }

        programHandle = OpenGLES.glCreateProgram();

        OpenGLES.glAttachShader(programHandle, vertexShader);
        OpenGLES.glAttachShader(programHandle, fragmentShader);
        OpenGLES.glLinkProgram(programHandle);
    }

    @Override
    public void use() {
        OpenGLES.glUseProgram(programHandle);
    }

    @Override
    public void unUse() {
        OpenGLES.glUseProgram(0);
    }

    @Override
    protected int loadShader(int type, String shaderCode) {
        int shader = OpenGLES.glCreateShader(type);

        String[] source = new String[1];
        source[0] = shaderCode;
        OpenGLES.glShaderSource(shader, 1, source, null);

        OpenGLES.glCompileShader(shader);

        IntPtr status = PtrFactory.newIntReference();
        OpenGLES.glGetShaderiv(shader, ES2.COMPILE_STATUS, status);
        if (status.getValue() == 0) {
            BytePtr info = PtrFactory.newWeakByteArray(256);
            OpenGLES.glGetShaderInfoLog(shader, 256, PtrFactory.newWeakIntReference(0), info);
            OpenGLES.glDeleteShader(shader);
            throw new IllegalArgumentException("Shader compilation failed with: " + info.toASCIIString());
        }

        return shader;
    }

    @Override
    public int getAttributeLocation(String attribute) {
        if (attributes.containsKey(attribute)) {
            return attributes.get(attribute);
        }
        int location = OpenGLES.glGetAttribLocation(programHandle, attribute);
        if (location == -1) {
            throw new IllegalStateException("Can't find a location for attribute " + attribute);
        }
        attributes.put(attribute, location);
        return location;
    }

    @Override
    public int getUniformLocation(String uniform) {
        if (attributes.containsKey(uniform)) {
            return attributes.get(uniform);
        }
        int location = OpenGLES.glGetUniformLocation(programHandle, uniform);
        if (location == -1) {
            throw new IllegalStateException("Can't find a location for uniform " + uniform);
        }
        attributes.put(uniform, location);
        return location;
    }
}
