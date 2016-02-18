package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NUInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.uikit.UIImage;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSSyncTileLayer extends GMSTileLayer {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSSyncTileLayer(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSSyncTileLayer alloc();

	@Generated
	@Selector("init")
	public native GMSSyncTileLayer init();

	@Generated
	@Selector("tileForX:y:zoom:")
	public native UIImage tileForXYZoom(@NUInt long x, @NUInt long y,
			@NUInt long zoom);
}