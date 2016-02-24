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
package com.intel.moe.frameworks.inapppurchase.ios;

import com.intel.moe.frameworks.inapppurchase.AbstractIAPHelper;
import com.intel.moe.frameworks.inapppurchase.CommonProductDetails;
import com.intel.moe.frameworks.inapppurchase.ios.utils.SKProductDetails;

import java.util.HashMap;
import java.util.Map;

import ios.foundation.NSArray;
import ios.foundation.NSError;
import ios.foundation.NSMutableSet;
import ios.foundation.NSNotificationCenter;
import ios.foundation.NSUserDefaults;
import ios.storekit.SKPayment;
import ios.storekit.SKPaymentQueue;
import ios.storekit.SKPaymentTransaction;
import ios.storekit.SKProduct;
import ios.storekit.SKProductsRequest;
import ios.storekit.SKProductsResponse;
import ios.storekit.SKRequest;
import ios.storekit.enums.Enums;
import ios.storekit.enums.SKPaymentTransactionState;
import ios.storekit.protocol.SKPaymentTransactionObserver;
import ios.storekit.protocol.SKProductsRequestDelegate;

public class PlatformIAPHelper extends AbstractIAPHelper implements SKProductsRequestDelegate, SKPaymentTransactionObserver {
    static {
        try {
            Class.forName(SKProduct.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private SKProductsRequest productsRequest;
    private Map<String, SKProduct> nativeProductsMap = new HashMap<>();

    public PlatformIAPHelper() {
        purchasedProductIdentifiers.clear();

        if (productIdentifiers != null) {
            for (String productId : productIdentifiers) {
                if (NSUserDefaults.standardUserDefaults().boolForKey(productId)) {
                    purchasedProductIdentifiers.add(productId);
                }
            }
        }
    }

    @Override
    public boolean canMakePayments() {
        return SKPaymentQueue.canMakePayments();
    }

    @Override
    public boolean handleActivityResult(int requestCode, int resultCode, Object data) {
        return false;
    }

    @Override
    public void restoreCompletedTransactions() {
        ((SKPaymentQueue) SKPaymentQueue.defaultQueue()).restoreCompletedTransactions();
    }

    @Override
    public void closeHelper() {}

    @Override
    public void setDebugHandler(DebugHandler handler) {
        debugHandler = handler;
    }

    @Override
    public void setContext(Object ctx) {}

    @Override
    public void initHelper() {}

    // Gets the list of SKProducts from the Apple server calls the handler with the list of products.
    @Override
    public void requestProductsWithCompletionHandler(RequestProductsCompletionHandler handler) {
        completionHandler = handler;
        NSMutableSet<String> nativeSet = (NSMutableSet<String>) NSMutableSet.alloc().init();
        for (String productIdentifier : productIdentifiers)
            nativeSet.addObject(productIdentifier);
        productsRequest = SKProductsRequest.alloc().initWithProductIdentifiers(nativeSet);
        productsRequest.setDelegate(this);
        productsRequest.start();
    }

    @Override
    public void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler) {
        purchaseHandler = handler;
        if (debugHandler != null) {
            debugHandler.callback("ios/PlatformIAPHelper [purchaseProduct]", "Buying " + product.getProductID());
        }
        SKProduct nativeProduct = nativeProductsMap.get(product.getProductID());
        if (nativeProduct != null) {
            SKPayment payment = SKPayment.paymentWithProduct(nativeProduct);
            ((SKPaymentQueue) SKPaymentQueue.defaultQueue()).addPayment(payment);
        }
    }

    @Override
    public void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler, String verificationPayload) {
        purchaseHandler = handler;
        if (debugHandler != null) {
            debugHandler.callback("ios/PlatformIAPHelper [purchaseProduct]", "Buying " + product.getProductID());
        }
        SKProduct nativeProduct = nativeProductsMap.get(product.getProductID());
        if (nativeProduct != null) {
            SKPayment payment = SKPayment.paymentWithProduct(nativeProduct);
            ((SKPaymentQueue) SKPaymentQueue.defaultQueue()).addPayment(payment);
        }
    }

    @Override
    public void productsRequestDidReceiveResponse(SKProductsRequest skProductsRequest, SKProductsResponse skProductsResponse) {
        System.out.println();
        if (debugHandler != null) {
            debugHandler.callback("ios/PlatformIAPHelper [productsRequestDidReceiveResponse]",
                    "Loaded list of products...");
        }
        NSArray<? extends SKProduct> nativeProducts = skProductsResponse.products();
        SKProductDetails[] products = new SKProductDetails[nativeProducts.size()];
        nativeProductsMap.clear();
        for (int i = 0; i < nativeProducts.size(); i++) {
            nativeProductsMap.put(nativeProducts.get(i).productIdentifier(), nativeProducts.get(i));
            products[i] = new SKProductDetails(nativeProducts.get(i).productIdentifier(),
                    nativeProducts.get(i).price(), nativeProducts.get(i).localizedTitle(),
                    nativeProducts.get(i).localizedDescription());
        }

        if (completionHandler != null)
            completionHandler.callback(true, products);
        clearRequest();

        if (debugHandler != null) {
            for (SKProductDetails product : products) {
                debugHandler.callback("ios/PlatformIAPHelper [productsRequestDidReceiveResponse]",
                        "Found product: " + product.getProductID() + " " + product.getTitle() + " " + product.getPrice());
            }
        }
    }

    @Override
    public void requestDidFailWithError(SKRequest request, NSError error) {
        if (errorHandler != null) {
            errorHandler.callback("ios/PlatformIAPHelper [requestDidFailWithError]",
                    "Failed to load list of products. Error: " + error.localizedDescription());
        }
        if (debugHandler != null) {
            debugHandler.callback("ios/PlatformIAPHelper [requestDidFailWithError]",
                    "Failed to load list of products. Error: " + error.localizedDescription());
        }
        clearRequest();
    }

    private void clearRequest() {
        productsRequest = null;
        completionHandler = null;
    }

    @Override
    public void paymentQueueUpdatedTransactions(SKPaymentQueue skPaymentQueue, NSArray<? extends SKPaymentTransaction> nsArray) {
        for (int i = 0; i < nsArray.size(); i++) {
            SKPaymentTransaction transaction = nsArray.get(i);
            if (transaction.transactionState() == SKPaymentTransactionState.Purchased) {
                completeTransaction(transaction);
            } else if (transaction.transactionState() == SKPaymentTransactionState.Failed) {
                failedTransaction(transaction);
            } else if (transaction.transactionState() == SKPaymentTransactionState.Restored) {
                restoreTransaction(transaction);
            }
        }

    }

    private void completeTransaction(SKPaymentTransaction transaction) {
        provideContentForProductIdentifier(transaction.payment().productIdentifier());
        ((SKPaymentQueue) SKPaymentQueue.defaultQueue()).finishTransaction(transaction);
    }

    private void restoreTransaction(SKPaymentTransaction transaction) {
        String productIdentifier = transaction.originalTransaction().payment().productIdentifier();
        provideContentForProductIdentifier(productIdentifier);
        ((SKPaymentQueue) SKPaymentQueue.defaultQueue()).finishTransaction(transaction);
    }

    private void provideContentForProductIdentifier(String productIdentifier) {
        purchasedProductIdentifiers.add(productIdentifier);
        NSUserDefaults.standardUserDefaults().setBoolForKey(true, productIdentifier);
        NSUserDefaults.standardUserDefaults().synchronize();
        NSNotificationCenter.defaultCenter().postNotificationNameObject(IAPHelperProductPurchasedPublicKey, productIdentifier);
    }

    private void failedTransaction(SKPaymentTransaction transaction) {
        if (transaction.error().code() != Enums.SKErrorPaymentCancelled) {
            if (errorHandler != null) {
                errorHandler.callback("ios/PlatformIAPHelper [requestDidFailWithError]",
                        "Transaction error: " + transaction.error().localizedDescription());
            }
            if (debugHandler != null) {
                debugHandler.callback("ios/PlatformIAPHelper [requestDidFailWithError]",
                        "Transaction error: " + transaction.error().localizedDescription());
            }
        }
        ((SKPaymentQueue) SKPaymentQueue.defaultQueue()).finishTransaction(transaction);
    }
}
