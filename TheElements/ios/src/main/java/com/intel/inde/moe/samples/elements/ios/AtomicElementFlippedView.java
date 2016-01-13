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

package com.intel.inde.moe.samples.elements.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.NSObject;
import ios.coregraphics.c.CoreGraphics;
import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSString;
import ios.foundation.NSURL;
import ios.foundation.c.Foundation;
import ios.uikit.UIApplication;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UIFont;
import ios.uikit.UIImage;
import ios.uikit.UIView;
import ios.uikit.enums.UIButtonType;
import ios.uikit.enums.UIControlContentHorizontalAlignment;
import ios.uikit.enums.UIControlContentVerticalAlignment;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIControlState;

public class AtomicElementFlippedView extends AtomicElementView {

    @Selector("alloc")
    public static native AtomicElementFlippedView alloc();

    @Selector("init")
    public native AtomicElementFlippedView init();

    protected AtomicElementFlippedView(Pointer peer) {
        super(peer);
    }

    private UIButton wikipediaButton;

    private void setupUserInterface() {
        CGRect buttonFrame = CoreGraphics.CGRectMake(10.0, 209.0, 234.0, 37.0);

        // Create the button
        wikipediaButton = (UIButton) UIButton.buttonWithType(UIButtonType.RoundedRect);
        wikipediaButton.setFrame(buttonFrame);

        wikipediaButton.setTitleForState("View at Wikipedia", UIControlState.Normal);

        // Center the text on the button, considering the button's shadow
        wikipediaButton.setContentHorizontalAlignment(UIControlContentHorizontalAlignment.Center);
        wikipediaButton.setContentVerticalAlignment(UIControlContentVerticalAlignment.Center);

        wikipediaButton.addTargetActionForControlEvents(this, Foundation.NSSelectorFromString("jumpToWikipedia:"), UIControlEvents.TouchUpInside);

        this.addSubview(wikipediaButton);
    }

    @Override
    public UIView initWithFrame(@ByValue CGRect frame) {
        super.initWithFrame(frame);
        this.setAutoresizesSubviews(true);
        this.setupUserInterface();
        return this;
    }

    @Selector("jumpToWikipedia:")
    public void jumpToWikipedia(NSObject sender) {
        // Create the string that points to the correct Wikipedia page for the element name
        String wikiPageString = "http://en.wikipedia.org/wiki/" + element.getName();
        if (!UIApplication.sharedApplication().openURL(NSURL.URLWithString(wikiPageString))) {
            System.out.println("Cannot open: " + wikiPageString);
            // There was an error trying to open the URL. for the moment we'll simply ignore it.
        }
    }

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

        // Draw the element number
        UIFont font = UIFont.boldSystemFontOfSize(32);
        CGPoint point = CoreGraphics.CGPointMake(10, 5);
        int n = element.getAtomicNumber();
        NSString atomicNumber = NSString.stringWithString(Integer.toString(n));
        atomicNumber.drawAtPointWithFont(point, font);

        // Draw the element symbol
        NSString symbol = NSString.stringWithString(element.getSymbol());
        CGSize stringSize = symbol.sizeWithFont(font);
        point = CoreGraphics.CGPointMake(this.bounds().size().width() - stringSize.width() - 10, 5);
        symbol.drawAtPointWithFont(point, font);

        // Draw the element name
        font = UIFont.boldSystemFontOfSize(36);
        NSString name = NSString.stringWithString(element.getName());
        stringSize = name.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, 50);
        name.drawAtPointWithFont(point, font);

        float verticalStartingPoint = 95;

        // Draw the element weight
        font = UIFont.boldSystemFontOfSize(14);
        NSString atomicWeightString = NSString.stringWithString("Atomic Weight: " + element.getAtomicWeight());
        stringSize = atomicWeightString.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint);
        atomicWeightString.drawAtPointWithFont(point, font);

        // Draw the element state
        font = UIFont.boldSystemFontOfSize(14);
        NSString stateString = NSString.stringWithString("State: " + element.getState());
        stringSize = stateString.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 20);
        stateString.drawAtPointWithFont(point, font);

        // Draw the element period
        font = UIFont.boldSystemFontOfSize(14);
        NSString periodString = NSString.stringWithString("Period: " + element.getPeriod());
        stringSize = periodString.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 40);
        periodString.drawAtPointWithFont(point, font);

        // Draw the element group
        font = UIFont.boldSystemFontOfSize(14);
        NSString groupString = NSString.stringWithString("Group: " + element.getGroup());
        stringSize = groupString.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 60);
        groupString.drawAtPointWithFont(point, font);

        // Draw the discovery year
        font = UIFont.boldSystemFontOfSize(14);
        NSString discoveryYearString = NSString.stringWithString("Discovered: " + element.getDiscoveryYear());
        stringSize = discoveryYearString.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 80);
        discoveryYearString.drawAtPointWithFont(point, font);
    }
}
