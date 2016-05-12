package com.intel.moe.samples.notetaking.ios;

import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.Mapped;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Property;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.natj.objc.map.ObjCObjectMapper;
import com.intel.moe.samples.notetaking.common.Data;

import ios.foundation.NSArray;
import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.uikit.UITextView;
import ios.uikit.UIViewController;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("DetailViewController")
@RegisterOnStartup
public class DetailViewController extends UIViewController {
    static {
        NatJ.register();
    }

    public Data data;
    private String detailItem = "";
    private UITextView dataText;

    protected DetailViewController(Pointer peer) {
        super(peer);
    }

    public static native DetailViewController alloc();

    @Override
    public native DetailViewController init();

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        dataText = noteView();
        // Do any additional setup after loading the view, typically from a nib.

        configureView();
    }

    public void configureView() {
        // Update the user interface for the detail item.
        if(data != null) {
            String currentNote = data.getAllNotes().get(detailItem);
            if (!currentNote.equals("New Note")) {
                dataText.setText(currentNote);
            } else {
                dataText.setText("");
            }
        }
        noteView().becomeFirstResponder();

    }

    public void setDetailItem(String newDetailItem, Data d) {
        if (!detailItem.equals(newDetailItem)) {
            detailItem = newDetailItem;
            data = d;
            data.setCurrentKey(newDetailItem);
        }
    }

    public String getDetailItem() {
        return detailItem;
    }

    @Generated
    public native DetailViewController initWithNibNameBundle(
            String nibNameOrNil, NSBundle nibBundleOrNil);

    @Generated
    public native DetailViewController initWithCoder(NSCoder aDecoder);

    @Generated
    @Property
    @Selector("noteView")
    public native UITextView noteView();

    @Generated
    @Selector("setNoteView:")
    public native void setNoteView_unsafe(UITextView value);

    @Generated
    public void setNoteView(UITextView value) {
        com.intel.moe.natj.objc.ObjCObject __old = (com.intel.moe.natj.objc.ObjCObject) noteView();
        if (value != null) {
            com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
                    value);
        }
        setNoteView_unsafe(value);
        if (__old != null) {
            com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
                    __old);
        }
    }

    @Selector("doSaveNote:")
    public void doSaveNote( Object sender){
        if(data != null) {
            if (!dataText.text().isEmpty()) {
                data.setNoteForCurrentKey(dataText.text());
            } else {
                data.removeCurrentNote();
            }
        }
    }
}
