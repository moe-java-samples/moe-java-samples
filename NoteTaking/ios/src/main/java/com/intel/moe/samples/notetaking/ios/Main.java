package com.intel.moe.samples.notetaking.ios;

import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.Class;
import com.intel.moe.natj.objc.ann.Selector;

import ios.NSObject;
import ios.foundation.NSDictionary;
import ios.uikit.UIApplication;
import ios.uikit.UINavigationController;
import ios.uikit.UISplitViewController;
import ios.uikit.UIViewController;
import ios.uikit.UIWindow;
import ios.uikit.c.UIKit;
import ios.uikit.protocol.UIApplicationDelegate;
import ios.uikit.protocol.UISplitViewControllerDelegate;

@RegisterOnStartup
public class Main extends NSObject implements UIApplicationDelegate, UISplitViewControllerDelegate {

    static {
        NatJ.register();
    }

    private UIWindow window;

    @Generated("NatJ")
    protected Main(Pointer peer) {
        super(peer);
    }

    public static void main(String[] args) {
        UIKit.UIApplicationMain(0, null, null, Main.class.getName());
    }

    public static native Main alloc();

    @Override
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        // Override point for customization after application launch.
        UISplitViewController splitViewController = (UISplitViewController) window.rootViewController();
        UINavigationController navigationController = (UINavigationController) splitViewController.viewControllers().lastObject();

        navigationController.topViewController().navigationItem().setLeftBarButtonItem(splitViewController.displayModeButtonItem());

        splitViewController.setDelegate(this);
        return true;
    }

    @Override
    public void setWindow(UIWindow value) {
        window = value;
    }

    @Override
    public UIWindow window() {
        return window;
    }

    @Override
    @Generated
    public boolean splitViewControllerCollapseSecondaryViewControllerOntoPrimaryViewController(UISplitViewController splitViewController, UIViewController secondaryViewController, UIViewController primaryViewController) {
        if (secondaryViewController.isKindOfClass(com.intel.moe.natj.objc.Class.fromJavaClass(UINavigationController.class)) &&
                ((UINavigationController) secondaryViewController).topViewController().isKindOfClass(Class.fromJavaClass(DetailViewController.class)) &&
                ((DetailViewController) ((UINavigationController) secondaryViewController).topViewController()).getDetailItem() == "") {
            // Return YES to indicate that we have handled the collapse by doing nothing; the secondary controller will be discarded.
            return true;
        }
        return false;
    }
}
