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

package com.intel.inde.moe.samples.keyboardaccessory.ios;

import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.*;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.general.ann.Generated;

import ios.NSObject;
import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGRect;
import ios.foundation.NSDictionary;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.foundation.NSString;
import ios.foundation.NSURL;
import ios.foundation.c.Foundation;
import ios.foundation.struct.NSRange;
import ios.uikit.NSLayoutConstraint;
import ios.uikit.NSTextAttachment;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIScrollView;
import ios.uikit.UITextView;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import ios.uikit.c.UIKit;
import ios.uikit.protocol.UITextViewDelegate;
import ios.uikit.struct.UIEdgeInsets;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("ViewController")
@RegisterOnStartup
public class ViewController extends UIViewController implements UITextViewDelegate {

    static {
        NatJ.register();
    }

    @Owned
    @Selector("alloc")
    public static native ViewController alloc();

    @Generated
    protected ViewController(Pointer peer) {
        super(peer);
    }

    // The height constraint we want to change when the keyboard shows/hides
    @Selector("constraintToAdjust")
    @Property
    public native NSLayoutConstraint getConstraintToAdjust();

    @Selector("setConstraintToAdjust:")
    @Property
    public native void setConstraintToAdjust(NSLayoutConstraint button);

    @Selector("textView")
    @Property
    public native UITextView getTextView();

    @Selector("setTextView:")
    @Property
    public native void setTextView(UITextView button);

    @Selector("doneButton")
    @Property
    public native UIBarButtonItem getDoneButton();

    @Selector("setDoneButton:")
    @Property
    public native void setDoneButton(UIBarButtonItem button);

    @Selector("editButton")
    @Property
    public native UIBarButtonItem getEditButton();

    @Selector("setEditButton:")
    @Property
    public native void setEditButton(UIBarButtonItem button);

    // View placed on top of keyboard
    @Selector("accessoryView")
    @Property
    public native UIView getAccessoryView();

    @Selector("setAccessoryView:")
    @Property
    public native void setAccessoryView(UIView button);

    @Selector("textContainerInset")
    @Property
    public native UIEdgeInsets getTextContainerInset();

    @Selector("setTextContainerInset:")
    @Property
    public native void setTextContainerInset(UIEdgeInsets button);

    @Override
    public void viewDidLoad() {
    }

    @Override
    public void viewDidAppear(boolean b) {
        // Observe keyboard hide and show notifications to resize the text view appropriately
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this,
                Foundation.NSSelectorFromString("keyboardWillShow:"),
                UIKit.UIKeyboardWillShowNotification(),
                null
        );
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this,
                Foundation.NSSelectorFromString("keyboardWillHide:"),
                UIKit.UIKeyboardWillHideNotification(),
                null
        );

        // Start editing the UITextView (makes the keyboard appear when the application launches)
        this.editAction(this);
    }

    @Override
    public void viewDidDisappear(boolean b) {
        NSNotificationCenter.defaultCenter().removeObserverNameObject(this,
                UIKit.UIKeyboardWillChangeFrameNotification(),
                null
        );
        NSNotificationCenter.defaultCenter().removeObserverNameObject(this,
                UIKit.UIKeyboardWillHideNotification(),
                null
        );
    }

    @Override
    public void didRotateFromInterfaceOrientation(long fromInterfaceOrientation) {
        adjustSelection(getTextView());
    }

    @Selector("doneAction:")
    public void doneAction(NSObject sender)
    {
        // User tapped the Done button, release first responder on the text view
        this.getTextView().resignFirstResponder();
    }

    @Selector("editAction:")
    public void editAction(NSObject sender)
    {
        // User tapped the Edit button, make the text view first responder
        this.getTextView().becomeFirstResponder();
    }

    @Override
    public boolean textViewShouldBeginEditing(UITextView textView) {
        // Note: you can create the accessory view programmatically (in code), or from the storyboard
        if (getTextView().inputAccessoryView() == null) {
            getTextView().setInputAccessoryView(getAccessoryView()); // Use what's in the storyboard
        }

        this.navigationItem().setRightBarButtonItem(getDoneButton());

        return true;
    }

    @Override
    public boolean textViewShouldEndEditing(UITextView textView) {
        textView.resignFirstResponder();
        navigationItem().setRightBarButtonItem(getEditButton());

        return true;
    }

    private void adjustSelection(UITextView textView) {
        // Workaround to UITextView bug, text at the very bottom is slightly cropped by the keyboard
        if (textView.respondsToSelector(Foundation.NSSelectorFromString("getTextContainerInset"))) {
            textView.layoutIfNeeded();
            CGRect caretRect = textView.caretRectForPosition(textView.selectedTextRange().end());
            double height = caretRect.size().height();
            height += textView.textContainerInset().bottom();
            caretRect.size().setHeight(height);
            textView.scrollRectToVisibleAnimated(caretRect, false);
        }
    }

    @Override
    public void textViewDidBeginEditing(UITextView textView) {
        adjustSelection(textView);
    }

    @Override
    public void textViewDidChangeSelection(UITextView textView) {
        adjustSelection(textView);
    }

    private void adjustTextViewByKeyboardState(boolean showKeyboard, NSDictionary info) {
        /*
        Reduce the size of the text view so that it's not obscured by the keyboard.
        Animate the resize so that it's in sync with the appearance of the keyboard.
        */
    }

    @Selector("keyboardWillShow:")
    public void keyboardWillShow(NSNotification notification) {
        /*
        Reduce the size of the text view so that it's not obscured by the keyboard.
        Animate the resize so that it's in sync with the appearance of the keyboard.
        */
        NSDictionary userInfo = notification.userInfo();
        adjustTextViewByKeyboardState(true, userInfo);
    }

    @Selector("keyboardWillHide:")
    public void keyboardWillHide(NSNotification notification) {
        /*
        Restore the size of the text view (fill self's view).
        Animate the resize so that it's in sync with the disappearance of the keyboard.
        */
        NSDictionary userInfo = notification.userInfo();
        adjustTextViewByKeyboardState(false, userInfo);
    }

    // Accessory view action

    @Selector("tappedMe:")
    public void tappedMe(NSObject sender)
    {
        // When the accessory view button is tapped, add a suitable string to the text view
        String text = this.getTextView().text();
        NSRange selectedRange = this.getTextView().selectedRange();
        String newText = text.substring(0, (int) selectedRange.location()) + "\nYou tapped me." +
                text.substring((int) (selectedRange.location() + selectedRange.length()));

        this.getTextView().setText(newText);
    }
}
