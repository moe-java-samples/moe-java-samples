// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.intel.wordsearch.ui;

import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ptr.Ptr;
import com.intel.moe.natj.general.ptr.impl.PtrFactory;
import com.intel.moe.natj.objc.SEL;
import com.intel.moe.natj.objc.ann.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ios.foundation.NSBundle;
import ios.foundation.NSError;
import ios.foundation.NSIndexPath;
import ios.foundation.NSMutableArray;
import ios.foundation.NSMutableDictionary;
import ios.foundation.NSString;
import ios.foundation.enums.Enums;
import ios.foundation.struct.NSRange;
import ios.uikit.NSLayoutConstraint;
import ios.uikit.UIAlertView;
import ios.uikit.UIColor;
import ios.uikit.UISegmentedControl;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.enums.NSLayoutAttribute;
import ios.uikit.enums.NSLayoutRelation;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UILayoutConstraintAxis;
import ios.uikit.enums.UIRectEdge;
import ios.uikit.enums.UIReturnKeyType;
import ios.uikit.enums.UITextAutocapitalizationType;
import ios.uikit.enums.UITextAutocorrectionType;
import ios.uikit.enums.UITextBorderStyle;
import ios.uikit.enums.UITextFieldViewMode;
import ios.uikit.protocol.UITableViewDataSource;
import ios.uikit.protocol.UITextFieldDelegate;

