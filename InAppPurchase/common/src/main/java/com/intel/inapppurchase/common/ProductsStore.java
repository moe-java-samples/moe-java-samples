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
package com.intel.inapppurchase.common;

import com.intel.moe.frameworks.inapppurchase.CommonProductDetails;
import com.intel.moe.frameworks.inapppurchase.IAPHelper;
import com.intel.moe.frameworks.inapppurchase.IAPHelperInterface;

import java.util.ArrayList;

public class ProductsStore {

    // TODO:  Change this to whatever you set on iTunes connect and on Google Play Developer Console
    private static final String prefix = "com.example.inapppurchase.products.";

    private static final String item1 = prefix + "item1";
    private static final String item2 = prefix + "item2";
    private static final String item3 = prefix + "item3";
    private static final String item4 = prefix + "item4";

    public static final String[] productIds = {
            item1,
            item2,
            item3,
            item4
    };

    private ArrayList<ProductDetails> products = new ArrayList<>();


    // Static instance of IAPHelper that for rage products.
    private static IAPHelper Store = null;

    /**
     * Constructor for iOS application
     * @param publicKey - Android security key
     */
    public ProductsStore(String publicKey) {
        Store = new IAPHelper();
        Store.setIAPHelperPublicKey(publicKey);
        Store.setProductIdentifiers(productIds);
    }

    /**
     * Constructor for android application
     * @param applicationContext - Android Context
     * @param publicKey - Android security key
     */
    public ProductsStore(Object applicationContext, String publicKey) {
        Store = new IAPHelper();
        Store.setIAPHelperPublicKey(publicKey);
        Store.setProductIdentifiers(productIds);
        Store.setContext(applicationContext);
        Store.initHelper();
    }

    /**
     * Close helper
     */
    public void close() {
        if (Store != null)
            Store.closeHelper();
    }

    /**
     * Get security IAPHelper public key
     * @return public key
     */
    public String getIAPHelperPublicKey() {
        if (Store != null) {
            return Store.getIAPHelperPublicKey();
        }
        return null;
    }

    public int getNumberOfProducts() {
        return products.size();
    }

    public ProductDetails getProductAt(int i) {
        if (i >= products.size()) {
            return null;
        }
        return products.get(i);
    }

    public void requestProductsWithCompletionHandler(final RequestProductsCompletionHandler handler) {
        if (Store != null) {
            Store.requestProductsWithCompletionHandler(new IAPHelperInterface.RequestProductsCompletionHandler() {
                @Override
                public void callback(boolean status, CommonProductDetails[] productsArr) {
                    products.clear();
                    for (int i = 0; i < productsArr.length; i++) {
                        if (productsArr[i] != null) {
                            products.add(new ProductDetails(productsArr[i].getItemType(),
                                    productsArr[i].getProductID(), productsArr[i].getType(),
                                    productsArr[i].getPrice(), productsArr[i].getTitle(),
                                    productsArr[i].getDescription()));
                        }
                    }
                    handler.callback(status);
                }
            });
        }
    }

    public void purchaseProduct(ProductDetails product, final RequestPurchaseProductsHandler handler) {
        if (Store != null) {
            Store.purchaseProduct(new CommonProductDetails(product.getItemType(),
                    product.getProductID(), product.getType(), product.getPrice(),
                    product.getTitle(), product.getDescription()), new IAPHelperInterface.RequestPurchaseProductsHandler() {
                @Override
                public void callback(boolean b) {
                    handler.callback(b);
                }
            });
        }
    }

    public boolean isProductPurchased(String productID) {
        if (Store != null) {
            return Store.isProductPurchased(productID);
        }
        return false;
    }

    public void restoreCompletedTransactions() {
        if (Store != null) {
            Store.restoreCompletedTransactions();
        }
    }

    public boolean handleActivityResult(int requestCode, int resultCode, Object data) {
        if (Store != null) {
            Store.handleActivityResult(requestCode, resultCode, data);
        }
        return false;
    }

    public interface RequestPurchaseProductsHandler {
        void callback(boolean status);
    }

    public interface RequestProductsCompletionHandler {
        void callback(boolean status);
    }
}
