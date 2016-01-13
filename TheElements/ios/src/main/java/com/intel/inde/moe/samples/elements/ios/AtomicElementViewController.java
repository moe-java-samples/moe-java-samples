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
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import com.intel.inde.moe.samples.elements.common.AtomicElement;

import ios.coregraphics.c.CoreGraphics;
import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSNumber;
import ios.foundation.NSString;
import ios.foundation.c.Foundation;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIButton;
import ios.uikit.UIImage;
import ios.uikit.UIImageView;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIControlState;
import ios.uikit.enums.UIViewAnimationTransition;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("AtomicElementViewController")
@RegisterOnStartup
public class AtomicElementViewController extends UIViewController {

    @Selector("alloc")
    public static native AtomicElementViewController alloc();

    @Selector("init")
    public native AtomicElementViewController init();

    protected AtomicElementViewController(Pointer peer) {
        super(peer);
    }

    private static final float kFlipTransitionDuration = 0.75f;
    private static final float reflectionFraction = 0.35f;
    private static final float reflectionOpacity = 0.5f;

    private AtomicElement element = null;
    private boolean frontViewIsVisible;
    private AtomicElementView atomicElementView;
    private UIImageView reflectionView;
    private AtomicElementFlippedView atomicElementFlippedView;
    private UIButton flipIndicatorButton;

    @Override
    public void viewDidLoad() {
        frontViewIsVisible = true;

        CGSize preferredAtomicElementViewSize = AtomicElementView.preferredViewSize();

        CGSize viewSize = view().bounds().size();

        CGRect viewRect = CoreGraphics.CGRectMake((viewSize.width() - preferredAtomicElementViewSize.width()) / 2,
                (viewSize.height() - preferredAtomicElementViewSize.height()) / 2 - 40,
                preferredAtomicElementViewSize.width(),
                preferredAtomicElementViewSize.height()
        );

        // Create the atomic element view
        AtomicElementView localAtomicElementView = (AtomicElementView) AtomicElementView.alloc().initWithFrame(viewRect);
        atomicElementView = localAtomicElementView;

        // Add the atomic element view to the view controller's view
        atomicElementView.setElement(element);
        view().addSubview(atomicElementView);

        atomicElementView.setViewController(this);

        // Create the atomic element flipped view
        AtomicElementFlippedView localAtomicElementFlippedView = (AtomicElementFlippedView) AtomicElementFlippedView.alloc().initWithFrame(viewRect);
        atomicElementFlippedView = localAtomicElementFlippedView;

        atomicElementFlippedView.setElement(element);
        atomicElementFlippedView.setViewController(this);

        // Create the reflection view
//        CGRect reflectionRect = viewRect;
//
//        // The reflection is a fraction of the size of the view being reflected
//        reflectionRect.getSize().setHeight(reflectionRect.getSize().getHeight() * reflectionFraction);
//
//        // and is offset to be at the bottom of the view being reflected
//        reflectionRect.getOrigin().setY(viewRect.getSize().getHeight());
//
//        UIImageView localReflectionImageView = (UIImageView) UIImageView.alloc().init(reflectionRect);
//        this.reflectionView = localReflectionImageView;
//
//        // Determine the size of the reflection to create
//        int reflectionHeight = (int) (this.atomicElementView.getBounds().getSize().getHeight() * reflectionFraction);
//
//        // Create the reflection image, assign it to the UIImageView and add the image view to the view controller's view
//        // self.reflectionView.image = [self.atomicElementView reflectedImageRepresentationWithHeight:reflectionHeight];
//        this.reflectionView.setImage(this.atomicElementView.reflectedImageRepresentationWithHeight(reflectionHeight));
//        this.reflectionView.setAlpha(reflectionOpacity);

        //getView().addSubview(this.reflectionView);

        // Setup our flip indicator button (placed as a nav bar item to the right)
        UIButton localFlipIndicator = UIButton.alloc().initWithFrame(CoreGraphics.CGRectMake(0.0, 0.0, 30.0, 30.0));
        this.flipIndicatorButton = localFlipIndicator;

        // Front view is always visible at first
        UIImage flipper_list_blue = UIImage.imageNamed("flipper_list_blue.png");
        this.flipIndicatorButton.setBackgroundImageForState(flipper_list_blue, UIControlState.Normal);

        UIBarButtonItem flipButtonBarItem;
        flipButtonBarItem = UIBarButtonItem.alloc().initWithCustomView(this.flipIndicatorButton);
        flipIndicatorButton.addTargetActionForControlEvents(this, Foundation.NSSelectorFromString("flipCurrentView"), UIControlEvents.TouchDown);
        this.navigationItem().setRightBarButtonItemAnimated(flipButtonBarItem, true);
    }

