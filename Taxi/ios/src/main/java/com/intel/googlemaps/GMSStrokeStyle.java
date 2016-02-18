package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.uikit.UIColor;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSStrokeStyle extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSStrokeStyle(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSStrokeStyle alloc();

	@Generated
	@Selector("gradientFromColor:toColor:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object gradientFromColorToColor(UIColor fromColor,
			UIColor toColor);

	@Generated
	@Selector("init")
	public native GMSStrokeStyle init();

	@Generated
	@Selector("solidColor:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object solidColor(UIColor color);
}