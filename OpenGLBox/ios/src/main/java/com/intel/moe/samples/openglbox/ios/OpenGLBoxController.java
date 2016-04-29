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

package com.intel.moe.samples.openglbox.ios;

import com.intel.moe.natj.c.CRuntime;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.ByValue;
import com.intel.moe.natj.general.ann.Mapped;
import com.intel.moe.natj.general.ptr.BytePtr;
import com.intel.moe.natj.general.ptr.FloatPtr;
import com.intel.moe.natj.general.ptr.IntPtr;
import com.intel.moe.natj.general.ptr.impl.PtrFactory;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.natj.objc.map.ObjCObjectMapper;
import com.intel.moe.samples.openglbox.common.Geometry;
import com.intel.moe.samples.openglbox.common.Parameters;
import com.intel.moe.samples.openglbox.common.Shaders;

import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSBundle;
import ios.foundation.NSMutableDictionary;
import ios.foundation.NSNumber;
import ios.foundation.NSSet;
import ios.glkit.GLKTextureInfo;
import ios.glkit.GLKTextureLoader;
import ios.glkit.GLKView;
import ios.glkit.GLKViewController;
import ios.glkit.c.GLKit;
import ios.glkit.enums.GLKViewDrawableDepthFormat;
import ios.glkit.protocol.GLKViewDelegate;
import ios.glkit.struct.GLKMatrix4;
import ios.opengles.EAGLContext;
import ios.opengles.c.OpenGLES;
import ios.opengles.enums.EAGLRenderingAPI;
import ios.opengles.enums.ES2;
import ios.uikit.UIEvent;
import ios.uikit.enums.UIRectEdge;

