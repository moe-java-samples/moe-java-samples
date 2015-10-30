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

package com.intel.inde.moe.samples.calculator.ios;

import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.samples.calculator.common.CalculatorAdapter;

import ios.NSObject;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UILabel;
import ios.uikit.UIViewController;


@com.intel.inde.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("CalcViewController")
@RegisterOnStartup
public class CalcViewController extends UIViewController {

    @Selector("alloc")
    public static native CalcViewController alloc();

    @Selector("init")
    public native CalcViewController init();

    protected CalcViewController(Pointer peer) {
        super(peer);
    }

    private CalculatorAdapter myCalculatorAdapter;

    public UILabel calc_num = null;
    public UIButton calc_btn_c = null;
    public UIButton calc_btn_pm = null;
    public UIButton calc_btn_proc = null;
    public UIButton calc_btn_devide = null;

    public UIButton calc_btn_7 = null;
    public UIButton calc_btn_8 = null;
    public UIButton calc_btn_9 = null;
    public UIButton calc_btn_umn = null;

    public UIButton calc_btn_4 = null;
    public UIButton calc_btn_5 = null;
    public UIButton calc_btn_6 = null;
    public UIButton calc_btn_min = null;

    public UIButton calc_btn_1 = null;
    public UIButton calc_btn_2 = null;
    public UIButton calc_btn_3 = null;
    public UIButton calc_btn_plus = null;

    public UIButton calc_btn_0 = null;
    public UIButton calc_btn_0_fake = null;
    public UIButton calc_btn_dote = null;
    public UIButton calc_btn_equel = null;

