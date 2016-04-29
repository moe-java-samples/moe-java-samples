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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Planet {

	public static final Vector2 MIN_LOCATION = new Vector2();
	public static final Vector2 MAX_LOCATION = new Vector2();
	public static final double MIN_RADIUS = 1.0f;
	public static final double MAX_RADIUS = 10.0f;

	private final double radius = newRandomFloat(MIN_RADIUS, MAX_RADIUS);

	private final Vector2 momentum = new Vector2();

	private final Vector2 location = newRandomVector2(MIN_LOCATION,
			MAX_LOCATION);

	private static SecureRandom secureRandomGenerator = null;


	private static double newRandomFloat(double min, double max) {
		if (secureRandomGenerator == null) {
			try {
				secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		double randomNumber = 0;
		if (secureRandomGenerator != null)
			randomNumber = secureRandomGenerator.nextDouble();
		return min + (max - min)
				* ((randomNumber * 16385) / 16384.0);
	}

	private static Vector2 newRandomVector2(Vector2 min, Vector2 max) {
		double x = newRandomFloat(min.getX(), max.getX());
		double y = newRandomFloat(min.getY(), max.getY());
		return new Vector2(x, y);
	}

	public double getRadius() {
		return radius;
	}

	public Vector2 getMomentum() {
		return momentum;
	}

	public Vector2 getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "{ " + radius + ", " + location + ", " + momentum + " }";
	}

}
