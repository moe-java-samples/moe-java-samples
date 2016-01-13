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

package com.intel.inde.moe.samples.calculator.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorAdapter {
	private boolean isFirst;
	private boolean isFractPart;
	private double a;
	private double b;
	private CalcOpsTypes type;
	private String showStr;
	
	private long fractPart;

	private long numberOfNulls;
	
	public CalculatorAdapter()
	{
		a = 0.;
		b = 0.;
		numberOfNulls = 0;
		type = CalcOpsTypes.NONE;
		isFirst = true;
		isFractPart = false;
		showStr  = "0";
	}
	
	public String sendNewSymbol (String symbol)
	{
		final double digit = isDigit(symbol);
		if (Double.compare(digit, -1.) != 0){
			if (Double.compare(a, 0) == 0) {// to avoid making
							// calculations like
							// 0+2
				type = CalcOpsTypes.NONE;
			}
			
			if (type != CalcOpsTypes.NONE && Double.compare(b, 0) == 0 && isFirst) // to avoid adding extra
							// digits after choice
							// of operation
			{
				isFirst = false;
				numberOfNulls = 0;
			}
			if (isFractPart) {
				if (fractPart == 0) {
					if ((int) digit != 0) {
						fractPart += (int) digit;
					} else {
						numberOfNulls += 1;
					}
				} else {
                    long prevFractPart = fractPart;
					fractPart = fractPart * 10 + (int) digit;
                    // Check overflow:
                    if (fractPart < 0) {
                        fractPart = prevFractPart;
                    }
				}
			}

			if (isFirst) {
				if (!isFractPart) {
					a = a * 10 + digit;
				} else {
					String fractStr = ".";
					for (int i = 0; i < numberOfNulls; i++) {
						fractStr += "0";
					}
					boolean aLessZero = false;
					if (a < 0)
						aLessZero = true;
					a = Double.parseDouble((int) a + fractStr + fractPart);
					if (a > 0 && aLessZero)
						a = -a;
				}
				showStr = String.valueOf(a);
				if (fractPart == 0 && isFractPart) {
					for (int i = 1; i < numberOfNulls; i++) {
						showStr += "0";
					}
				}
				if (fractPart != 0) {
					long fractNulls = 0;
					while (fractPart % (long) Math.pow(10, fractNulls + 1) == 0) {
						showStr += "0";
						fractNulls++;
					}
				}
			} else {
				if (!isFractPart) {
					b = b * 10 + digit;
				} else {
					String fractStr = ".";
					for (int i = 0; i < numberOfNulls; i++) {
						fractStr += "0";
					}
					b = Double.parseDouble((int) b + fractStr + fractPart);
				}
				showStr = String.valueOf(a) + opToString(type) + String.valueOf(b);

				if (fractPart == 0 && isFractPart) {
					for (int i = 1; i < numberOfNulls; i++) {
						showStr += "0";
					}
				}

				if (fractPart != 0) {
					long fractNulls = 0;
					while (fractPart % (long) Math.pow(10, fractNulls + 1) == 0) {
						showStr += "0";
						fractNulls++;
					}
				}
			}
			
		}
		else{
			final CalcOpsTypes opType = isCalcOperation(symbol);
			if (opType != CalcOpsTypes.NONE){
				if (Double.compare(a, 0) != 0) {
					if (Double.compare(b, 0) != 0) {
						calculateAndPrepare(opType);
					} else {
						type = opType;
						showStr = String.valueOf(a) + opToString(type) + "0";
						fractPart = 0;
						isFractPart = false;
					}
				}
			}
			else
			{
				if (symbol.equals("=")){//everything all right
					if (!isFirst){
						calculateAndPrepare(CalcOpsTypes.NONE);
					}
				}
				else if (symbol.equals("C")){//everything all right
					fractPart = 0;
					a = 0.;
					b = 0.;
					numberOfNulls = 0;
					isFractPart = false;
					isFirst = true;
					showStr = "0";
				}
				else if (symbol.equals(".")) {
					if (!isFractPart)
						showStr += ".";
					isFractPart = true;
				} else if (symbol.equals("INVERT") && isFirst){
					a = -a;
					showStr = String.valueOf(a);
					if (fractPart == 0 && isFractPart) {
						for (int i = 1; i < numberOfNulls; i++) {
							showStr += "0";
						}
					}
					if (fractPart != 0) {
						long fractNulls = 0;
						while (fractPart % (long) Math.pow(10, fractNulls + 1) == 0) {
							showStr += "0";
							fractNulls++;
						}
					}
				}
				
			}
		}
		return showStr;
	}
	
	public double isDigit (String ss)
	{
		double digit = -1.;
		try{
			digit = Double.parseDouble(ss);
		}
		catch(NumberFormatException nfe)  
		{  
		    return digit;  
		}  
		return digit;
	}
	
	public CalcOpsTypes isCalcOperation (String ss)
	{
		CalcOpsTypes myType = CalcOpsTypes.NONE;
		if (ss.equals("+")){
			myType = CalcOpsTypes.SUM;
		}
		else if (ss.equals("-")){
			myType = CalcOpsTypes.DIFF;
		}
		else if (ss.equals("*")){
			myType = CalcOpsTypes.MULT;
		}
		else if (ss.equals("/")){
			myType = CalcOpsTypes.DIVIDE;
		}
		
		return myType;
	}
	
	public String opToString (CalcOpsTypes myType){
		String ss = "";
		switch (myType){
		case DIFF:
			ss = "-";
			break;
		case DIVIDE:
			ss = "/";
			break;
		case MULT:
			ss = "*";
			break;
		case NONE:
			break;
		case SUM:
			ss = "+";
			break;
		}
		return ss;
	}

	private void calculateAndPrepare(CalcOpsTypes afterOperation){
		double result = CalcOperations.calculate(a, b, type);
		try{
			result = new BigDecimal(result).setScale(4, RoundingMode.HALF_UP).doubleValue();
			showStr = String.valueOf(result);
			switch (afterOperation) {
			case SUM:
				showStr += "+";
				break;
			case DIFF:
				showStr += "-";
				break;
			case DIVIDE:
				showStr += "/";
				break;
			case MULT:
				showStr += "*";
				break;
			case NONE:
				break;
			default:
				break;
	
			}
		}
		catch (NumberFormatException e){
			System.out.println("The result is NAN!");
			showStr = String.valueOf(result);
		}

		a = result;
		b = 0.;
		numberOfNulls = 0;
		isFirst = true;
		fractPart = (int) (Math.abs(a - (int) a) * Math.pow(10, String.valueOf(Math.abs(a))
				.length() - 2));
		type = afterOperation;
	}
	
	public String getShowStr() {
		return showStr;
	}
}
