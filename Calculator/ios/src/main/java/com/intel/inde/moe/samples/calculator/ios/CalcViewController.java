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

    public UILabel calcNum = null;
    public UIButton calcBtnC = null;
    public UIButton calcBtnPm = null;
    public UIButton calcBtnProc = null;
    public UIButton calcBtnDevide = null;

    public UIButton calcBtn7 = null;
    public UIButton calcBtn8 = null;
    public UIButton calcBtn9 = null;
    public UIButton calcBtnUmn = null;

    public UIButton calcBtn4 = null;
    public UIButton calcBtn5 = null;
    public UIButton calcBtn6 = null;
    public UIButton calcBtnMin = null;

    public UIButton calcBtn1 = null;
    public UIButton calcBtn2 = null;
    public UIButton calcBtn3 = null;
    public UIButton calcBtnPlus = null;

    public UIButton calcBtn0 = null;
    public UIButton calcBtn0Fake = null;
    public UIButton calcBtnDote = null;
    public UIButton calcBtnEquel = null;

    @Override
    public void viewDidLoad(){
        myCalculatorAdapter = new CalculatorAdapter();
        calcNum = getCalcNum();
        calcBtnC = getCalcBtnC();
        calcBtnPm = getCalcBtnPm();
        calcBtnProc = getCalcBtnProc();
        calcBtnDevide = getCalcBtnDevide();

        calcBtn7 = getCalcBtn7();
        calcBtn8 = getCalcBtn8();
        calcBtn9 = getCalcBtn9();
        calcBtnUmn = getCalcBtnUmn();

        calcBtn4 = getCalcBtn4();
        calcBtn5 = getCalcBtn5();
        calcBtn6 = getCalcBtn6();
        calcBtnMin = getCalcBtnMin();

        calcBtn1 = getCalcBtn1();
        calcBtn2 = getCalcBtn2();
        calcBtn3 = getCalcBtn3();
        calcBtnPlus = getCalcBtnPlus();

        calcBtn0 = getCalcBtn0();
        calcBtn0Fake = getCalcBtn0Fake();
        calcBtnDote = getCalcBtnDote();
        calcBtnEquel = getCalcBtnEquel();

        calcNum.setText("0");
        calcBtnC.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnPm.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnProc.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnDevide.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        calcBtn7.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn8.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn9.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnUmn.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        calcBtn4.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn5.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn6.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnMin.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        calcBtn1.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn2.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn3.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnPlus.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        calcBtn0.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn0Fake.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnDote.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtnEquel.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));

    }

    @Selector("calc_num")
    @Property
    public native UILabel getCalcNum();

    @Selector("calc_btn_c")
    @Property
    public native UIButton getCalcBtnC();

    @Selector("calc_btn_pm")
    @Property
    public native UIButton getCalcBtnPm();

    @Selector("calc_btn_proc")
    @Property
    public native UIButton getCalcBtnProc();

    @Selector("calc_btn_devide")
    @Property
    public native UIButton getCalcBtnDevide();

    @Selector("calc_btn_7")
    @Property
    public native UIButton getCalcBtn7();

    @Selector("calc_btn_8")
    @Property
    public native UIButton getCalcBtn8();

    @Selector("calc_btn_9")
    @Property
    public native UIButton getCalcBtn9();

    @Selector("calc_btn_umn")
    @Property
    public native UIButton getCalcBtnUmn();

    @Selector("calc_btn_4")
    @Property
    public native UIButton getCalcBtn4();

    @Selector("calc_btn_5")
    @Property
    public native UIButton getCalcBtn5();

    @Selector("calc_btn_6")
    @Property
    public native UIButton getCalcBtn6();

    @Selector("calc_btn_min")
    @Property
    public native UIButton getCalcBtnMin();

    @Selector("calc_btn_1")
    @Property
    public native UIButton getCalcBtn1();

    @Selector("calc_btn_2")
    @Property
    public native UIButton getCalcBtn2();

    @Selector("calc_btn_3")
    @Property
    public native UIButton getCalcBtn3();

    @Selector("calc_btn_plus")
    @Property
    public native UIButton getCalcBtnPlus();

    @Selector("calc_btn_0")
    @Property
    public native UIButton getCalcBtn0();

    @Selector("calc_btn_0_fake")
    @Property
    public native UIButton getCalcBtn0Fake();

    @Selector("calc_btn_dote")
    @Property
    public native UIButton getCalcBtnDote();

    @Selector("calc_btn_equel")
    @Property
    public native UIButton getCalcBtnEquel();

    @Selector("BtnPressed_c:")
    public void btnPressedC(NSObject sender){
        calcBtnC.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));

    }

    @Selector("BtnPressedCancel_c:")
    public void btnPressedCancelC(NSObject sender){
        calcBtnC.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("C");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_pm:")
    public void btnPressedPm(NSObject sender){
        calcBtnPm.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }
    @Selector("BtnPressedCancel_pm:")
    public void btnPressedCancelPm(NSObject sender){
        calcBtnPm.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("INVERT");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_proc:")
    public void btnPressedProc(NSObject sender){
        calcBtnProc.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_proc:")
    public void btnPressedCancelProc(NSObject sender){
        calcBtnProc.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("C");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_devide:")
    public void btnPressedDevide(NSObject sender){
        calcBtnDevide.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_devide:")
    public void btnPressedCancelDevide(NSObject sender){
        calcBtnDevide.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("/");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_7:")
    public void btnPressed7(NSObject sender){
        calcBtn7.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_7:")
    public void btnPressedCancel7(NSObject sender){
        calcBtn7.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("7");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_8:")
    public void btnPressed8(NSObject sender){
        calcBtn8.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_8:")
    public void btnPressedCancel8(NSObject sender){
        calcBtn8.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("8");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_9:")
    public void btnPressed9(NSObject sender){
        calcBtn9.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_9:")
    public void btnPressedCancel9(NSObject sender){
        calcBtn9.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("9");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_umn:")
    public void btnPressedUmn(NSObject sender){
        calcBtnUmn.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_umn:")
    public void btnPressedCancelUmn(NSObject sender){
        calcBtnUmn.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("*");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_4:")
    public void btnPressed4(NSObject sender){
        calcBtn4.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_4:")
    public void btnPressedCancel4(NSObject sender){
        calcBtn4.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("4");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_5:")
    public void btnPressed5(NSObject sender){
        calcBtn5.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_5:")
    public void btnPressedCancel5(NSObject sender){
        calcBtn5.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("5");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_6:")
    public void btnPressed6(NSObject sender){
        calcBtn6.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_6:")
    public void btnPressedCancel6(NSObject sender){
        calcBtn6.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("6");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_min:")
    public void btnPressedMin(NSObject sender){
        calcBtnMin.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_min:")
    public void btnPressedCancelMin(NSObject sender){
        calcBtnMin.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("-");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_1:")
    public void btnPressed1(NSObject sender){
        calcBtn1.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_1:")
    public void btnPressedCancel1(NSObject sender){
        calcBtn1.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("1");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_2:")
    public void btnPressed2(NSObject sender){
        calcBtn2.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_2:")
    public void btnPressedCancel2(NSObject sender){
        calcBtn2.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("2");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_3:")
    public void btnPressed3(NSObject sender){
        calcBtn3.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_3:")
    public void btnPressedCancel3(NSObject sender){
        calcBtn3.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("3");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_plus:")
    public void btnPressedPlus(NSObject sender){
        calcBtnPlus.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_plus:")
    public void btnPressedCancelPlus(NSObject sender){
        calcBtnPlus.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("+");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_0:")
    public void btnPressed0(NSObject sender){
        calcBtn0.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
        calcBtn0Fake.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_0:")
    public void btnPressedCancel0(NSObject sender){
        calcBtn0.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        calcBtn0Fake.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("0");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_dote:")
    public void btnPressedDote(NSObject sender){
        calcBtnDote.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_dote:")
    public void btnPressedCancelDote(NSObject sender){
        calcBtnDote.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x71 / 255.0), (float) (0xba / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol(".");
        calcNum.setText(str);
    }

    @Selector("BtnPressed_equel:")
    public void btnPressedEquel(NSObject sender){
        calcBtnEquel.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) (0xfd / 255.0), (float) (0xb8 / 255.0), (float) (0x13 / 255.0), (float) 1.0));
    }

    @Selector("BtnPressedCancel_equel:")
    public void btnPressedCancelEquel(NSObject sender){
        calcBtnEquel.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0, (float) (0x4b / 255.0), (float) (0x8d / 255.0), (float) 1.0));
        String str = myCalculatorAdapter.sendNewSymbol("=");
        calcNum.setText(str);
    }
}