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

package com.intel.inde.moe.samples.flipview.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;

import ios.coregraphics.c.CoreGraphics;
import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSNumber;
import ios.foundation.NSString;
import ios.foundation.c.Foundation;
import ios.uikit.UIImage;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIViewAnimationTransition;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("FlipViewController")
@RegisterOnStartup
public class FlipViewController extends UIViewController {

    @Selector("alloc")
    public static native FlipViewController alloc();

    @Selector("init")
    public native FlipViewController init();

    protected FlipViewController(Pointer peer) {
        super(peer);
    }

    private static final float kFlipTransitionDuration = 0.75f;

    private boolean frontViewIsVisible;
    private FrontView frontView;
    private FlippedView flippedView;

    private CGRect getRectForSubViews(CGSize superviewSize) {
        CGSize preferredViewSize = FrontView.preferredViewSize();

        CGRect viewRect = CoreGraphics.CGRectMake(
                (superviewSize.width() - preferredViewSize.width()) / 2,
                (superviewSize.height() - preferredViewSize.height()) / 2,
                preferredViewSize.width(),
                preferredViewSize.height()
        );

        return viewRect;
    }

    @Override
    public void viewDidLoad() {
        frontViewIsVisible = true;

        CGRect viewRect = getRectForSubViews(view().bounds().size());

        // Create the about company view
        FrontView localFrontView = (FrontView) FrontView.alloc().initWithFrame(viewRect);
        frontView = localFrontView;

        // Add the about company view to the view controller's view
        view().addSubview(frontView);

        this.frontView.setViewController(this);

        // Create the atomic element flipped view
        FlippedView localFlippedView = (FlippedView) FlippedView.alloc().initWithFrame(viewRect);
        flippedView = localFlippedView;

        flippedView.setViewController(this);
    }

    @Override
    public void viewWillTransitionToSizeWithTransitionCoordinator(@ByValue CGSize size, @Mapped(ObjCObjectMapper.class) Object coordinator) {
        CGRect viewRect = getRectForSubViews(size);
        frontView.setFrame(viewRect);
        flippedView.setFrame(viewRect);
    }

    public void flipCurrentView() {
        UIImage reflectedImage;

        // Disable user interaction during the flip animation
        this.view().setUserInteractionEnabled(false);

        // Setup the animation group
        UIView.beginAnimationsContext(null, null);
        UIView.setAnimationDuration_static(kFlipTransitionDuration);
        UIView.setAnimationDelegate(this);
        UIView.setAnimationDidStopSelector(Foundation.NSSelectorFromString("myTransitionDidStop:finished:context:"));

        // Swap the views and transition
        if (frontViewIsVisible) {
            UIView.setAnimationTransitionForViewCache(UIViewAnimationTransition.FlipFromRight, this.view(), true);
            frontView.removeFromSuperview();
            view().addSubview(flippedView);
        } else {
            UIView.setAnimationTransitionForViewCache(UIViewAnimationTransition.FlipFromLeft, this.view(), true);
            flippedView.removeFromSuperview();
            view().addSubview(frontView);
        }
        UIView.commitAnimations();

        // Invert the front view state
        frontViewIsVisible = !frontViewIsVisible;
    }

    @Selector("myTransitionDidStop:finished:context:")
    public void myTransitionDidStop(NSString animationID, NSNumber finished, VoidPtr context) {
        // Re-enable user interaction when the flip animation is completed
        this.view().setUserInteractionEnabled(true);
    }
}
