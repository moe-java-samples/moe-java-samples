package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSMapViewDelegate;
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
import ios.coregraphics.struct.CGRect;
import ios.corelocation.CLLocation;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSCoder;
import ios.uikit.UIView;
import ios.uikit.struct.UIEdgeInsets;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSMapView extends UIView {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSMapView(Pointer peer) {
		super(peer);
	}

	@Generated
	@Selector("accessibilityElementsHidden")
	public native boolean accessibilityElementsHidden();

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSMapView alloc();

	@Generated
	@Selector("animateToBearing:")
	public native void animateToBearing(double bearing);

	@Generated
	@Selector("animateToCameraPosition:")
	public native void animateToCameraPosition(GMSCameraPosition cameraPosition);

	@Generated
	@Selector("animateToLocation:")
	public native void animateToLocation(
			@ByValue CLLocationCoordinate2D location);

	@Generated
	@Selector("animateToViewingAngle:")
	public native void animateToViewingAngle(double viewingAngle);

	@Generated
	@Selector("animateToZoom:")
	public native void animateToZoom(float zoom);

	@Generated
	@Selector("animateWithCameraUpdate:")
	public native void animateWithCameraUpdate(GMSCameraUpdate cameraUpdate);

	@Generated
	@Selector("camera")
	public native GMSCameraPosition camera();

	@Generated
	@Selector("cameraForBounds:insets:")
	public native GMSCameraPosition cameraForBoundsInsets(
			GMSCoordinateBounds bounds, @ByValue UIEdgeInsets insets);

	@Generated
	@Selector("clear")
	public native void clear();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native GMSMapViewDelegate delegate();

	@Generated
	@Selector("indoorDisplay")
	public native GMSIndoorDisplay indoorDisplay();

	@Generated
	@Selector("init")
	public native GMSMapView init();

	@Generated
	@Selector("initWithCoder:")
	public native GMSMapView initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithFrame:")
	public native GMSMapView initWithFrame(@ByValue CGRect frame);

	@Generated
	@Selector("isBuildingsEnabled")
	public native boolean isBuildingsEnabled();

	@Generated
	@Selector("isIndoorEnabled")
	public native boolean isIndoorEnabled();

	@Generated
	@Selector("isMyLocationEnabled")
	public native boolean isMyLocationEnabled();

	@Generated
	@Selector("isTrafficEnabled")
	public native boolean isTrafficEnabled();

	@Generated
	@Selector("layer")
	public native GMSMapLayer layer();

	@Generated
	@Selector("mapType")
	public native int mapType();

	@Generated
	@Selector("mapWithFrame:camera:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object mapWithFrameCamera(@ByValue CGRect frame,
			GMSCameraPosition camera);

	@Generated
	@Selector("maxZoom")
	public native float maxZoom();

	@Generated
	@Selector("minZoom")
	public native float minZoom();

	@Generated
	@Selector("moveCamera:")
	public native void moveCamera(GMSCameraUpdate update);

	@Generated
	@Selector("myLocation")
	public native CLLocation myLocation();

	@Generated
	@Selector("padding")
	@ByValue
	public native UIEdgeInsets padding();

	@Generated
	@Selector("projection")
	public native GMSProjection projection();

	@Generated
	@Selector("selectedMarker")
	public native GMSMarker selectedMarker();

	@Generated
	@Selector("setAccessibilityElementsHidden:")
	public native void setAccessibilityElementsHidden(boolean value);

	@Generated
	@Selector("setBuildingsEnabled:")
	public native void setBuildingsEnabled(boolean value);

	@Generated
	@Selector("setCamera:")
	public native void setCamera(GMSCameraPosition value);

	@Generated
	@Selector("setDelegate:")
	public native void setDelegate_unsafe(
			@Mapped(ObjCObjectMapper.class) GMSMapViewDelegate value);

	@Generated
	public void setDelegate(
			@Mapped(ObjCObjectMapper.class) GMSMapViewDelegate value) {
		Object __old = delegate();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setDelegate_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("setIndoorEnabled:")
	public native void setIndoorEnabled(boolean value);

	@Generated
	@Selector("setMapType:")
	public native void setMapType(int value);

	@Generated
	@Selector("setMinZoom:maxZoom:")
	public native void setMinZoomMaxZoom(float minZoom, float maxZoom);

	@Generated
	@Selector("setMyLocationEnabled:")
	public native void setMyLocationEnabled(boolean value);

	@Generated
	@Selector("setPadding:")
	public native void setPadding(@ByValue UIEdgeInsets value);

	@Generated
	@Selector("setSelectedMarker:")
	public native void setSelectedMarker(GMSMarker value);

	@Generated
	@Selector("setTrafficEnabled:")
	public native void setTrafficEnabled(boolean value);

	@Generated
	@Selector("settings")
	public native GMSUISettings settings();

	@Generated
	@Deprecated
	@Selector("startRendering")
	public native void startRendering();

	@Generated
	@Deprecated
	@Selector("stopRendering")
	public native void stopRendering();
}