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
import com.intel.inde.moe.samples.planets.common.Planet;
import com.intel.inde.moe.samples.planets.common.Simulation;

import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGRect;
import ios.uikit.UIBezierPath;
import ios.uikit.UIColor;
import ios.uikit.UIView;

public class CoreGraphicsBackend extends UIView {

	private Simulation simulation;

	public static native CoreGraphicsBackend alloc();

	@Override
	public native CoreGraphicsBackend init();

	@Override
	public native CoreGraphicsBackend initWithFrame(CGRect frame);

	protected CoreGraphicsBackend(Pointer peer) {
		super(peer);
	}

	@Override
	public void drawRect(CGRect rect) {
		UIColor.blackColor().set();
		UIBezierPath.bezierPathWithRect(this.bounds()).fill();

		if (simulation == null) {
			return;
		}

		UIColor.orangeColor().set();
		for (Planet p : simulation.getPlanets()) {
			CGPoint cgPoint = new CGPoint(p.getLocation().getX(), p.getLocation().getY());
			UIBezierPath
					.bezierPathWithArcCenterRadiusStartAngleEndAngleClockwise(
							cgPoint, p.getRadius(), 0, 2 * Simulation.PI, true).fill();
		}
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
