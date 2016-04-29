package com.intel.moe.samples.notetaking.ios;

import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;

import ios.uikit.UISplitViewController;
import ios.uikit.enums.UIStatusBarStyle;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("SplitViewController")
@RegisterOnStartup
public class SplitViewController extends UISplitViewController {

    protected SplitViewController(Pointer peer) {
        super(peer);
    }

    public static native SplitViewController alloc();

    @Override
    public native SplitViewController init();

    @Override
    public void viewDidLoad() {

    }

    @Override
    public long preferredStatusBarStyle() {

        return UIStatusBarStyle.LightContent;
    }
}
