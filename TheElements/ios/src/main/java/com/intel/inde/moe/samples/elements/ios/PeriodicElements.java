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

package com.intel.inde.moe.samples.elements.ios;

import com.intel.inde.moe.samples.elements.common.AtomicElement;
import com.intel.inde.moe.samples.elements.common.ElementComparatorByAtomicNumber;
import com.intel.inde.moe.samples.elements.common.ElementComparatorByState;
import com.intel.inde.moe.samples.elements.common.ElementComparatorBySymbol;

import com.longevitysoft.android.xml.plist.PListXMLHandler;
import com.longevitysoft.android.xml.plist.PListXMLParser;
import com.longevitysoft.android.xml.plist.domain.Array;
import com.longevitysoft.android.xml.plist.domain.Dict;
import com.longevitysoft.android.xml.plist.domain.PList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ios.coregraphics.c.CoreGraphics;
import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGRect;
import ios.coregraphics.struct.CGSize;
import ios.foundation.NSBundle;
import ios.foundation.NSString;
import ios.uikit.UIColor;
import ios.uikit.UIFont;
import ios.uikit.UIImage;
import ios.uikit.c.UIKit;

public class PeriodicElements {

    // We use the singleton approach, one collection for the entire application
    public static List<AtomicElement> elements = null; // Original, sorted by name
    public static List<AtomicElement> elementsSortedByAtomicNumber = null;
    public static List<AtomicElement> elementsSortedBySymbol = null;
    public static List<AtomicElement> elementSortedByState = null;
    // sharedPeriodicElementsInstance

    public static void setupElementsArray() {
        NSBundle mainBundle = NSBundle.mainBundle();
        String pathToElements = mainBundle.pathForResourceOfType("Elements", "xml");

        if (pathToElements != null) {
            NSString plist = NSString.stringWithContentsOfFileEncodingError(
                    pathToElements,
                    4, // NSStringEncoding.UTF8StringEncoding
                    null
            );
            if (plist != null) {
                PListXMLHandler handler = new PListXMLHandler();
                PListXMLParser parser = new PListXMLParser();
                parser.setHandler(handler);
                parser.parse(plist.toString());
                PList actualPList = ((PListXMLHandler) parser.getHandler()).getPlist();
                Array array = (Array) actualPList.getRootElement();
                int size = array.size();

                elements = new ArrayList<>(size);
                for (int i = 0; i < size; ++i) {
                    Dict cat = (Dict) array.get(i);
                    AtomicElement element = new AtomicElement(cat);
                    elements.add(element);
                }

                // Sorting
                elementsSortedByAtomicNumber = new ArrayList<>(elements);
                Collections.sort(elementsSortedByAtomicNumber, new ElementComparatorByAtomicNumber());

                elementsSortedBySymbol = new ArrayList<>(elements);
                Collections.sort(elementsSortedBySymbol, new ElementComparatorBySymbol());

                elementSortedByState = new ArrayList<>(elements);
                Collections.sort(elementSortedByState, new ElementComparatorByState());
            }
        }
    }

    static UIImage stateImageForAtomicElementView(String state) {
        String stateImage;

        switch (state) {
            case "Artificial":
                stateImage = "Artificial_256.png";
                break;
            case "Gas":
                stateImage = "Gas_256.png";
                break;
            case "Liquid":
                stateImage = "Liquid_256.png";
                break;
            default:
                stateImage = "Solid_256.png";
                break;
        }

        return UIImage.imageNamed(stateImage);
    }

    static UIImage stateImageForAtomicElementTileView(String state) {
        String stateImage;

        switch (state) {
            case "Artificial":
                stateImage = "artificial37.png";
                break;
            case "Gas":
                stateImage = "gas37.png";
                break;
            case "Liquid":
                stateImage = "liquid37.png";
                break;
            default:
                stateImage = "solid37.png";
                break;
        }

        return UIImage.imageNamed(stateImage);
    }

    public static UIImage flipperImageForAtomicElementNavigationItem(AtomicElement element) {
        // Return a 30 x 30 image that is a reduced version
        // of the AtomicElementTileView content
        // this is used to display the flipper button in the navigation bar
        CGSize itemSize = CoreGraphics.CGSizeMake(30.0, 30.0);
        UIKit.UIGraphicsBeginImageContext(itemSize);

        UIImage backgroundImage = PeriodicElements.stateImageForAtomicElementTileView(element.getState());
        CGRect elementSymbolRectangle = CoreGraphics.CGRectMake(0, 0,
                backgroundImage.size().width(), backgroundImage.size().height()
        );
        backgroundImage.drawInRect(elementSymbolRectangle);

        UIColor.whiteColor().set();

        // Draw the element number
        UIFont font = UIFont.boldSystemFontOfSize(8);
        CGPoint point = CoreGraphics.CGPointMake(2, 1);
        int n = element.getAtomicNumber();
        NSString atomicNumber =  NSString.stringWithString(Integer.toString(n));
        atomicNumber.drawAtPointWithFont(point, font);

        // Draw the element symbol
        font = UIFont.boldSystemFontOfSize(13);
        NSString symbol =  NSString.stringWithString(element.getSymbol());
        CGSize stringSize = symbol.sizeWithFont(font);
        point = CoreGraphics.CGPointMake((elementSymbolRectangle.size().width() - stringSize.width()) / 2, 14.0);
        symbol.drawAtPointWithFont(point, font);

        UIImage theImage = UIKit.UIGraphicsGetImageFromCurrentImageContext();
        UIKit.UIGraphicsEndImageContext();
        return theImage;
    }
}
