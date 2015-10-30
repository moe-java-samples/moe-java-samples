package com.intel.inde.moe.samples.simplemap.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.Selector;

import java.lang.ref.WeakReference;

import ios.foundation.NSMutableArray;
import ios.foundation.NSMutableDictionary;
import ios.mapkit.MKAnnotationView;
import ios.mapkit.MKMapView;
import ios.mapkit.MKPinAnnotationView;
import ios.mapkit.MKPointAnnotation;
import ios.mapkit.enums.MKPinAnnotationColor;
import ios.mapkit.protocol.MKMapViewDelegate;
import ios.uikit.NSLayoutConstraint;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIButtonType;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UIControlState;
import ios.uikit.enums.UIRectEdge;
import ios.uikit.enums.UIReturnKeyType;
import ios.uikit.enums.UITextBorderStyle;
import ios.uikit.protocol.UITextFieldDelegate;

public class SimpleMapController extends UIViewController {

	private static final String PointAnnotationViewID = "PointAnnotation";

	private WeakReference<UITextField> fieldRef;
	private WeakReference<MKMapView> mapRef;

	private final NSMutableDictionary views = NSMutableDictionary.alloc().init();
	private final NSMutableArray constraints = NSMutableArray.alloc().init();

	private final UITextFieldDelegate tfdelegate = new UITextFieldDelegate() {

		@Override
		public boolean textFieldShouldReturn(UITextField textField) {
			textField.resignFirstResponder();
			return false;
		}
	};

	public static native SimpleMapController alloc();

	@Override
	public native SimpleMapController init();

	protected SimpleMapController(Pointer peer) {
		super(peer);
	}

	@Override
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Simple Map");

		setEdgesForExtendedLayout(UIRectEdge.None);
		view().setBackgroundColor(UIColor.whiteColor());

		views.put("bottomGuide", this.bottomLayoutGuide());
		views.put("topGuide", this.topLayoutGuide());

		final MKMapView mapView = MKMapView.alloc().init();
		mapView.setTranslatesAutoresizingMaskIntoConstraints(false);
		view().addSubview(mapView);
		views.put("map", mapView);

		final UITextField pinLabelField = UITextField.alloc().init();
		pinLabelField.setTranslatesAutoresizingMaskIntoConstraints(false);
		view().addSubview(pinLabelField);
		views.put("pinLabel", pinLabelField);

		mapRef = new WeakReference<MKMapView>(mapView);
		fieldRef = new WeakReference<UITextField>(pinLabelField);

		mapView.setDelegate(new MKMapViewDelegate() {

			@Override
			public void mapViewDidSelectAnnotationView(MKMapView mapView,
													   MKAnnotationView view) {
				MKPointAnnotation ann = (MKPointAnnotation) view.annotation();
				pinLabelField.setText(ann.title());
				MKPinAnnotationView pa = (MKPinAnnotationView) view;
				pa.setPinColor(MKPinAnnotationColor.Green);
			}

			@Override
			public void mapViewDidDeselectAnnotationView(MKMapView mapView,
														 MKAnnotationView view) {
				MKPinAnnotationView pa = (MKPinAnnotationView) view;
				pa.setPinColor(MKPinAnnotationColor.Red);
			}

			@Override
			public MKAnnotationView mapViewViewForAnnotation(MKMapView mapView,
															 Object annotation) {
				MKPinAnnotationView view = (MKPinAnnotationView) mapView
						.dequeueReusableAnnotationViewWithIdentifier(PointAnnotationViewID);
				if (view == null) {
					view = MKPinAnnotationView.alloc()
							.initWithAnnotationReuseIdentifier(annotation,
									PointAnnotationViewID);
				} else {
					view.setAnnotation(annotation);
				}
				view.setAnimatesDrop(true);
				return view;
			}

		});
		view().addSubview(mapView);

		pinLabelField.setReturnKeyType(UIReturnKeyType.Done);
		pinLabelField.setBorderStyle(UITextBorderStyle.RoundedRect);
		pinLabelField.setDelegate(tfdelegate);
		view().addSubview(pinLabelField);

		UIButton addPinBtn = UIButton.buttonWithType(UIButtonType.RoundedRect);
		addPinBtn.setTranslatesAutoresizingMaskIntoConstraints(false);
		addPinBtn.setTitleForState("+", UIControlState.Normal);
		views.put("addPinBtn", addPinBtn);
		view().addSubview(addPinBtn);

		addPinBtn.addTargetActionForControlEvents(this, new SEL(
				"annotationAction:"), UIControlEvents.TouchUpInside);
		view().addSubview(addPinBtn);

		UIButton remPinBtn = UIButton.buttonWithType(UIButtonType.RoundedRect);
		remPinBtn.setTranslatesAutoresizingMaskIntoConstraints(false);
		remPinBtn.setTitleForState("-", UIControlState.Normal);
		views.put("remPinBtn", remPinBtn);
		view().addSubview(remPinBtn);

		remPinBtn.addTargetActionForControlEvents(this, new SEL(
				"annotationAction:"), UIControlEvents.TouchUpInside);
		view().addSubview(remPinBtn);

		constraints.addObjectsFromArray(NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews("H:|-[pinLabel]-[addPinBtn]-[remPinBtn]-|",
						0, null, views));
		constraints.addObjectsFromArray(NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews("H:|-0-[map]-0-|",
						0, null, views));
		constraints.addObjectsFromArray(NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews("V:[topGuide]-[pinLabel]-[map]-0-[bottomGuide]",
						0, null, views));

		view().addConstraints(constraints);
		view().layoutSubviews();
	}

	@Selector("annotationAction:")
	public void annotationAction(UIButton sender) {
		if (sender.titleLabel().text().equals("+")) {
			MKPointAnnotation pa = MKPointAnnotation.alloc().init();
			pa.setTitle(fieldRef.get().text());
			pa.setCoordinate(mapRef.get().centerCoordinate());
			mapRef.get().addAnnotation(pa);
		} else {
			mapRef.get().removeAnnotations(mapRef.get().selectedAnnotations());
		}
	}

}
