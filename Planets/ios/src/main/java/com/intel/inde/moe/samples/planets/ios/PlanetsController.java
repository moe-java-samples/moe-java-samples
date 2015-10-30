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

package com.intel.inde.moe.samples.planets.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.samples.planets.common.Planet;
import com.intel.inde.moe.samples.planets.common.Simulation;

import ios.foundation.NSArray;
import ios.foundation.NSDictionary;
import ios.foundation.NSTimer;
import ios.uikit.NSLayoutConstraint;
import ios.uikit.UIColor;
import ios.uikit.UIView;
import ios.uikit.UIViewController;

public class PlanetsController extends UIViewController {

	private Simulation simulation;

	private NSTimer timer;

	private UIView renderer;

	public static native PlanetsController alloc();

	@Override
	public native PlanetsController init();

	protected PlanetsController(Pointer peer) {
		super(peer);
	}

	@Override
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Planets");

		view().setBackgroundColor(UIColor.whiteColor());

		renderer = CoreGraphicsBackend.alloc().initWithFrame(view().bounds());
		view().addSubview(renderer);

		renderer.setTranslatesAutoresizingMaskIntoConstraints(false);
		NSDictionary views = NSDictionary.dictionaryWithObjectForKey(renderer,
				"renderer");
		NSArray constrs = NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews(
						"|-0-[renderer]-0-|", 0, (NSDictionary<String, Object>) NSDictionary.dictionary(),
						views);
		view().addConstraints(constrs);
		constrs = NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews(
						"V:|-0-[renderer]-0-|", 0, (NSDictionary<String, Object>) NSDictionary.dictionary(),
						views);
		view().addConstraints(constrs);
		view().layoutSubviews();
	}

	@Override
	public void viewWillAppear(boolean animated) {
		super.viewWillAppear(animated);

		if (timer != null) {
			timer.invalidate();
		}
		timer = NSTimer
				.scheduledTimerWithTimeIntervalTargetSelectorUserInfoRepeats(
						1.0 / 30.0, this, new SEL("tick:"), null, true);
	}

	@Override
	public void viewDidDisappear(boolean animated) {
		if (timer != null) {
			timer.invalidate();
			timer = null;
		}

		super.viewDidDisappear(animated);
	}

	@Selector("tick:")
	public void tick(final NSTimer timer) {
		if (simulation == null) {
			Planet.MAX_LOCATION.setSize(view().bounds().size().width(), view().bounds().size().height());
			simulation = new Simulation(30);
			((CoreGraphicsBackend) renderer).setSimulation(simulation);
		}
		simulation.tick(timer.timeInterval());
		renderer.setNeedsDisplay();
	}

}
