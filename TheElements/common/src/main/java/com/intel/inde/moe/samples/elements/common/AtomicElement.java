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

package com.intel.inde.moe.samples.elements.common;

import com.longevitysoft.android.xml.plist.domain.*;

import java.lang.String;

public class AtomicElement {

    private int atomicNumber;
    private String atomicWeight;
    private String discoveryYear;
    private int group;
    private int horizPos;
    private int vertPos;
    private String name;
    private int period;
    private boolean radioactive;
    private String state;
    private String symbol;

    public AtomicElement() { // Test purposes only
        this.atomicNumber = 89;
        this.atomicWeight = "227";
        this.discoveryYear = "1899 A.D.";
        this.group = 3;
        this.horizPos = 4;
        this.vertPos = 10;
        this.name = "Actinium";
        this.period = 7;
        this.radioactive = true;
        this.state = "Solid";
        this.symbol = "Ac";
    }

    public AtomicElement(Dict cat) {
        com.longevitysoft.android.xml.plist.domain.Integer integerValue;
        com.longevitysoft.android.xml.plist.domain.String stringValue;
        integerValue = cat.getConfigurationInteger("atomicNumber");
        if (integerValue != null)
            this.atomicNumber = integerValue.getValue();

        stringValue = cat.getConfiguration("atomicWeight");
        if (stringValue != null)
            this.atomicWeight = stringValue.getValue();

        stringValue = cat.getConfiguration("discoveryYear");
        if (stringValue != null)
            this.discoveryYear = stringValue.getValue();

        integerValue = cat.getConfigurationInteger("group");
        if (integerValue != null)
            this.group = integerValue.getValue();

        integerValue = cat.getConfigurationInteger("horizPos");
        if (integerValue != null)
            this.horizPos = integerValue.getValue();

        integerValue = cat.getConfigurationInteger("vertPos");
        if (integerValue != null)
            this.vertPos = integerValue.getValue();

        stringValue = cat.getConfiguration("name");
        if (stringValue != null)
            this.name = stringValue.getValue();

        integerValue = cat.getConfigurationInteger("period");
        if (integerValue != null)
            this.period = integerValue.getValue();

        stringValue = cat.getConfiguration("radioactive");
        if (stringValue != null)
            this.radioactive = stringValue.getValue().equals("True");

        stringValue = cat.getConfiguration("state");
        if (stringValue != null)
            this.state = stringValue.getValue();

        stringValue = cat.getConfiguration("symbol");
        if (stringValue != null)
            this.symbol = stringValue.getValue();
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(int atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public String getAtomicWeight() {
        return atomicWeight;
    }

    public void setAtomicWeight(String atomicWeight) {
        this.atomicWeight = atomicWeight;
    }

    public String getDiscoveryYear() {
        return discoveryYear;
    }

    public void setDiscoveryYear(String discoveryYear) {
        this.discoveryYear = discoveryYear;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getHorizPos() {
        return horizPos;
    }

    public void setHorizPos(int horizPos) {
        this.horizPos = horizPos;
    }

    public int getVertPos() {
        return vertPos;
    }

    public void setVertPos(int vertPos) {
        this.vertPos = vertPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean getRadioactive() {
        return radioactive;
    }

    public void setRadioactive(boolean radioactive) {
        this.radioactive = radioactive;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
