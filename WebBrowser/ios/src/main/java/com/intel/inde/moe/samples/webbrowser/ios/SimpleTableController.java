package com.intel.inde.moe.samples.webbrowser.ios;

import com.intel.inde.moe.natj.general.Pointer;

import java.util.ArrayList;

import ios.foundation.NSArray;
import ios.foundation.NSIndexPath;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.enums.UITableViewRowAnimation;

public class SimpleTableController extends UITableViewController {

	private static final String CELL_IDENTIFIER = "Cell";

	private final ArrayList<String> options = new ArrayList<String>();

	public static interface EventListener {
		public void tableViewDidSelectRow(UITableView tableView, String row);
	}

	private EventListener listener;

	public static native SimpleTableController alloc();

	@Override
	public native SimpleTableController init();

	protected SimpleTableController(Pointer peer) {
		super(peer);
	}

	@Override
	public void viewDidLoad() {
		super.viewDidLoad();

		tableView().registerClassForCellReuseIdentifier(
				new com.intel.inde.moe.natj.objc.Class("UITableViewCell"),
				CELL_IDENTIFIER);
	}

	public void add(String elem) {
		options.add(elem);
		if (tableView() != null) {
			NSIndexPath path = NSIndexPath.indexPathForRowInSection(
					options.size() - 1, 0);
			NSArray paths = NSArray.arrayWithObject(path);
			tableView().insertRowsAtIndexPathsWithRowAnimation(paths,
					UITableViewRowAnimation.Automatic);
		}
	}

	@Override
	public long numberOfSectionsInTableView(UITableView tableView) {
		return 1;
	}

	@Override
	public long tableViewNumberOfRowsInSection(UITableView tableView, long section) {
		return options.size();
	}

	@Override
	public UITableViewCell tableViewCellForRowAtIndexPath(
			UITableView tableView, NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView
				.dequeueReusableCellWithIdentifierForIndexPath(CELL_IDENTIFIER,
						indexPath);

		cell.textLabel().setText(options.get((int)indexPath.row()));

		return cell;
	}

	@Override
	public void tableViewDidSelectRowAtIndexPath(UITableView tableView,
			NSIndexPath indexPath) {
		if (listener != null) {
			listener.tableViewDidSelectRow(tableView,
					options.get((int)indexPath.row()));
		}
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public EventListener getListener() {
		return listener;
	}

	public void setListener(EventListener listener) {
		this.listener = listener;
	}

}
