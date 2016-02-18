package com.intel.googlemaps.protocol;


import com.intel.googlemaps.GMSCameraPosition;
import com.intel.googlemaps.GMSMapView;
import com.intel.googlemaps.GMSMarker;
import com.intel.googlemaps.GMSOverlay;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCProtocolName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.uikit.UIView;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCProtocolName("GMSMapViewDelegate")
public interface GMSMapViewDelegate {
	@Generated
	@IsOptional
	@Selector("didTapMyLocationButtonForMapView:")
	default boolean didTapMyLocationButtonForMapView(GMSMapView mapView) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didBeginDraggingMarker:")
	default void mapViewDidBeginDraggingMarker(GMSMapView mapView,
			GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didChangeCameraPosition:")
	default void mapViewDidChangeCameraPosition(GMSMapView mapView,
			GMSCameraPosition position) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didCloseInfoWindowOfMarker:")
	default void mapViewDidCloseInfoWindowOfMarker(GMSMapView mapView,
			GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didDragMarker:")
	default void mapViewDidDragMarker(GMSMapView mapView, GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didEndDraggingMarker:")
	default void mapViewDidEndDraggingMarker(GMSMapView mapView,
			GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didLongPressAtCoordinate:")
	default void mapViewDidLongPressAtCoordinate(GMSMapView mapView,
			@ByValue CLLocationCoordinate2D coordinate) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didLongPressInfoWindowOfMarker:")
	default void mapViewDidLongPressInfoWindowOfMarker(GMSMapView mapView,
			GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didTapAtCoordinate:")
	default void mapViewDidTapAtCoordinate(GMSMapView mapView,
			@ByValue CLLocationCoordinate2D coordinate) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didTapInfoWindowOfMarker:")
	default void mapViewDidTapInfoWindowOfMarker(GMSMapView mapView,
			GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didTapMarker:")
	default boolean mapViewDidTapMarker(GMSMapView mapView, GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:didTapOverlay:")
	default void mapViewDidTapOverlay(GMSMapView mapView, GMSOverlay overlay) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:idleAtCameraPosition:")
	default void mapViewIdleAtCameraPosition(GMSMapView mapView,
			GMSCameraPosition position) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:markerInfoContents:")
	default UIView mapViewMarkerInfoContents(GMSMapView mapView,
			GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:markerInfoWindow:")
	default UIView mapViewMarkerInfoWindow(GMSMapView mapView, GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapView:willMove:")
	default void mapViewWillMove(GMSMapView mapView, boolean gesture) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapViewDidFinishTileRendering:")
	default void mapViewDidFinishTileRendering(GMSMapView mapView) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("mapViewDidStartTileRendering:")
	default void mapViewDidStartTileRendering(GMSMapView mapView) {
		throw new java.lang.UnsupportedOperationException();
	}
}