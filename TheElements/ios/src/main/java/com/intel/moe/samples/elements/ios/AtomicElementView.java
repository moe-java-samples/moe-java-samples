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

package com.intel.moe.samples.elements.ios;

import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.ByValue;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.elements.common.AtomicElement;

import ios.coregraphics.c.CoreGraphics;
import ios.coregraphics.opaque.CGContextRef;
import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSString;
import ios.foundation.c.Foundation;
import ios.uikit.UIColor;
import ios.uikit.UIFont;
import ios.uikit.UIGestureRecognizer;
import ios.uikit.UIImage;
import ios.uikit.UITapGestureRecognizer;
import ios.uikit.UIView;

public class AtomicElementView extends UIView {

    @Selector("alloc")
    public static native AtomicElementView alloc();

    @Selector("init")
    public native AtomicElementView init();

    protected AtomicElementView(Pointer peer) {
        super(peer);
    }

    protected AtomicElement element = null;
    protected AtomicElementViewController viewController = null;

    // The preferred size of this view is the size of the background image
    public static CGSize preferredViewSize() {
        return CoreGraphics.CGSizeMake(256, 256);
    }

    // Initialize the view, calling super and setting the properties
    @Override
    public UIView initWithFrame(@ByValue CGRect frame) {
        super.initWithFrame(frame);

        // Set the background color of the view to clear
        this.setBackgroundColor(UIColor.clearColor());

        // Attach a tap gesture recognizer to this view so it can flip
        UITapGestureRecognizer tapGestureRecognizer = (UITapGestureRecognizer) UITapGestureRecognizer.alloc().initWithTargetAction(this, Foundation.NSSelectorFromString("tapAction:"));
        this.addGestureRecognizer(tapGestureRecognizer);

        return this;
    }

    @Selector("tapAction:")
    public void tapAction(UIGestureRecognizer gestureRecognizer) {
        // When a tap gesture occurs tell the view controller to flip this view to the
        // back and show the AtomicElementFlippedView instead
        viewController.flipCurrentView();
    }

    public void setElement(AtomicElement element) {
        this.element = element;
    }

    public void setViewController(AtomicElementViewController viewController) {
        this.viewController = viewController;
    }

    @Selector("drawRect:")
    @Override
    public void drawRect(CGRect cgRect) {
        // Get the background image for the state of the element
        // position it appropriately and draw the image
        UIImage backgroundImage = PeriodicElements.stateImageForAtomicElementView(element.getState());
        CGRect elementSymbolRectangle = CoreGraphics.CGRectMake(0, 0,
                backgroundImage.size().width(), backgroundImage.size().height()
        );
        backgroundImage.drawInRect(elementSymbolRectangle);

        // All the text is drawn in white
        UIColor.whiteColor().set();

        // Draw the element name
        UIFont font = UIFont.boldSystemFontOfSize(36);
        NSString name = NSString.stringWithString(element.getName());
        CGSize stringSize = name.sizeWithFont(font);
        CGPoint point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, 256 / 2 - 50);
        name.drawAtPointWithFont(point, font);

        // Draw the element number
        font = UIFont.boldSystemFontOfSize(48);
        point = CoreGraphics.CGPointMake(10, 0);
        int n = element.getAtomicNumber();
        NSString atomicNumber = NSString.stringWithString(Integer.toString(n));
        atomicNumber.drawAtPointWithFont(point, font);

        // Draw the element symbol
        font = UIFont.boldSystemFontOfSize(96);
        NSString symbol = NSString.stringWithString(element.getSymbol());
        stringSize = symbol.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, 256 - 120);
        symbol.drawAtPointWithFont(point, font);
    }

    public UIImage reflectedImageRepresentationWithHeight(int height) {

        CGContextRef mainViewContentContext;
        //CGColorSpaceRef colorSpace;

        // Create a bitmap graphics context the size of the image
        // mainViewContentContext = CGBitmapContextCreate (NULL, self.bounds.size.width,height, 8,0, colorSpace, kCGImageAlphaPremultipliedLast);

        return null;
    }
}
