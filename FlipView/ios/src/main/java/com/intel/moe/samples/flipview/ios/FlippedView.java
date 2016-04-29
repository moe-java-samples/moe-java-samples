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

public class FlippedView extends FrontView {

    @Selector("alloc")
    public static native FlippedView alloc();

    @Selector("init")
    public native FlippedView init();

    protected FlippedView(Pointer peer) {
        super(peer);
    }

    private UIButton wikipediaButton;

    private void setupUserInterface() {
        CGRect buttonFrame = CoreGraphics.CGRectMake(10.0, 209.0, 234.0, 37.0);

        // Create the button
        wikipediaButton = (UIButton) UIButton.buttonWithType(UIButtonType.RoundedRect);
        wikipediaButton.setFrame(buttonFrame);

        wikipediaButton.setTitleForState(Strings.linkNameToWiki, UIControlState.Normal);

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
        // Create the string that points to the correct Wikipedia page
        if (!UIApplication.sharedApplication().openURL(NSURL.URLWithString(Strings.linkToWiki))) {
            System.out.println("Cannot open url: " + Strings.linkToWiki);
        }
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
        UIFont font = UIFont.boldSystemFontOfSize(36);
        NSString name =  NSString.stringWithString(Strings.companyName);
        CGSize stringSize = name.sizeWithFont(font);
        CGPoint point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, 35);
        name.drawAtPointWithFont(point, font);

        float verticalStartingPoint = 85;
        font = UIFont.boldSystemFontOfSize(14);

        // Draw the date
        NSString date = NSString.stringWithString(Strings.companyFounded);
        stringSize = date.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint);
        date.drawAtPointWithFont(point, font);

        // Draw the founders
        String[] founders = Strings.companyFounder.split("\n");
        NSString founder = NSString.stringWithString(founders[0]);
        stringSize = founder.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 20);
        founder.drawAtPointWithFont(point, font);

        founder = NSString.stringWithString(founders[1]);
        stringSize = founder.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 40);
        founder.drawAtPointWithFont(point, font);

        // Draw the headquarters
        NSString headquarters = NSString.stringWithString(Strings.companyHeadquarters);
        stringSize = headquarters.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 60);
        headquarters.drawAtPointWithFont(point, font);

        // Draw number of employees
        NSString employees = NSString.stringWithString(Strings.companyEmployeesNumber);
        stringSize = employees.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 80);
        employees.drawAtPointWithFont(point, font);

        // Draw the revenue
        NSString revenue = NSString.stringWithString(Strings.companyRevenue);
        stringSize = revenue.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((this.bounds().size().width() - stringSize.width()) / 2, verticalStartingPoint + 100);
        revenue.drawAtPointWithFont(point, font);
    }
}
