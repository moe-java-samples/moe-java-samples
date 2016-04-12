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
package com.intel.inapppurchase.android;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.intel.inapppurchase.common.ProductDetails;
import com.intel.inapppurchase.common.ProductsStore;

public class PurchaseListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ProductsStore productsStore;

    public PurchaseListAdapter(Activity activity, ProductsStore productsStore) {
        this.activity = activity;
        this.productsStore = productsStore;
    }

    @Override
    public int getCount() {
        if (productsStore == null)
            return 0;
        return productsStore.getNumberOfProducts();
    }

    @Override
    public Object getItem(int position) {
        if (productsStore == null)
            return 0;
        return productsStore.getProductAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_layout, null);

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        int tint = ContextCompat.getColor(activity, R.color.colorPrimary);
        icon.setColorFilter(tint);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        final Button buyButton = (Button) convertView.findViewById(R.id.buyButton);

        final ProductDetails product = productsStore.getProductAt(position);

        title.setText(product.getTitle());

        price.setText(product.getPrice());

        if (productsStore != null) {
            buyButton.setEnabled(!productsStore.isProductPurchased(productsStore.getProductAt(position).getProductID()));
        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productsStore != null) {
                    productsStore.purchaseProduct(product, new ProductsStore.RequestPurchaseProductsHandler() {
                        @Override
                        public void callback(boolean status) {
                            buyButton.setEnabled(!status);
                        }
                    });
                }
            }
        });

        return convertView;
    }
}
