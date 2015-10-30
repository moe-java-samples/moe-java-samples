package com.intel.inde.moe.samples.webbrowser.ios;

import ios.NSObject;
import ios.coregraphics.struct.CGRect;
import ios.foundation.NSDictionary;
import ios.uikit.UIApplication;
import ios.uikit.UINavigationController;
import ios.uikit.UIScreen;
import ios.uikit.UIViewController;
import ios.uikit.UIWindow;
import ios.uikit.c.UIKit;
import ios.uikit.protocol.UIApplicationDelegate;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ann.Selector;

@RegisterOnStartup
public class Main extends NSObject implements UIApplicationDelegate {

    public static void main(String[] args) {
        UIKit.UIApplicationMain(0, null, null, Main.class.getName());
    }

    @Selector("alloc")
    public static native Main alloc();

    protected Main(Pointer peer) {
        super(peer);
    }

    private UIWindow window;

    @Override
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        UIViewController vc = BrowserController.alloc().init();
        UINavigationController navigationController = UINavigationController.alloc().init();

        navigationController.initWithRootViewController(vc);

        UIScreen screen = UIScreen.mainScreen();

        CGRect bounds = screen.bounds();
        window = UIWindow.alloc().initWithFrame(bounds);

        window.setRootViewController(navigationController);

        window.makeKeyAndVisible();

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
}