public class WordSearchController extends UIViewController implements
		UITableViewDataSource, UITextFieldDelegate {

	private static final String CELL_IDENTIFIER = "Cell";

	private final NSMutableDictionary views = NSMutableDictionary.alloc().init();
	private final NSMutableArray constraints = NSMutableArray.alloc().init();

	static final class PrefixedWords {
		public static final int STARTS_WITH_MODE = 0;
		public static final int CONTAINS_MODE = 1;
		public static final int ENDS_WITH = 2;

		public final String prefix;
		private final ArrayList<String> words = new ArrayList<String>();

		public PrefixedWords(String prefix, List<String> allwords) {
			this.prefix = prefix;
			for (String word : allwords) {
				if (word.startsWith(prefix)) {
					words.add(word);
				}
			}
			Collections.sort(words);
		}

		private PrefixedWords(String prefix, List<String> allwords,
				String filter, int mode) {
			this.prefix = prefix;
			if (mode == STARTS_WITH_MODE) {
				for (String word : allwords) {
					if (word.startsWith(filter)) {
						words.add(word);
					}
				}
			} else if (mode == CONTAINS_MODE) {
				for (String word : allwords) {
					if (word.indexOf(filter) != -1) {
						words.add(word);
					}
				}
			} else if (mode == ENDS_WITH) {
				for (String word : allwords) {
					if (word.endsWith(filter)) {
						words.add(word);
					}
				}
			} else {
				throw new IllegalArgumentException();
			}
		}

		public int size() {
			return words.size();
		}

		public String get(int index) {
			return words.get(index);
		}

		public PrefixedWords getFiltered(String filter, int mode) {
			if (filter.length() == 0) {
				return this;
			} else {
				return new PrefixedWords(prefix, words, filter, mode);
			}
		}
	}

	private static final boolean OPTIMIZED_FILTERING = true;

	private final ArrayList<PrefixedWords> data = new ArrayList<PrefixedWords>();
	private final ArrayList<PrefixedWords> filtered = new ArrayList<PrefixedWords>();

	private UITableView tableView;

	private String filterString = "";
	private int filterMode = PrefixedWords.STARTS_WITH_MODE;

	public static native WordSearchController alloc();

	@Override
	public native WordSearchController init();

	protected WordSearchController(Pointer peer) {
		super(peer);
	}

	@Override
	public void viewDidLoad() {
		super.viewDidLoad();

		setTitle("Word Search");

		setEdgesForExtendedLayout(UIRectEdge.None);
		view().setBackgroundColor(UIColor.whiteColor());
		this.view().setUserInteractionEnabled(false);

		views.put("bottomGuide", this.bottomLayoutGuide());
		views.put("topGuide", this.topLayoutGuide());

		NSMutableArray modes = NSMutableArray.alloc().init();
		modes.add("Starts With");
		modes.add("Contains");
		modes.add("Ends With");

		// Create views
		tableView = UITableView.alloc().init();
		tableView.setTranslatesAutoresizingMaskIntoConstraints(false);
		view().addSubview(tableView);
		views.put("table", tableView);

		final UITextField filterField = UITextField.alloc().init();
		filterField.setTranslatesAutoresizingMaskIntoConstraints(false);
		view().addSubview(filterField);
		views.put("filter", filterField);

		final UISegmentedControl modeControl = UISegmentedControl.alloc().initWithItems(modes);
		modeControl.setTranslatesAutoresizingMaskIntoConstraints(false);
		view().addSubview(modeControl);
		views.put("modes", modeControl);

		// Set content hugging
		modeControl.setContentHuggingPriorityForAxis(500, UILayoutConstraintAxis.Horizontal);

		// Prepare layout
		constraints.addObjectsFromArray(NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews("H:|-0-[table]-0-|",
						0, null, views));
		constraints.addObjectsFromArray(NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews("H:|-[filter]-|",
						0, null, views));
		constraints.addObjectsFromArray(NSLayoutConstraint
				.constraintsWithVisualFormatOptionsMetricsViews("V:[topGuide]-[filter]-[modes]-[table]-0-[bottomGuide]",
						0, null, views));
		constraints.add(NSLayoutConstraint
				.constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(
						modeControl, NSLayoutAttribute.CenterX, NSLayoutRelation.Equal,
						view(), NSLayoutAttribute.CenterX, 1.0, 0.0));
		view().addConstraints(constraints);
		view().layoutSubviews();

		// Setup tableview
		tableView.registerClassForCellReuseIdentifier(
				new com.intel.moe.natj.objc.Class("UITableViewCell"),
				CELL_IDENTIFIER);
		tableView.setDataSource_unsafe(this);

		// Setup filter field
		filterField.setBorderStyle(UITextBorderStyle.RoundedRect);
		filterField.setClearButtonMode(UITextFieldViewMode.Always);
		filterField.setReturnKeyType(UIReturnKeyType.Done);
		filterField
				.setAutocapitalizationType(UITextAutocapitalizationType.None);
		filterField.setAutocorrectionType(UITextAutocorrectionType.No);
		filterField.setDelegate_unsafe(this);

		// Setup mode control
		modeControl.setSelectedSegmentIndex(1);
		modeChanged(modeControl);
		modeControl.addTargetActionForControlEvents(this, new SEL(
				"modeChanged:"), UIControlEvents.ValueChanged);

		this.performSelectorInBackgroundWithObject(new SEL("loadDictionary"),
				null);
	}

	@Selector("modeChanged:")
	private void modeChanged(UISegmentedControl sender) {
		if (sender.selectedSegmentIndex() == 0) {
			filterMode = PrefixedWords.STARTS_WITH_MODE;
		} else if (sender.selectedSegmentIndex() == 1) {
			filterMode = PrefixedWords.CONTAINS_MODE;
		} else {
			filterMode = PrefixedWords.ENDS_WITH;
		}
		filter(true);
	}

	@Selector("loadDictionary")
	public void loadDictionary() {
		data.clear();
		String path = NSBundle.mainBundle().pathForResourceOfType("US", "dic");
		Ptr<NSError> err = PtrFactory.newObjectPtr(NSError.class, 1, true,
				false);

		NSString source = NSString.stringWithContentsOfFileEncodingError(path,
				Enums.NSASCIIStringEncoding, err);
		if (err.get() != null || source == null || source.length() == 0) {
			this.performSelectorOnMainThreadWithObjectWaitUntilDone(new SEL(
					"loadDictionaryFailed"), null, false);
			return;
		}

		String[] sourceWords = source.toString().split("\n");

		for (char c = 'a'; c <= 'z'; ++c) {
			data.add(new PrefixedWords("" + c, Arrays.asList(sourceWords)));
		}

		this.view().setUserInteractionEnabled(true);
		filter(true);
	}

	public void filter(boolean root) {
//		if (root || !OPTIMIZED_FILTERING) {
			filtered.clear();
			filtered.addAll(data);
//		}

		for (int i = 0; i < filtered.size(); ++i) {
			filtered.set(i,
					filtered.get(i).getFiltered(filterString, filterMode));
		}

		this.performSelectorOnMainThreadWithObjectWaitUntilDone(new SEL(
				"reloadTableViewData"), null, false);
	}

	@Selector("loadDictionaryFailed")
	public void loadDictionaryFailed() {
		UIAlertView alert = UIAlertView.alloc().init();
		alert.setTitle("An error occurred");
		alert.setMessage("Couldn't load dictionary file");
		alert.addButtonWithTitle("Close");
		alert.show();
	}

	@Selector("reloadTableViewData")
	public void reloadTableViewData() {
		tableView.reloadData();
	}

	@Override
	public long numberOfSectionsInTableView(UITableView tableView) {
		int count = 0;
		for (PrefixedWords pw : filtered) {
			if (pw.size() > 0) {
				++count;
			}
		}
		return count;
	}

	private PrefixedWords getFilteredForSection(long section) {
		for (PrefixedWords pw : filtered) {
			if (pw.size() > 0) {
				if (section == 0) {
					return pw;
				}
				--section;
			}
		}
		return null;
	}

	@Override
	public UITableViewCell tableViewCellForRowAtIndexPath(UITableView tableView, NSIndexPath indexPath) {
		UITableViewCell cell = (UITableViewCell) tableView
				.dequeueReusableCellWithIdentifierForIndexPath(CELL_IDENTIFIER,
						indexPath);

		PrefixedWords sectionWords = getFilteredForSection(indexPath.section());
		if (sectionWords == null)
			return null;
		String text = sectionWords.get((int) indexPath.row());
		cell.textLabel().setText(text);

		return cell;
	}

	@Override
	public long tableViewNumberOfRowsInSection(UITableView tableView, long section) {
		PrefixedWords sectionWords = getFilteredForSection(section);
		if (sectionWords == null)
			return 0;
		return sectionWords.size();
	}

	@Override
	public String tableViewTitleForHeaderInSection(UITableView tableView,
			long section) {
		PrefixedWords sectionWords = getFilteredForSection(section);
		if (sectionWords == null)
			return null;
		return sectionWords.prefix.toUpperCase();
	}

	@Override
	public boolean textFieldShouldChangeCharactersInRangeReplacementString(
			UITextField textField, NSRange range, String string) {
		String newfilter = filterString.substring(0, (int)range.location())
				+ string.toLowerCase()
				+ filterString.substring((int)range.location() + (int)range.length(),
						filterString.length());
		boolean doRoot = !newfilter.startsWith(filterString);
		filterString = newfilter;
		filter(doRoot);
		return true;
	}

	@Override
	public boolean textFieldShouldClear(UITextField textField) {
		filterString = "";
		filter(true);
		return true;
	}

	@Override
	public boolean textFieldShouldReturn(UITextField textField) {
		textField.resignFirstResponder();
		return false;
	}
}
