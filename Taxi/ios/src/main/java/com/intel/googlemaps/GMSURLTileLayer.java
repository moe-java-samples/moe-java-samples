package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NUInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCBlock;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.foundation.NSURL;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSURLTileLayer extends GMSTileLayer {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSURLTileLayer(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSURLTileLayer alloc();

	@Generated
	@Selector("init")
	public native GMSURLTileLayer init();

	@Generated
	@Selector("setUserAgent:")
	public native void setUserAgent(String value);

	@Generated
	@Selector("tileLayerWithURLConstructor:")
	public static native GMSURLTileLayer tileLayerWithURLConstructor(
			@ObjCBlock(name = "call_tileLayerWithURLConstructor") Block_tileLayerWithURLConstructor constructor);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_tileLayerWithURLConstructor {
		@Generated
		NSURL call_tileLayerWithURLConstructor(@NUInt long arg0,
				@NUInt long arg1, @NUInt long arg2);
	}

	@Generated
	@Selector("userAgent")
	public native String userAgent();
}