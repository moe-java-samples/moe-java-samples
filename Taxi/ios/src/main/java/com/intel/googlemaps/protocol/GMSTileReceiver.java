package com.intel.googlemaps.protocol;


import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NUInt;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCProtocolName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.uikit.UIImage;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCProtocolName("GMSTileReceiver")
public interface GMSTileReceiver {
	@Generated
	@Selector("receiveTileWithX:y:zoom:image:")
	void receiveTileWithXYZoomImage(@NUInt long x, @NUInt long y,
			@NUInt long zoom, UIImage image);
}