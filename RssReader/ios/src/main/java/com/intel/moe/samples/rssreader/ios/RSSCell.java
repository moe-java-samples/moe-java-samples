package com.intel.moe.samples.rssreader.ios;

import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.ByValue;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.NInt;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.general.ann.Runtime;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Property;
import com.intel.moe.natj.objc.ann.Selector;

import ios.coregraphics.struct.CGRect;
import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;
import ios.uikit.UITextView;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("RSSCell")
@RegisterOnStartup
public class RSSCell extends UITableViewCell {

	@Generated
	protected RSSCell(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native RSSCell alloc();

	@Generated
	@Selector("init")
	public native RSSCell init();

	@Generated
	@Property
	@Selector("rssDate")
	public native UILabel rssDate();

	@Generated
	@Property
	@Selector("rssDescription")
	public native UITextView rssDescription();

	@Generated
	@Property
	@Selector("rssTitle")
	public native UILabel rssTitle();

	@Generated
	@Property
	@Selector("setRssDate:")
	public native void setRssDate(UILabel value);

	@Generated
	@Property
	@Selector("setRssDescription:")
	public native void setRssDescription(UITextView value);

	@Generated
	@Property
	@Selector("setRssTitle:")
	public native void setRssTitle(UILabel value);
}