    @Override
    public void viewDidLoad(){
        myCalculatorAdapter = new CalculatorAdapter();
        calc_num = getCalc_num();
        calc_btn_c = getCalc_btn_c();
        calc_btn_pm = getCalc_btn_pm();
        calc_btn_proc = getCalc_btn_proc();
        calc_btn_devide = getCalc_btn_devide();

        calc_btn_7 = getCalc_btn_7();
        calc_btn_8 = getCalc_btn_8();
        calc_btn_9 = getCalc_btn_9();
        calc_btn_umn = getCalc_btn_umn();

        calc_btn_4 = getCalc_btn_4();
        calc_btn_5 = getCalc_btn_5();
        calc_btn_6 = getCalc_btn_6();
        calc_btn_min = getCalc_btn_min();

        calc_btn_1 = getCalc_btn_1();
        calc_btn_2 = getCalc_btn_2();
        calc_btn_3 = getCalc_btn_3();
        calc_btn_plus = getCalc_btn_plus();

        calc_btn_0 = getCalc_btn_0();
        calc_btn_0_fake = getCalc_btn_0_fake();
        calc_btn_dote = getCalc_btn_dote();
        calc_btn_equel = getCalc_btn_equel();

        calc_num.setText("0");
        calc_btn_c.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calc_btn_pm.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_proc.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_devide.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        calc_btn_7.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_8.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_9.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_umn.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        calc_btn_4.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_5.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_6.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_min.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        calc_btn_1.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_2.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_3.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_plus.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        calc_btn_0.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_0_fake.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_dote.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_equel.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));

    }

    @Selector("calc_num")
    @Property
    public native UILabel getCalc_num();

    @Selector("calc_btn_c")
    @Property
    public native UIButton getCalc_btn_c();

    @Selector("calc_btn_pm")
    @Property
    public native UIButton getCalc_btn_pm();

    @Selector("calc_btn_proc")
    @Property
    public native UIButton getCalc_btn_proc();

    @Selector("calc_btn_devide")
    @Property
    public native UIButton getCalc_btn_devide();

    @Selector("calc_btn_7")
    @Property
    public native UIButton getCalc_btn_7();

    @Selector("calc_btn_8")
    @Property
    public native UIButton getCalc_btn_8();

    @Selector("calc_btn_9")
    @Property
    public native UIButton getCalc_btn_9();

    @Selector("calc_btn_umn")
    @Property
    public native UIButton getCalc_btn_umn();

    @Selector("calc_btn_4")
    @Property
    public native UIButton getCalc_btn_4();

    @Selector("calc_btn_5")
    @Property
    public native UIButton getCalc_btn_5();

    @Selector("calc_btn_6")
    @Property
    public native UIButton getCalc_btn_6();

    @Selector("calc_btn_min")
    @Property
    public native UIButton getCalc_btn_min();

    @Selector("calc_btn_1")
    @Property
    public native UIButton getCalc_btn_1();

    @Selector("calc_btn_2")
    @Property
    public native UIButton getCalc_btn_2();

    @Selector("calc_btn_3")
    @Property
    public native UIButton getCalc_btn_3();

    @Selector("calc_btn_plus")
    @Property
    public native UIButton getCalc_btn_plus();

    @Selector("calc_btn_0")
    @Property
    public native UIButton getCalc_btn_0();

    @Selector("calc_btn_0_fake")
    @Property
    public native UIButton getCalc_btn_0_fake();

    @Selector("calc_btn_dote")
    @Property
    public native UIButton getCalc_btn_dote();

    @Selector("calc_btn_equel")
    @Property
    public native UIButton getCalc_btn_equel();

    @Selector("BtnPressed_c:")
    public void BtnPressed_c(NSObject sender){
        calc_btn_c.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));

    }

    @Selector("BtnPressedCancel_c:")
    public void BtnPressedCancel_c(NSObject sender){
        calc_btn_c.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("C");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_pm:")
    public void BtnPressed_pm(NSObject sender){
        calc_btn_pm.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }
    @Selector("BtnPressedCancel_pm:")
    public void BtnPressedCancel_pm(NSObject sender){
        calc_btn_pm.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("C");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_proc:")
    public void BtnPressed_proc(NSObject sender){
        calc_btn_proc.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_proc:")
    public void BtnPressedCancel_proc(NSObject sender){
        calc_btn_proc.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("C");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_devide:")
    public void BtnPressed_devide(NSObject sender){
        calc_btn_devide.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_devide:")
    public void BtnPressedCancel_devide(NSObject sender){
        calc_btn_devide.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("/");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_7:")
    public void BtnPressed_7(NSObject sender){
        calc_btn_7.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_7:")
    public void BtnPressedCancel_7(NSObject sender){
        calc_btn_7.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("7");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_8:")
    public void BtnPressed_8(NSObject sender){
        calc_btn_8.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_8:")
    public void BtnPressedCancel_8(NSObject sender){
        calc_btn_8.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("8");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_9:")
    public void BtnPressed_9(NSObject sender){
        calc_btn_9.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_9:")
    public void BtnPressedCancel_9(NSObject sender){
        calc_btn_9.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("9");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_umn:")
    public void BtnPressed_umn(NSObject sender){
        calc_btn_umn.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_umn:")
    public void BtnPressedCancel_umn(NSObject sender){
        calc_btn_umn.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("*");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_4:")
    public void BtnPressed_4(NSObject sender){
        calc_btn_4.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_4:")
    public void BtnPressedCancel_4(NSObject sender){
        calc_btn_4.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("4");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_5:")
    public void BtnPressed_5(NSObject sender){
        calc_btn_5.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_5:")
    public void BtnPressedCancel_5(NSObject sender){
        calc_btn_5.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("5");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_6:")
    public void BtnPressed_6(NSObject sender){
        calc_btn_6.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_6:")
    public void BtnPressedCancel_6(NSObject sender){
        calc_btn_6.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("6");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_min:")
    public void BtnPressed_min(NSObject sender){
        calc_btn_min.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_min:")
    public void BtnPressedCancel_min(NSObject sender){
        calc_btn_min.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("-");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_1:")
    public void BtnPressed_1(NSObject sender){
        calc_btn_1.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_1:")
    public void BtnPressedCancel_1(NSObject sender){
        calc_btn_1.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("1");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_2:")
    public void BtnPressed_2(NSObject sender){
        calc_btn_2.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_2:")
    public void BtnPressedCancel_2(NSObject sender){
        calc_btn_2.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("2");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_3:")
    public void BtnPressed_3(NSObject sender){
        calc_btn_3.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_3:")
    public void BtnPressedCancel_3(NSObject sender){
        calc_btn_3.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("3");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_plus:")
    public void BtnPressed_plus(NSObject sender){
        calc_btn_plus.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_plus:")
    public void BtnPressedCancel_plus(NSObject sender){
        calc_btn_plus.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("+");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_0:")
    public void BtnPressed_0(NSObject sender){
        calc_btn_0.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
        calc_btn_0_fake.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_0:")
    public void BtnPressedCancel_0(NSObject sender){
        calc_btn_0.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        calc_btn_0_fake.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("0");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_dote:")
    public void BtnPressed_dote(NSObject sender){
        calc_btn_dote.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_dote:")
    public void BtnPressedCancel_dote(NSObject sender){
        calc_btn_dote.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x71/255.0), (float)(0xba/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol(".");
        calc_num.setText(str);
    }

    @Selector("BtnPressed_equel:")
    public void BtnPressed_equel(NSObject sender){
        calc_btn_equel.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)(0xfd/255.0), (float)(0xb8/255.0), (float)(0x13/255.0), (float)1.0));
    }

    @Selector("BtnPressedCancel_equel:")
    public void BtnPressedCancel_equel(NSObject sender){
        calc_btn_equel.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float)0.0, (float)(0x4b/255.0), (float)(0x8d/255.0), (float)1.0));
        String str = myCalculatorAdapter.sendNewSymbol("=");
        calc_num.setText(str);
    }
}