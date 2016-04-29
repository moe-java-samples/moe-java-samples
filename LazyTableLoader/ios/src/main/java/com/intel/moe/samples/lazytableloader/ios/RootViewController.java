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

package com.intel.moe.samples.lazytableloader.ios;

import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.*;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.samples.lazytableloader.common.AppRecord;
import com.intel.moe.samples.lazytableloader.common.CompletionHandler;
import com.intel.moe.samples.lazytableloader.common.Constants;
import com.intel.moe.samples.lazytableloader.common.IconDownloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ios.foundation.NSIndexPath;
import ios.foundation.c.Foundation;
import ios.foundation.enums.NSSearchPathDirectory;
import ios.foundation.enums.NSSearchPathDomainMask;
import ios.uikit.UIImage;
import ios.uikit.UITableView;
import ios.uikit.UITableViewController;

import static com.intel.moe.natj.objc.Class.fromJavaClass;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("RootViewController")
@RegisterOnStartup
public class RootViewController extends UITableViewController {

    protected RootViewController(Pointer peer) {
        super(peer);
    }

    @Owned
    @Selector("alloc")
    public static native RootViewController alloc();

    @Selector("init")
    public native RootViewController init();

    private List<AppRecord> entries = new ArrayList<>();

    public void viewDidLoad()
    {
        super.viewDidLoad();
        setTitle(Constants.TitleName);
        tableView().registerClassForCellReuseIdentifier(fromJavaClass(MyTableViewCell.class), Constants.CellIdentifier);
        tableView().registerClassForCellReuseIdentifier(fromJavaClass(MyTableViewCell.class),
                Constants.PalceholderCellIdentifier);

    }

    public long tableViewNumberOfRowsInSection(UITableView var1, long var2)
    {
        long count = 0;
        if(entries != null) count = entries.size();

        if(count == 0)
        {
            return Constants.kCustomRowCount;
        }

        return count;
    }

    public  MyTableViewCell tableViewCellForRowAtIndexPath(UITableView var1, NSIndexPath var2)
    {
        MyTableViewCell cell;
        long nodeCount = 0;
        if(entries != null) nodeCount = entries.size();

        if (nodeCount == 0 && var2.row() == 0)
        {
            // add a placeholder cell while waiting on table data
            cell = (MyTableViewCell)tableView().dequeueReusableCellWithIdentifierForIndexPath(Constants.PalceholderCellIdentifier, var2);

            cell.detailTextLabel().setText("Loading…");
        }
        else
        {
            cell = (MyTableViewCell) tableView().dequeueReusableCellWithIdentifierForIndexPath(Constants.CellIdentifier, var2);

            // Leave cells empty if there's no data yet
            if (nodeCount > 0)
            {
                // Set up the cell representing the app
                AppRecord appRecord = entries.get((int) var2.row()) ;

                cell.textLabel().setText(appRecord.getAppName());
                cell.detailTextLabel().setText(appRecord.getArtist());

                // Only load cached images; defer new downloads until scrolling ends
                if (appRecord.getAppIcon() == null)
                {
                    if (!tableView().isDragging() && !tableView().isDecelerating())
                    {
                        startIconDownloadForIndexPath(appRecord ,var2);
                    }
                    // if a download is deferred or in progress, return a placeholder image
                    cell.imageView().setImage(UIImage.imageNamed("Placeholder.png"));
                }
                else
                {
                    //cell.imageView().setImage(appRecord.getAppIcon());
                    UIImage image = getUIImageFromAppRecord((int) var2.row());
                    if (image == null) {
                        cell.imageView().setImage(UIImage.imageNamed("Placeholder.png"));
                    } else {
                        cell.imageView().setImage(image);
                    }
                }
            }
        }

        return cell;
    }

    public void startIconDownloadForIndexPath(final AppRecord appRecord, final NSIndexPath indexPath)
    {
        IconDownloader iconDownloader = new IconDownloader();
        iconDownloader.setAppRecord(appRecord);
        iconDownloader.setCompletionHandler(new CompletionHandler() {

            public void completeAction()
            {
                MyTableViewCell cell = (MyTableViewCell)tableView().cellForRowAtIndexPath(indexPath);
                if (cell != null) {

                    UIImage image = getUIImageFromAppRecord((int) indexPath.row());
                    if (image == null) {
                        cell.imageView().setImage(UIImage.imageNamed("Placeholder.png"));
                    } else {
                        cell.imageView().setImage(image);
                    }
                }
            }
        });

        final IconDownloader finalIconDownloader = iconDownloader;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                finalIconDownloader.downloadImage();
            }
        });
        thread.start();
    }

    private UIImage getUIImageFromAppRecord(int index) {
        AppRecord appRecord = entries.get(index);
        String fileRootPath = Foundation.NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask, true).get(0);
        String imageName = fileRootPath + "/image_" + String.valueOf(index) + ".png";

        File file = new File(imageName);
        file.delete();

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imageName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            fos.write(appRecord.getAppIcon().getImage());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                fos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }

        UIImage image = UIImage.imageWithContentsOfFile(imageName);

        return image;
    }

    public void setEntries(List<AppRecord> entries) {
        this.entries = entries;

        for (int i = 0; i < this.entries.size(); i++) {
            AppRecord appRecord = this.entries.get(i);
            final IconDownloader iconDownloader = new IconDownloader();
            iconDownloader.setAppRecord(appRecord);
            final int finalI = i;
            iconDownloader.setCompletionHandler(new CompletionHandler() {

                public void completeAction() {

                    NSIndexPath indexPath = NSIndexPath.indexPathForRowInSection(finalI, 0);

                    MyTableViewCell cell = (MyTableViewCell) tableView().cellForRowAtIndexPath(indexPath);
                    if (cell != null) {

                        UIImage image = getUIImageFromAppRecord((int) indexPath.row());
                        if (image == null) {
                            cell.imageView().setImage(UIImage.imageNamed("Placeholder.png"));
                        } else {
                            cell.imageView().setImage(image);
                        }
                    }
                }
            });

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    iconDownloader.downloadImage();
                }
            });
            thread.start();
        }
    }
}
