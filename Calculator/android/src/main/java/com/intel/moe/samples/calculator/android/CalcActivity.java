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

package com.intel.moe.samples.calculator.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.intel.calc.R;
import com.intel.moe.samples.calculator.common.CalculatorAdapter;


public class CalcActivity extends AppCompatActivity implements OnClickListener {

	private TextView calcText;
	
	private String showStr;
	
	private CalculatorAdapter myCalculatorAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		myCalculatorAdapter = new CalculatorAdapter();
		showStr = "";
		
		setContentView(R.layout.calc_activity);
		calcText = (TextView) findViewById(R.id.calc_text);
		calcText.setTextSize(50f);

		final Button btn1 = (Button) findViewById(R.id.bdig_1);
		final Button btn2 = (Button) findViewById(R.id.bdig_2);
		final Button btn3 = (Button) findViewById(R.id.bdig_3);
		final Button btn4 = (Button) findViewById(R.id.bdig_4);
		final Button btn5 = (Button) findViewById(R.id.bdig_5);
		final Button btn6 = (Button) findViewById(R.id.bdig_6);
		final Button btn7 = (Button) findViewById(R.id.bdig_7);
		final Button btn8 = (Button) findViewById(R.id.bdig_8);
		final Button btn9 = (Button) findViewById(R.id.bdig_9);
		final Button btn0 = (Button) findViewById(R.id.bdig_0);
		final Button btn10 = (Button) findViewById(R.id.b_c);
		final Button btn11 = (Button) findViewById(R.id.b_div);
		final Button btn12 = (Button) findViewById(R.id.b_mul);
		final Button btn13 = (Button) findViewById(R.id.b_minus);
		final Button btn14 = (Button) findViewById(R.id.b_sum);
		final Button btn15 = (Button) findViewById(R.id.b_dot);
		final Button btn16 = (Button) findViewById(R.id.b_equal);

		btn0.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn11.setOnClickListener(this);
		btn12.setOnClickListener(this);
		btn13.setOnClickListener(this);
		btn14.setOnClickListener(this);
		btn15.setOnClickListener(this);
		btn16.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String symbol = "";

		// boolean isEqual = false;
		switch (v.getId()) {
		case R.id.bdig_1:
			symbol = "1";
			break;
		case R.id.bdig_2:
			symbol = "2";
			break;
		case R.id.bdig_3:
			symbol = "3";
			break;
		case R.id.bdig_4:
			symbol = "4";
			break;
		case R.id.bdig_5:
			symbol = "5";
			break;
		case R.id.bdig_6:
			symbol = "6";
			break;
		case R.id.bdig_7:
			symbol = "7";
			break;
		case R.id.bdig_8:
			symbol = "8";
			break;
		case R.id.bdig_9:
			symbol = "9";
			break;
		case R.id.bdig_0:
			symbol = "0";
			break;
		case R.id.b_c:
			symbol = "C";
			break;
		case R.id.b_dot:
			symbol = ".";
			break;
		case R.id.b_equal:
			symbol = "=";
			break;
		case R.id.b_sum:
			symbol = "+";
			break;
		case R.id.b_minus:
			symbol = "-";
			break;
		case R.id.b_mul:
			symbol = "*";
			break;
		case R.id.b_div:
			symbol = "/";
			break;
		default:
			System.out.println("Not defined action by " + v.getId());
			return;
		}
		if (symbol != "")
		{
			showStr = myCalculatorAdapter.sendNewSymbol(symbol);
			calcText.setText(showStr);
		}
	}

}