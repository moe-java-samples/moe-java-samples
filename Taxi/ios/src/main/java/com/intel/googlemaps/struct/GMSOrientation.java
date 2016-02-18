package com.intel.googlemaps.struct;


import com.intel.inde.moe.natj.c.StructObject;
import com.intel.inde.moe.natj.c.ann.Structure;
import com.intel.inde.moe.natj.c.ann.StructureField;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;

@Generated
@Structure()
public final class GMSOrientation extends StructObject {
	static {
		NatJ.register();
	}
	private static long __natjCache;

	@Generated
	public GMSOrientation() {
		super(GMSOrientation.class);
	}

	@Generated
	protected GMSOrientation(Pointer peer) {
		super(peer);
	}

	@Generated
	public GMSOrientation(double heading, double pitch) {
		super(GMSOrientation.class);
		setHeading(heading);
		setPitch(pitch);
	}

	@Generated
	@StructureField(order = 0, isGetter = true)
	public native double heading();

	@Generated
	@StructureField(order = 0, isGetter = false)
	public native void setHeading(double value);

	@Generated
	@StructureField(order = 1, isGetter = true)
	public native double pitch();

	@Generated
	@StructureField(order = 1, isGetter = false)
	public native void setPitch(double value);
}