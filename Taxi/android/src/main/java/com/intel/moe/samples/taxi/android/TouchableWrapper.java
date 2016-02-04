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

package com.intel.moe.samples.taxi.android;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchableWrapper extends FrameLayout {

    public TouchableWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchableWrapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TouchableWrapper(Context context) {
        super(context);
    }

    private long lastTouched = 0;
    private static final long SCROLL_TIME = 200L; // 200 Milliseconds, but you can adjust that to your liking
    private UpdateMapAfterUserInteraction updateMapAfterUserInteraction;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouched = SystemClock.uptimeMillis();
                if (updateMapAfterUserInteraction != null)
                    updateMapAfterUserInteraction.onTouchDown();
                break;
            case MotionEvent.ACTION_UP:
                if (updateMapAfterUserInteraction != null)
                    updateMapAfterUserInteraction.onTouchUp();
//                final long now = SystemClock.uptimeMillis();
//                if (now - lastTouched > SCROLL_TIME) {
//                    // Update the map
//                    if (updateMapAfterUserInteraction != null)
//                        updateMapAfterUserInteraction.onUpdateMapAfterUserInteraction();
//                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    // Map Activity must implement this interface
    public interface UpdateMapAfterUserInteraction {
        void onUpdateMapAfterUserInteraction();
        void onTouchDown();
        void onTouchUp();
    }

    public void setUpdateMapAfterUserInteraction(UpdateMapAfterUserInteraction updateMapAfterUserInteraction){
        this.updateMapAfterUserInteraction = updateMapAfterUserInteraction;
    }
}

