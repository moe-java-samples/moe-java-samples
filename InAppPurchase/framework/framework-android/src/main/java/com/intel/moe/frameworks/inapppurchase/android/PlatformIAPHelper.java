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
package com.intel.moe.frameworks.inapppurchase.android;

import android.content.Context;
import android.content.Intent;

import com.intel.moe.frameworks.inapppurchase.CommonProductDetails;
import com.intel.moe.frameworks.inapppurchase.AbstractIAPHelper;
import com.intel.moe.frameworks.inapppurchase.android.util.IabHelper;
import com.intel.moe.frameworks.inapppurchase.android.util.IabResult;
import com.intel.moe.frameworks.inapppurchase.android.util.Inventory;
import com.intel.moe.frameworks.inapppurchase.android.util.Purchase;
import com.intel.moe.frameworks.inapppurchase.android.util.SkuDetails;

public class PlatformIAPHelper extends AbstractIAPHelper {

    private IabHelper mHelper;
    private Context ctx = null;

    private boolean helperInitialized = false;

    // (arbitrary) request code for the purchase flow
    private static final int RC_REQUEST = 10001;

    public PlatformIAPHelper() {}

    @Override
    public void restoreCompletedTransactions() {}

    @Override
    public void requestProductsWithCompletionHandler(RequestProductsCompletionHandler handler) {
        completionHandler = handler;
        if (!helperInitialized)
            return;

        mHelper.queryInventoryAsync(true, productIdentifiers, mGotAllProductListener);
    }

    @Override
    public void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler, String verificationPayload) {
        purchaseHandler = handler;
        if (!helperInitialized)
            return;

        String productID = product.getProductID();
        mHelper.launchPurchaseFlow(ctx, productID, RC_REQUEST,
                mPurchaseFinishedListener, verificationPayload);
    }

    @Override
    public void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler) {
        purchaseHandler = handler;
        if (!helperInitialized)
            return;
        String payload = "";
        String productID = product.getProductID();
        mHelper.launchPurchaseFlow(ctx, productID, RC_REQUEST,
                mPurchaseFinishedListener, payload);
    }

    @Override
    public boolean canMakePayments() {
        return true;
    }

    @Override
    public boolean handleActivityResult(int requestCode, int resultCode, Object data) {
        return mHelper.handleActivityResult(requestCode, resultCode, (Intent) data);
    }

    @Override
    public void closeHelper() {
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    @Override
    public void setDebugHandler(DebugHandler handler) {
        debugHandler = handler;
        mHelper.enableDebugLogging(handler != null);
    }

    @Override
    public void setContext(Object ctx) {
        this.ctx = (Context) ctx;
    }

    @Override
    public void initHelper() {
        helperInitialized = false;
        if (ctx == null || IAPHelperProductPurchasedPublicKey.isEmpty())
            return;

        mHelper = new IabHelper(ctx, IAPHelperProductPurchasedPublicKey);
        mHelper.enableDebugLogging(false);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    if (errorHandler != null) {
                        errorHandler.callback("android/PlatformIAPHelper [initHelper]",
                                "In-app Billing setup failed: " + result);
                    }
                    if (debugHandler != null) {
                        debugHandler.callback("android/PlatformIAPHelper [initHelper]",
                                "In-app Billing setup failed: " + result);
                    }
                } else {
                    if (debugHandler != null) {
                        debugHandler.callback("android/PlatformIAPHelper [initHelper]",
                                "In-app Billing is set up OK");
                    }
                    helperInitialized = true;
                    mHelper.queryInventoryAsync(true, productIdentifiers, mGotAllProductListener);
                }
            }
        });
    }

    /** Verifies the developer payload of a purchase. */
    private boolean verifyDeveloperPayload(Purchase p) {
        if (customVerifyPayloadHandler != null)
            return customVerifyPayloadHandler.callback(p);
        String payload = p.getDeveloperPayload();
        // Return always true
        return true;
    }

    // Get All Products
    private IabHelper.QueryInventoryFinishedListener mGotAllProductListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (debugHandler != null) {
                debugHandler.callback("android/PlatformIAPHelper [QueryInventoryFinishedListener]",
                        "Query inventory finished.");
            }
            if (result.isFailure()) {
                if (debugHandler != null) {
                    debugHandler.callback("android/PlatformIAPHelper [QueryInventoryFinishedListener]",
                            "Failed to query inventory: " + result);
                }
                return;
            }

            if (debugHandler != null) {
                debugHandler.callback("android/PlatformIAPHelper [QueryInventoryFinishedListener]",
                        "Query inventory was successful.");
            }

            // Get all products
            SkuDetails[] products = new SkuDetails[productIdentifiers.size()];
            for (int i = 0; i < productIdentifiers.size(); i++) {
                products[i] = inventory.getSkuDetails(productIdentifiers.get(i));
                Purchase purchase = inventory.getPurchase(productIdentifiers.get(i));
                // Add purchase products
                if (purchase != null && verifyDeveloperPayload(purchase) && !purchasedProductIdentifiers.contains(products[i].getSku())) {
                    purchasedProductIdentifiers.add(products[i].getSku());
                }
            }

            if (completionHandler != null)
                completionHandler.callback(true, products);
        }
    };

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (debugHandler != null) {
                debugHandler.callback("android/PlatformIAPHelper [OnIabPurchaseFinishedListener]",
                        "Purchase finished: " + result + ", purchase: " + purchase);
            }
            if (result.isFailure()) {
                if (purchaseHandler != null) {
                    purchaseHandler.callback(false);
                }
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                return;
            }

            purchasedProductIdentifiers.add(purchase.getSku());

            if (debugHandler != null) {
                debugHandler.callback("android/PlatformIAPHelper [OnIabPurchaseFinishedListener]",
                        "Purchase successful.");
                debugHandler.callback("android/PlatformIAPHelper [OnIabPurchaseFinishedListener]",
                        "Purchase for " + purchase.getSku() + " done. Congratulating user.");
            }
            if (purchaseHandler != null) {
                purchaseHandler.callback(true);
            }
        }
    };
}
