package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.coregraphics.struct.CGPoint;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.uikit.UIColor;
import ios.uikit.UIImage;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSMarker extends GMSOverlay {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSMarker(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSMarker alloc();

	@Generated
	@Selector("appearAnimation")
	public native int appearAnimation();

	@Generated
	@Selector("groundAnchor")
	@ByValue
	public native CGPoint groundAnchor();

	@Generated
	@Selector("icon")
	public native UIImage icon();

	@Generated
	@Selector("infoWindowAnchor")
	@ByValue
	public native CGPoint infoWindowAnchor();

	@Generated
	@Selector("init")
	public native GMSMarker init();

	@Generated
	@Selector("isDraggable")
	public native boolean isDraggable();

	@Generated
	@Selector("isFlat")
	public native boolean isFlat();

	@Generated
	@Selector("layer")
	public native GMSMarkerLayer layer();

	@Generated
	@Selector("markerImageWithColor:")
	public static native UIImage markerImageWithColor(UIColor color);

	@Generated
	@Selector("markerWithPosition:")
	public static native GMSMarker markerWithPosition(
			@ByValue CLLocationCoordinate2D position);

	@Generated
	@Selector("opacity")
	public native float opacity();

	@Generated
	@Selector("panoramaView")
	public native GMSPanoramaView panoramaView();

	@Generated
	@Selector("position")
	@ByValue
	public native CLLocationCoordinate2D position();

	@Generated
	@Selector("rotation")
	public native double rotation();

	@Generated
	@Selector("setAppearAnimation:")
	public native void setAppearAnimation(int value);

	@Generated
	@Selector("setDraggable:")
	public native void setDraggable(boolean value);

	@Generated
	@Selector("setFlat:")
	public native void setFlat(boolean value);

	@Generated
	@Selector("setGroundAnchor:")
	public native void setGroundAnchor(@ByValue CGPoint value);

	@Generated
	@Selector("setIcon:")
	public native void setIcon(UIImage value);

	@Generated
	@Selector("setInfoWindowAnchor:")
	public native void setInfoWindowAnchor(@ByValue CGPoint value);

	@Generated
	@Selector("setOpacity:")
	public native void setOpacity(float value);

	@Generated
	@Selector("setPanoramaView:")
	public native void setPanoramaView_unsafe(GMSPanoramaView value);

	@Generated
	public void setPanoramaView(GMSPanoramaView value) {
		Object __old = panoramaView();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setPanoramaView_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("setPosition:")
	public native void setPosition(@ByValue CLLocationCoordinate2D value);

	@Generated
	@Selector("setRotation:")
	public native void setRotation(double value);

	@Generated
	@Selector("setSnippet:")
	public native void setSnippet(String value);

	@Generated
	@Selector("setUserData:")
	public native void setUserData(@Mapped(ObjCObjectMapper.class) Object value);

	@Generated
	@Selector("snippet")
	public native String snippet();

	@Generated
	@Selector("userData")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object userData();
}