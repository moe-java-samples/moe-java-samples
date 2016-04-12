package com.intel.moe.samples.notetaking.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.uikit.UISplitViewController;
import ios.uikit.enums.UIStatusBarStyle;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("SplitViewController")
@RegisterOnStartup
public class SplitViewController extends UISplitViewController {

    protected SplitViewController(Pointer peer) {
        super(peer);
    }

    @Owned
    @Selector("alloc")
    public static native SplitViewController alloc();

    @Selector("init")
    public native SplitViewController init();

    @Override
    @Selector("viewDidLoad")
    public void viewDidLoad() {

    }

    @Override
    @Selector("preferredStatusBarStyle")
    public long preferredStatusBarStyle() {

        return UIStatusBarStyle.LightContent;
    }
}
