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

package com.intel.moe.samples.planets.common;

import java.util.ArrayList;

public class Simulation {

	public static final double PI = Math.PI;

	private final int planetCount;

	private final ArrayList<Planet> planets;

	private final ArrayList<Vector2> forces;

	public Simulation(int planet_count) {
		planetCount = planet_count;
		forces = new ArrayList<Vector2>(planet_count);
		planets = new ArrayList<Planet>(planet_count);
		for (int i = 0; i < planet_count; ++i) {
			forces.add(new Vector2());
			planets.add(new Planet());
		}
	}

	public void tick(double time) {
		for (int i = 0; i < planetCount; ++i) {
			for (int o = 0; o < planetCount; ++o) {
				if (i != o) {
					_tick(time, i, o);
				}
			}
		}
		for (int i = 0; i < planetCount; ++i) {
			Planet p = planets.get(i);
			Vector2 f = forces.get(i);

			p.getMomentum().add(f);
			p.getLocation().add(p.getMomentum().copy().mul(time));
		}
		for (Vector2 force : forces) {
			force.zero();
		}
	}

	private void _tick(double time, int i, int o) {
		final Planet a = planets.get(i);
		final Planet b = planets.get(o);

		Vector2 diff = b.getLocation().diff(a.getLocation());
		double d = diff.length();

		if (d > a.getRadius()) {
			double ra = a.getRadius();
			double fa = 4.0 * ra * ra * ra * PI / 3.0;
			double rb = b.getRadius();
			double fb = 4.0 * rb * rb * rb * PI / 3.0;

			double e = 0.2 * (fa * fb) / (d * d) / fa * time;

			forces.get(i).add(diff.mul(e));
		}
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}

}