public class OpenGLBoxController extends GLKViewController implements
		GLKViewDelegate {

	protected OpenGLBoxController(Pointer peer) {
		super(peer);
	}

	public static native OpenGLBoxController alloc();

	@Override
	public native OpenGLBoxController init();

	static final int GLKViewDrawableMultisample4X = 1;

    private EAGLContext context;

    private final BytePtr indices = PtrFactory.newByteArray(Geometry.INDICES);

    private ShaderProgram shaderProgram = new ShaderProgram();

    private int texture = -1;

    private IntPtr vertexBuffer = PtrFactory.newIntReference();
    private IntPtr indexBuffer = PtrFactory.newIntReference();
    private IntPtr vertexArray = PtrFactory.newIntReference();

    private float rotation;

    private GLKMatrix4 modelMatrix = GLKit.GLKMatrix4Identity();
    private GLKMatrix4 viewMatrix;
    private GLKMatrix4 projectionMatrix;
    private GLKMatrix4 MVPMatrix;

    private GLKView glkView;

    @Override
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("OpenGLBox");

		setEdgesForExtendedLayout(UIRectEdge.None);

		context = EAGLContext.alloc().initWithAPI(EAGLRenderingAPI.GLES2);
		if (context == null) {
			System.out.println("Failed to create ES context");
		}
		glkView = (GLKView) view();
		glkView.setContext(context);
		glkView.setDrawableMultisample(GLKViewDrawableMultisample4X);
        glkView.setDrawableDepthFormat(GLKViewDrawableDepthFormat.Format16);
		setupGL();
	}

    @Override
    public void viewWillTransitionToSizeWithTransitionCoordinator(@ByValue CGSize size, @Mapped(ObjCObjectMapper.class) Object coordinator) {
        if (isPaused())
            glkView.setNeedsDisplay();
    }

	@Override
	public void viewDidDisappear(boolean animated) {
		super.viewDidDisappear(animated);
		tearDownGL();
		if (EAGLContext.currentContext() != null && EAGLContext.currentContext().equals(context)) {
			EAGLContext.setCurrentContext(null);
		}
		context = null;
	}

	@Override
	public void didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning();
		if (isViewLoaded() && (view().window() == null)) {
			setView(null);
			tearDownGL();
			if (EAGLContext.currentContext().equals(context)) {
				EAGLContext.setCurrentContext(null);
			}
			context = null;
		}
		// Dispose of any resources that can be recreated.
	}

	private void setupGL() {
		EAGLContext.setCurrentContext(context);

        // Set the background color
        OpenGLES.glClearColor(Parameters.BACKGROUND[0],
                Parameters.BACKGROUND[1],
                Parameters.BACKGROUND[2],
                Parameters.BACKGROUND[3]);

        OpenGLES.glEnable(ES2.DEPTH_TEST);
        OpenGLES.glEnable(ES2.CULL_FACE);

        shaderProgram.create(Shaders.VERTEXT_SHADER, Shaders.FRAGMENT_SHADER);

        viewMatrix = GLKit.GLKMatrix4MakeLookAt(
                0.0f, 0.0f, Parameters.EYE_Z, // eye
                0.0f, 0.0f, 0.0f, // center
                0.0f, 1.0f, 0.0f // up-vector
        );
        viewMatrix = GLKit.GLKMatrix4Rotate(viewMatrix,
                GLKit.GLKMathDegreesToRadians(Parameters.PITCH), 1, 0, 0);

        texture = loadGLTexture("intel_logo_square", "png");

		setupGeometry();
	}

	private void setupGeometry() {
		OpenGLES.glGenVertexArraysOES(1, vertexArray);
		OpenGLES.glBindVertexArrayOES(vertexArray.getValue());

		OpenGLES.glGenBuffers(1, vertexBuffer);
		OpenGLES.glBindBuffer(ES2.ARRAY_BUFFER, vertexBuffer.getValue());
		FloatPtr vertices = PtrFactory.newFloatArray(Geometry.VERTICES);
		int sizeOfVertices = Geometry.VERTICES.length * CRuntime.FLOAT_SIZE;
		OpenGLES.glBufferData(ES2.ARRAY_BUFFER, sizeOfVertices, vertices,
                ES2.STATIC_DRAW);

		OpenGLES.glGenBuffers(1, indexBuffer);
		OpenGLES.glBindBuffer(ES2.ELEMENT_ARRAY_BUFFER, indexBuffer.getValue());
		int sizeOfIndices = Geometry.INDICES.length;
		OpenGLES.glBufferData(ES2.ELEMENT_ARRAY_BUFFER, sizeOfIndices,
				indices, ES2.STATIC_DRAW);

        int sizeOfVertex = (3 + 2) * CRuntime.FLOAT_SIZE;

        int aPosition = shaderProgram.getAttributeLocation("aPosition");
		OpenGLES.glEnableVertexAttribArray(aPosition);
		OpenGLES.glVertexAttribPointer(aPosition, 3,
                ES2.FLOAT, (byte) ES2.FALSE, sizeOfVertex,
                PtrFactory.newWeakVoidPtr(0));

		int aTexCoord = shaderProgram.getAttributeLocation("aTexCoord");
		OpenGLES.glEnableVertexAttribArray(aTexCoord);
		OpenGLES.glVertexAttribPointer(aTexCoord, 2,
				ES2.FLOAT, (byte) ES2.FALSE, sizeOfVertex,
				PtrFactory.newWeakVoidPtr(3 * CRuntime.FLOAT_SIZE));

		OpenGLES.glBindVertexArrayOES(0);
	}

    public static int loadGLTexture(String name, String extension) {
        NSMutableDictionary options = NSMutableDictionary.alloc().init();
        options.put(NSNumber.numberWithBool(true), GLKit.GLKTextureLoaderOriginBottomLeft());

        String path = NSBundle.mainBundle().pathForResourceOfType(name, extension);
        GLKTextureInfo info = GLKTextureLoader.textureWithContentsOfFileOptionsError(path, options, null);
        if (info == null) {
            System.out.println("Error loading file: " + name + "." + extension);
            return -1;
        }

        return info.name();
    }

	private void tearDownGL() {
		EAGLContext.setCurrentContext(context);
		OpenGLES.glDeleteBuffers(1, vertexBuffer);
		OpenGLES.glDeleteBuffers(1, indexBuffer);
	}

    private void computeProjectionMatrix(CGSize size) {
        float aspectRatio = (float) Math.abs(size.width() / size.height());

        projectionMatrix = GLKit.GLKMatrix4MakePerspective(
                GLKit.GLKMathDegreesToRadians(Parameters.FOV_Y),
                aspectRatio,
                Parameters.Z_NEAR,
				Parameters.Z_FAR
        );
    }

	@Override
	public void glkViewDrawInRect(GLKView view, CGRect rect) {
		OpenGLES.glClear(ES2.DEPTH_BUFFER_BIT | ES2.COLOR_BUFFER_BIT);

        shaderProgram.use();

        OpenGLES.glActiveTexture(ES2.TEXTURE0);
        OpenGLES.glBindTexture(ES2.TEXTURE_2D, texture);

        MVPMatrix = GLKit.GLKMatrix4Multiply(viewMatrix, modelMatrix);
        computeProjectionMatrix(rect.size());
        MVPMatrix = GLKit.GLKMatrix4Multiply(projectionMatrix, MVPMatrix);
        float[] mvpMatrix = getRawData(MVPMatrix);

        int uMVPMatrix = shaderProgram.getUniformLocation("uMVPMatrix");
        OpenGLES.glUniformMatrix4fv(uMVPMatrix, 1,
                (byte) ES2.FALSE, PtrFactory.newFloatArray(mvpMatrix));

		OpenGLES.glBindVertexArrayOES(vertexArray.getValue());
		OpenGLES.glDrawElements(ES2.TRIANGLES,
                Geometry.INDICES.length, ES2.UNSIGNED_BYTE,
                PtrFactory.newWeakVoidPtr(0));

        shaderProgram.unUse();
	}

	private static float[] getRawData(GLKMatrix4 glkMatrix4) {
		float[] matrix = new float[16];
		for (int i = 0; i < 16; ++i) {
			matrix[i] = glkMatrix4.m(i);
		}
		return matrix;
	}

	@Selector("update")
	public void update() {
		rotation += Parameters.DEGREES_PER_SECOND * timeSinceLastUpdate();

        modelMatrix = GLKit.GLKMatrix4Identity();
        modelMatrix = GLKit.GLKMatrix4Rotate(modelMatrix,
                GLKit.GLKMathDegreesToRadians(rotation), 0, 1, 0);
	}

	@Override
	public void touchesBeganWithEvent(NSSet touches, UIEvent event) {
		setPaused(!isPaused());
	}
}
