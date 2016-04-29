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

package com.intel.moe.samples.currencyconverter.ios;

import com.intel.moe.samples.currencyconverter.common.Names;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.ByValue;
import com.intel.moe.natj.general.ann.NInt;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.SEL;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Property;
import com.intel.moe.natj.objc.ann.Selector;

import com.intel.moe.samples.currencyconverter.common.Currency;

import java.math.BigDecimal;

import ios.NSObject;
import ios.c.Globals;
import ios.coregraphics.c.CoreGraphics;
import ios.foundation.NSMutableArray;
import ios.foundation.struct.NSRange;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UILabel;
import ios.uikit.UIPickerView;
import ios.uikit.UITextField;
import ios.uikit.UIToolbar;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.enums.UIBarButtonSystemItem;
import ios.uikit.enums.UIBarStyle;
import ios.uikit.protocol.UIPickerViewDataSource;
import ios.uikit.protocol.UIPickerViewDelegate;
import ios.uikit.protocol.UITextFieldDelegate;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("AppViewController")
@RegisterOnStartup
public class AppViewController extends UIViewController implements UIPickerViewDelegate, UITextFieldDelegate, UIPickerViewDataSource {

    @Owned
    @Selector("alloc")
    public static native AppViewController alloc();

    @Selector("init")
    public native AppViewController init();

    protected AppViewController(Pointer peer) {
        super(peer);
    }

    private String[][] arraySpinner;
    private int [] currentIndexes;

    public UILabel statusText = null;
    public UIButton convertButton = null;
    public UITextField textNumber = null;
    public UIPickerView currencyPicker = null;

    @Override
    public void viewDidLoad() {
        statusText = getLabel();
        convertButton = getConvertButton();

        // PickerView Array
        arraySpinner = new String[2][Names.currencyNameSymbols.size()];
        int countKey = 0;
        for(String key : Names.currencyNameSymbols.keySet()) {
            arraySpinner[0][countKey] = Names.currencyNameSymbols.get(key);
            arraySpinner[1][countKey] = Names.currencyNameSymbols.get(key);
            countKey++;
        }

        // Current indexes
        currentIndexes = new int[] {0, 0};

        textNumber = getEditNumber();
        textNumber.setDelegate(this);
        textNumber.setPlaceholder(Names.hintEditText);
        initCustomKeyboard();

        currencyPicker = getCurrencyPicker();
        currencyPicker.setDataSource(this);
        currencyPicker.setDelegate(this);
    }

    @Selector("BtnPressedCancel_convertButton:")
    public void btnPressedCancel_convertButton(NSObject sender) {
        String convertFromStr = "";
        String convertToStr = "";
        for (String key : Names.currencyNameSymbols.keySet()) {
            if (Names.currencyNameSymbols.get(key).equals(arraySpinner[0][currentIndexes[0]])) {
                convertFromStr = key;
            }
            if (Names.currencyNameSymbols.get(key).equals(arraySpinner[1][currentIndexes[1]])) {
                convertToStr = key;
            }
            if (!convertFromStr.isEmpty() && !convertToStr.isEmpty())
                break;
        }
        asyncGetCurrencyRate(textNumber.text(), convertFromStr, convertToStr);
    }

    private void asyncGetCurrencyRate(final String numberStr, final String curencyFrom, final String currencyTo) {
        if (numberStr == null || numberStr.isEmpty()) {
            statusText.setText("Please, input number for converting.");
            return;
        }

        Globals.dispatch_async(Globals.dispatch_get_global_queue(0, 0), new Globals.Block_dispatch_async() {
            @Override
            public void call_dispatch_async() {
                double[] convertResult = Currency.convert(Double.parseDouble(numberStr), curencyFrom, currencyTo);

                final String resultString;

                if (Math.abs(convertResult[0]) < 0.00000001) {
                    resultString = "Error: Cannot get currency rate!";
                } else {
                    BigDecimal x = new BigDecimal(convertResult[1]);
                    x = x.setScale(2, BigDecimal.ROUND_HALF_UP);
                    convertResult[1] = x.doubleValue();
                    resultString = "Currency rate: " + String.valueOf(convertResult[0]) +
                            ". Result: " + String.valueOf(convertResult[1]) + " " +
                            Currency.getCurrencySymbol(currencyTo);
                }

                Globals.dispatch_async(Globals.dispatch_get_main_queue(), new Globals.Block_dispatch_async() {
                    @Override
                    public void call_dispatch_async() {
                        statusText.setText(resultString);
                    }
                });
            }
        });
    }

