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
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.elements.common.AtomicElement;

import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("AtomicElementTableViewCell")
@RegisterOnStartup
public class AtomicElementTableViewCell extends UITableViewCell {

    private AtomicElement element = null;

    @Selector("alloc")
    public static native AtomicElementTableViewCell alloc();

    @Selector("init")
    public native AtomicElementTableViewCell init();

    protected AtomicElementTableViewCell(Pointer peer) {
        super(peer);
    }

    // The element setter
    // We implement this because the table cell values need to be updated when this property
    // changes, and this allows for the changes to be encapsulated

    public void setElement(AtomicElement element) {
        this.element = element;

        AtomicElementTileView elementTileView = (AtomicElementTileView) this.contentView().viewWithTag(1);
        elementTileView.setElement(element);

        UILabel labelView = (UILabel) this.contentView().viewWithTag(2);
        String name = element.getName();
        labelView.setText(name);

        elementTileView.setNeedsDisplay();
        labelView.setNeedsDisplay();
    }
}
