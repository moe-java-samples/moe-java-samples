package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSAutocompleteTableDataSourceDelegate;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.NFloat;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ann.UncertainArgument;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.coregraphics.struct.CGPoint;
import ios.foundation.NSArray;
import ios.foundation.NSIndexPath;
import ios.uikit.UIFocusAnimationCoordinator;
import ios.uikit.UIScrollView;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewFocusUpdateContext;
import ios.uikit.UITableViewRowAction;
import ios.uikit.UIView;
import ios.uikit.protocol.UITableViewDataSource;
import ios.uikit.protocol.UITableViewDelegate;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSAutocompleteTableDataSource extends NSObject implements
		UITableViewDataSource, UITableViewDelegate {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAutocompleteTableDataSource(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAutocompleteTableDataSource alloc();

	@Generated
	@Selector("autocompleteBounds")
	public native GMSCoordinateBounds autocompleteBounds();

	@Generated
	@Selector("autocompleteFilter")
	public native GMSAutocompleteFilter autocompleteFilter();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native GMSAutocompleteTableDataSourceDelegate delegate();

	@Generated
	@IsOptional
	@Selector("indexPathForPreferredFocusedViewInTableView:")
	public native NSIndexPath indexPathForPreferredFocusedViewInTableView(
			UITableView tableView);

	@Generated
	@Selector("init")
	public native GMSAutocompleteTableDataSource init();

	@Generated
	@IsOptional
	@Selector("numberOfSectionsInTableView:")
	@NInt
	public native long numberOfSectionsInTableView(UITableView tableView);

	@Generated
	@IsOptional
	@Selector("scrollViewDidEndDecelerating:")
	public native void scrollViewDidEndDecelerating(UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewDidEndDragging:willDecelerate:")
	public native void scrollViewDidEndDraggingWillDecelerate(
			UIScrollView scrollView, boolean decelerate);

	@Generated
	@IsOptional
	@Selector("scrollViewDidEndScrollingAnimation:")
	public native void scrollViewDidEndScrollingAnimation(
			UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewDidEndZooming:withView:atScale:")
	public native void scrollViewDidEndZoomingWithViewAtScale(
			UIScrollView scrollView, UIView view, @NFloat double scale);

	@Generated
	@IsOptional
	@Selector("scrollViewDidScroll:")
	public native void scrollViewDidScroll(UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewDidScrollToTop:")
	public native void scrollViewDidScrollToTop(UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewDidZoom:")
	public native void scrollViewDidZoom(UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewShouldScrollToTop:")
	public native boolean scrollViewShouldScrollToTop(UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewWillBeginDecelerating:")
	public native void scrollViewWillBeginDecelerating(UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewWillBeginDragging:")
	public native void scrollViewWillBeginDragging(UIScrollView scrollView);

	@Generated
	@IsOptional
	@Selector("scrollViewWillBeginZooming:withView:")
	public native void scrollViewWillBeginZoomingWithView(
			UIScrollView scrollView, UIView view);

	@Generated
	@IsOptional
	@Selector("scrollViewWillEndDragging:withVelocity:targetContentOffset:")
	public native void scrollViewWillEndDraggingWithVelocityTargetContentOffset(
			UIScrollView scrollView,
			@ByValue CGPoint velocity,
			@UncertainArgument("Options: reference, array Fallback: reference") CGPoint targetContentOffset);

	@Generated
	@IsOptional
	@Selector("sectionIndexTitlesForTableView:")
	public native NSArray<? extends String> sectionIndexTitlesForTableView(
			UITableView tableView);

	@Generated
	@Selector("setAutocompleteBounds:")
	public native void setAutocompleteBounds(GMSCoordinateBounds value);

	@Generated
	@Selector("setAutocompleteFilter:")
	public native void setAutocompleteFilter(GMSAutocompleteFilter value);

	@Generated
	@Selector("setDelegate:")
	public native void setDelegate_unsafe(
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteTableDataSourceDelegate value);

	@Generated
	public void setDelegate(
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteTableDataSourceDelegate value) {
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
	@Selector("sourceTextHasChanged:")
	public native void sourceTextHasChanged(String text);

	@Generated
	@IsOptional
	@Selector("tableView:accessoryButtonTappedForRowWithIndexPath:")
	public native void tableViewAccessoryButtonTappedForRowWithIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Deprecated
	@Selector("tableView:accessoryTypeForRowWithIndexPath:")
	@NInt
	public native long tableViewAccessoryTypeForRowWithIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:canEditRowAtIndexPath:")
	public native boolean tableViewCanEditRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:canFocusRowAtIndexPath:")
	public native boolean tableViewCanFocusRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:canMoveRowAtIndexPath:")
	public native boolean tableViewCanMoveRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:canPerformAction:forRowAtIndexPath:withSender:")
	public native boolean tableViewCanPerformActionForRowAtIndexPathWithSender(
			UITableView tableView, SEL action, NSIndexPath indexPath,
			@Mapped(ObjCObjectMapper.class) Object sender);

	@Generated
	@Selector("tableView:cellForRowAtIndexPath:")
	public native UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:commitEditingStyle:forRowAtIndexPath:")
	public native void tableViewCommitEditingStyleForRowAtIndexPath(
			UITableView tableView, @NInt long editingStyle,
			NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:didDeselectRowAtIndexPath:")
	public native void tableViewDidDeselectRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:didEndDisplayingCell:forRowAtIndexPath:")
	public native void tableViewDidEndDisplayingCellForRowAtIndexPath(
			UITableView tableView, UITableViewCell cell, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:didEndDisplayingFooterView:forSection:")
	public native void tableViewDidEndDisplayingFooterViewForSection(
			UITableView tableView, UIView view, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:didEndDisplayingHeaderView:forSection:")
	public native void tableViewDidEndDisplayingHeaderViewForSection(
			UITableView tableView, UIView view, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:didEndEditingRowAtIndexPath:")
	public native void tableViewDidEndEditingRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:didHighlightRowAtIndexPath:")
	public native void tableViewDidHighlightRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:didSelectRowAtIndexPath:")
	public native void tableViewDidSelectRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:didUnhighlightRowAtIndexPath:")
	public native void tableViewDidUnhighlightRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:didUpdateFocusInContext:withAnimationCoordinator:")
	public native void tableViewDidUpdateFocusInContextWithAnimationCoordinator(
			UITableView tableView, UITableViewFocusUpdateContext context,
			UIFocusAnimationCoordinator coordinator);

	@Generated
	@IsOptional
	@Selector("tableView:editActionsForRowAtIndexPath:")
	public native NSArray<? extends UITableViewRowAction> tableViewEditActionsForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:editingStyleForRowAtIndexPath:")
	@NInt
	public native long tableViewEditingStyleForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:estimatedHeightForFooterInSection:")
	@NFloat
	public native double tableViewEstimatedHeightForFooterInSection(
			UITableView tableView, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:estimatedHeightForHeaderInSection:")
	@NFloat
	public native double tableViewEstimatedHeightForHeaderInSection(
			UITableView tableView, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:estimatedHeightForRowAtIndexPath:")
	@NFloat
	public native double tableViewEstimatedHeightForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:heightForFooterInSection:")
	@NFloat
	public native double tableViewHeightForFooterInSection(
			UITableView tableView, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:heightForHeaderInSection:")
	@NFloat
	public native double tableViewHeightForHeaderInSection(
			UITableView tableView, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:heightForRowAtIndexPath:")
	@NFloat
	public native double tableViewHeightForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:indentationLevelForRowAtIndexPath:")
	@NInt
	public native long tableViewIndentationLevelForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:moveRowAtIndexPath:toIndexPath:")
	public native void tableViewMoveRowAtIndexPathToIndexPath(
			UITableView tableView, NSIndexPath sourceIndexPath,
			NSIndexPath destinationIndexPath);

	@Generated
	@Selector("tableView:numberOfRowsInSection:")
	@NInt
	public native long tableViewNumberOfRowsInSection(UITableView tableView,
			@NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:performAction:forRowAtIndexPath:withSender:")
	public native void tableViewPerformActionForRowAtIndexPathWithSender(
			UITableView tableView, SEL action, NSIndexPath indexPath,
			@Mapped(ObjCObjectMapper.class) Object sender);

	@Generated
	@IsOptional
	@Selector("tableView:sectionForSectionIndexTitle:atIndex:")
	@NInt
	public native long tableViewSectionForSectionIndexTitleAtIndex(
			UITableView tableView, String title, @NInt long index);

	@Generated
	@IsOptional
	@Selector("tableView:shouldHighlightRowAtIndexPath:")
	public native boolean tableViewShouldHighlightRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:shouldIndentWhileEditingRowAtIndexPath:")
	public native boolean tableViewShouldIndentWhileEditingRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:shouldShowMenuForRowAtIndexPath:")
	public native boolean tableViewShouldShowMenuForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:shouldUpdateFocusInContext:")
	public native boolean tableViewShouldUpdateFocusInContext(
			UITableView tableView, UITableViewFocusUpdateContext context);

	@Generated
	@IsOptional
	@Selector("tableView:targetIndexPathForMoveFromRowAtIndexPath:toProposedIndexPath:")
	public native NSIndexPath tableViewTargetIndexPathForMoveFromRowAtIndexPathToProposedIndexPath(
			UITableView tableView, NSIndexPath sourceIndexPath,
			NSIndexPath proposedDestinationIndexPath);

	@Generated
	@IsOptional
	@Selector("tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:")
	public native String tableViewTitleForDeleteConfirmationButtonForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:titleForFooterInSection:")
	public native String tableViewTitleForFooterInSection(
			UITableView tableView, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:titleForHeaderInSection:")
	public native String tableViewTitleForHeaderInSection(
			UITableView tableView, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:viewForFooterInSection:")
	public native UIView tableViewViewForFooterInSection(UITableView tableView,
			@NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:viewForHeaderInSection:")
	public native UIView tableViewViewForHeaderInSection(UITableView tableView,
			@NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:willBeginEditingRowAtIndexPath:")
	public native void tableViewWillBeginEditingRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:willDeselectRowAtIndexPath:")
	public native NSIndexPath tableViewWillDeselectRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:willDisplayCell:forRowAtIndexPath:")
	public native void tableViewWillDisplayCellForRowAtIndexPath(
			UITableView tableView, UITableViewCell cell, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("tableView:willDisplayFooterView:forSection:")
	public native void tableViewWillDisplayFooterViewForSection(
			UITableView tableView, UIView view, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:willDisplayHeaderView:forSection:")
	public native void tableViewWillDisplayHeaderViewForSection(
			UITableView tableView, UIView view, @NInt long section);

	@Generated
	@IsOptional
	@Selector("tableView:willSelectRowAtIndexPath:")
	public native NSIndexPath tableViewWillSelectRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath);

	@Generated
	@IsOptional
	@Selector("viewForZoomingInScrollView:")
	public native UIView viewForZoomingInScrollView(UIScrollView scrollView);
}