    @Selector("textField:shouldChangeCharactersInRange:replacementString:")
    public boolean textFieldShouldChangeCharactersInRangeReplacementString(UITextField textField, @ByValue NSRange range, String string) {
        if (string.length() == 0)
            return true;

        if (textNumber.text().equals("0") && string.equals("0"))
            return false;

        if (string.matches("[0-9]+") && textNumber.text().equals("0")) {
            textNumber.setText("");
            return true;
        }

        if (string.equals(".") && !textNumber.text().contains(".") && !textNumber.text().isEmpty())
            return true;

        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Selector("pickerView:didSelectRow:inComponent:")
    public void pickerViewDidSelectRowInComponent(UIPickerView pickerView, @NInt long row, @NInt long component) {
        currentIndexes[((int) component)] = (int) row;
    }

    @Selector("numberOfComponentsInPickerView:")
    @NInt
    public long numberOfComponentsInPickerView(UIPickerView pickerView) {
        return 2;
    }

    @Selector("pickerView:numberOfRowsInComponent:")
    @NInt
    public long pickerViewNumberOfRowsInComponent(UIPickerView pickerView, @NInt long component) {
        return arraySpinner[((int) component)].length;
    }

    @Selector("pickerView:titleForRow:forComponent:")
    public String pickerViewTitleForRowForComponent(UIPickerView pickerView, @NInt long row, @NInt long component) {
        return arraySpinner[((int) component)][((int) row)];
    }

    @Selector("statusText")
    @Property
    public native UILabel getLabel();

    @Selector("currencyPicker")
    @Property
    public native UIPickerView getCurrencyPicker();

    @Selector("textNumber")
    @Property
    public native UITextField getEditNumber();

    @Selector("convertButton")
    @Property
    public native UIButton getConvertButton();

    private void initCustomKeyboard() {
        UIToolbar numberToolbar = UIToolbar.alloc().initWithFrame(CoreGraphics.CGRectMake(0, 0, 320, 50));
        numberToolbar.setBarStyle(UIBarStyle.BlackTranslucent); // setup style for bar
        numberToolbar.setTintColor(UIColor.whiteColor()); // setup button color

        //Create array of buttons
        NSMutableArray buttonsArray = NSMutableArray.alloc().init();
        buttonsArray.addObject(UIBarButtonItem.alloc().initWithTitleStyleTargetAction("Clear",
                UIBarButtonItemStyle.Bordered, this, new SEL("clearField:")));
        buttonsArray.addObject(UIBarButtonItem.alloc().initWithBarButtonSystemItemTargetAction(UIBarButtonSystemItem.FlexibleSpace,
                null, null));
        buttonsArray.addObject(UIBarButtonItem.alloc().initWithTitleStyleTargetAction("Done",
                UIBarButtonItemStyle.Done, this, new SEL("doneWithNumberPad:")));
        numberToolbar.setItems(buttonsArray);

        numberToolbar.sizeToFit();

        textNumber.setInputAccessoryView(numberToolbar);
    }

    @Selector("doneWithNumberPad:")
    public void doneWithNumberPad() {
        textNumber.resignFirstResponder();
    }

    @Selector("clearField:")
    public void clearField() {
        textNumber.setText("");
    }
}
