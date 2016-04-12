package com.intel.moe.samples.notetaking.ios;

import com.intel.moe.samples.notetaking.common.Data;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;

import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.uikit.UITextView;
import ios.uikit.UIViewController;

@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("DetailViewController")
@RegisterOnStartup
public class DetailViewController extends UIViewController {
    static {
        NatJ.register();
    }

   // private DetailViewController detailViewController;

    protected DetailViewController(Pointer peer) {
        super(peer);
    }

    private String detailItem = "";

    public Data data;
    @Owned
    @Selector("alloc")
    public static native DetailViewController alloc();

    @Selector("init")
    public native DetailViewController init();

    private UITextView dataText;


    @Override
    @Selector("viewDidLoad")
    public void viewDidLoad() {
        super.viewDidLoad();

        dataText = noteView();
        // Do any additional setup after loading the view, typically from a nib.
        configureView();
    }

    public void configureView() {
        // Update the user interface for the detail item.
        String currentNote = data.getAllNotes().get(detailItem);
        if (!currentNote.equals("New Note")) {
            dataText.setText(currentNote);
        }else{
            dataText.setText("");
        }
        noteView().becomeFirstResponder();

    }

    public void viewWillDisappear(boolean animated){
        if(!dataText.text().isEmpty()){
            data.setNoteForCurrentKey(dataText.text());
        }else{
            data.removeCurrentNote();
        }
    }


    public void setDetailItem(String newDetailItem, Data d) {
        if (!detailItem.equals(newDetailItem)) {
            detailItem = newDetailItem;
            data =d;
            data.setCurrentKey(newDetailItem);

        }
    }

    public String getDetailItem() {
        return detailItem;
    }

    @Generated
    @Selector("initWithNibName:bundle:")
    public native DetailViewController initWithNibNameBundle(
            String nibNameOrNil, NSBundle nibBundleOrNil);

    @Generated
	@Selector("initWithCoder:")
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
		com.intel.inde.moe.natj.objc.ObjCObject __old = (com.intel.inde.moe.natj.objc.ObjCObject) noteView();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setNoteView_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}
}
