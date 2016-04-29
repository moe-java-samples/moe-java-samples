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

package com.intel.moe.samples.flipview.ios;

import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.ByValue;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.flipview.common.Strings;

import ios.coregraphics.c.CoreGraphics;
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

public class FrontView extends UIView {

    @Selector("alloc")
    public static native FrontView alloc();

    @Selector("init")
    public native FrontView init();

    protected FrontView(Pointer peer) {
        super(peer);
    }

    protected FlipViewController viewController = null;

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

    public void setViewController(FlipViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public void drawRect(CGRect cgRect) {
        // Get the background image, position it appropriately and draw the image
        UIImage backgroundImage = UIImage.imageNamed("Background.png");
        CGRect elementSymbolRectangle = CoreGraphics.CGRectMake(0, 0,
                backgroundImage.size().width(), backgroundImage.size().height()
        );
        backgroundImage.drawInRect(elementSymbolRectangle);

        // All the text is drawn in white
        UIColor.whiteColor().set();

        // Draw the company name
        UIFont font = UIFont.boldSystemFontOfSize(96);
        NSString symbol =  NSString.stringWithString(Strings.companyName);;
        CGSize stringSize = symbol.sizeWithFont(font);
        CGPoint point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2,
                (this.bounds().size().height() - stringSize.height()) / 2);
        symbol.drawAtPointWithFont(point, font);

        // Draw the company name
        font = UIFont.boldSystemFontOfSize(14);
        NSString name =  NSString.stringWithString(Strings.tabButtonName);
        stringSize = name.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, 256 - 50);
        name.drawAtPointWithFont(point, font);
    }
}