    private CGRect getRectForSubViews(CGSize superviewSize) {
        CGSize preferredViewSize = AtomicElementView.preferredViewSize();

        CGRect viewRect = CoreGraphics.CGRectMake(
                (superviewSize.width() - preferredViewSize.width()) / 2,
                (superviewSize.height() - preferredViewSize.height()) / 2,
                preferredViewSize.width(),
                preferredViewSize.height()
        );

        return viewRect;
    }

    @Override
    public void viewWillTransitionToSizeWithTransitionCoordinator(@ByValue CGSize size, @Mapped(ObjCObjectMapper.class) Object coordinator) {
        CGRect viewRect = getRectForSubViews(size);
        atomicElementView.setFrame(viewRect);
        atomicElementFlippedView.setFrame(viewRect);
    }

    public void setElement(AtomicElement element) {
        this.element = element;
    }

    @Selector("flipCurrentView")
    public void flipCurrentView() {
//        NSUInteger reflectionHeight;
        UIImage reflectedImage;

        // Disable user interaction during the flip animation
        this.view().setUserInteractionEnabled(false);
        this.flipIndicatorButton.setUserInteractionEnabled(false);

        // Setup the animation group
        UIView.beginAnimationsContext(null, null);
        UIView.setAnimationDuration_static(kFlipTransitionDuration);
        UIView.setAnimationDelegate(this);
        UIView.setAnimationDidStopSelector(Foundation.NSSelectorFromString("myTransitionDidStop:finished:context:"));

        // Swap the views and transition
        if (frontViewIsVisible) {
            UIView.setAnimationTransitionForViewCache(UIViewAnimationTransition.FlipFromRight, this.view(), true);
            atomicElementView.removeFromSuperview();
            view().addSubview(atomicElementFlippedView);

            // Update the reflection image for the new view
        } else {
            UIView.setAnimationTransitionForViewCache(UIViewAnimationTransition.FlipFromLeft, this.view(), true);
            atomicElementFlippedView.removeFromSuperview();
            view().addSubview(atomicElementView);

            // Update the reflection image for the new view
        }
        UIView.commitAnimations();

        // Swap the nav bar button views
        UIView.beginAnimationsContext(null, null);
        UIView.setAnimationDuration_static(kFlipTransitionDuration);
        UIView.setAnimationDelegate(this);

        if (frontViewIsVisible) {
            UIView.setAnimationTransitionForViewCache(UIViewAnimationTransition.FlipFromRight, flipIndicatorButton, true);
            UIImage flipperImage = PeriodicElements.flipperImageForAtomicElementNavigationItem(element);
            this.flipIndicatorButton.setBackgroundImageForState(flipperImage, UIControlState.Normal);
        } else {
            UIView.setAnimationTransitionForViewCache(UIViewAnimationTransition.FlipFromLeft, flipIndicatorButton, true);
            UIImage flipper_list_blue = UIImage.imageNamed("flipper_list_blue.png");
            this.flipIndicatorButton.setBackgroundImageForState(flipper_list_blue, UIControlState.Normal);
        }

        UIView.commitAnimations();

        // Invert the front view state
        frontViewIsVisible = !frontViewIsVisible;
    }

    @Selector("myTransitionDidStop:finished:context:")
    public void myTransitionDidStop(NSString animationID, NSNumber finished, VoidPtr context) {
        // Re-enable user interaction when the flip animation is completed
        this.view().setUserInteractionEnabled(true);
        this.flipIndicatorButton.setUserInteractionEnabled(true);
    }
}
