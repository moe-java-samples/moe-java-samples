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

package com.intel.moe.samples.currencyconverter.android;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.intel.moe.samples.currencyconverter.common.Currency;
import com.intel.moe.samples.currencyconverter.common.Names;

import java.math.BigDecimal;
import java.util.Locale;

import intel.com.currencyconverter.R;

public class MainActivity extends AppCompatActivity {

    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerFrom = (Spinner)findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = (Spinner)findViewById(R.id.spinnerTo);

        final EditText numberSrt = (EditText)findViewById(R.id.edtNum);
        numberSrt.setHint(Names.hintEditText);
        numberSrt.setFilters(new InputFilter[]{
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if (src.equals("0") && numberSrt.getText().toString().equals("0")) { // for backspace
                            return "";
                        }
                        if (src.equals(".") && numberSrt.getText().toString().isEmpty()) { // for backspace
                            return "";
                        }
                        if (src.toString().matches("[0-9]+") && numberSrt.getText().toString().equals("0")) {
                            numberSrt.setText("");
                            numberSrt.setText(src);
                            numberSrt.setSelection(1);
                        }

                        if (src.toString().matches("[0-9.]+"))
                            return src;

                        return "";
                    }
                }
        });

        Locale.setDefault(Locale.UK);
        arraySpinner = new String[Names.currencyNameSymbols.size()];
        int countKey = 0;
        for(String key : Names.currencyNameSymbols.keySet()) {
            arraySpinner[countKey] = Names.currencyNameSymbols.get(key);
            countKey++;
        }

        final Button convertButton = (Button)findViewById(R.id.btnConvert);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String convertFromStr = "";
                String convertToStr = "";

                Spinner spinnerFrom = (Spinner)findViewById(R.id.spinnerFrom);
                Spinner spinnerTo = (Spinner)findViewById(R.id.spinnerTo);
                TextView resultTextView = (TextView)findViewById(R.id.resultLayout);
                EditText numberSrt = (EditText)findViewById(R.id.edtNum);
                String resultTextStr = numberSrt.getText().toString();

                if (resultTextStr.equals("")) {
                    resultTextView.setText("Please, input number for converting.");
                    return;
                }
                resultTextView.setText("Please wait...");

                for (String key : Names.currencyNameSymbols.keySet()) {
                    if (Names.currencyNameSymbols.get(key).equals(spinnerFrom.getSelectedItem().toString())) {
                        convertFromStr = key;
                    }
                    if (Names.currencyNameSymbols.get(key).equals(spinnerTo.getSelectedItem().toString())) {
                        convertToStr = key;
                    }
                    if (!convertFromStr.equals("") && !convertToStr.equals(""))
                        break;
                }
                String[] converterTaskParams = { resultTextStr, convertFromStr, convertToStr };
                AsyncTask converter = new ConverterTask();
                converter.execute(converterTaskParams);
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    private class ConverterTask extends AsyncTask<String, Void, Void> {

        private String resultString = "";

        @Override
        protected Void doInBackground(String... params) {
            double [] convertResult = Currency.convert(Double.parseDouble(params[0]),
                    params[1], params[2]);
            if (convertResult[0] == 0.0) {
                resultString = "Error: Cannot get currency rate!";
            } else {
                BigDecimal x = new BigDecimal(convertResult[1]);
                x = x.setScale(2, BigDecimal.ROUND_HALF_UP);
                convertResult[1] = x.doubleValue();
                resultString = "Currency rate: " + String.valueOf(convertResult[0]) +
                        "\nResult: " + String.valueOf(convertResult[1]) + " " +
                        Currency.getCurrencySymbol(params[2]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView resultTextView = (TextView)findViewById(R.id.resultLayout);
            resultTextView.setText(resultString);
        }
    }
}
