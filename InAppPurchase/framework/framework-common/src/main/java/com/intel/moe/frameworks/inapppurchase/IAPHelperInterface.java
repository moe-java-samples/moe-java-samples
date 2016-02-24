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
package com.intel.moe.frameworks.inapppurchase;


public interface IAPHelperInterface {

    /**
     * Restore transactions
     */
    void restoreCompletedTransactions();

    /**
     * Close helper
     */
    void closeHelper();

    /**
     * Set debug handler for get information from IAPHelper
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.DebugHandler}
     */
    void setDebugHandler(DebugHandler handler);

    /**
     * Set Android Context
     * @param ctx - Android context
     */
    void setContext(Object ctx);

    /**
     * Init IAPHelper
     */
    void initHelper();

    /**
     * Get list of products
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.RequestProductsCompletionHandler}
     */
    void requestProductsWithCompletionHandler(RequestProductsCompletionHandler handler);

    /**
     * Purchase product
     * @param product - {@link CommonProductDetails}
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.RequestPurchaseProductsHandler}
     */
    void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler);

    /**
     * Purchase product
     * @param product - {@link CommonProductDetails}
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.RequestPurchaseProductsHandler}
     * @param verificationPayload - string with hash sum for check purchase
     */
    void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler, String verificationPayload);

    /**
     * Check that you can make payments
     * @return true if can make payments else false
     */
    boolean canMakePayments();

    /**
     * Handle activity result
     * @param requestCode
     * @param resultCode
     * @param data
     * @return
     */
    boolean handleActivityResult(int requestCode, int resultCode, Object data);

    /**
     * Callback for getting product list
     */
    interface RequestProductsCompletionHandler {
        void callback(boolean status, CommonProductDetails[] products);
    }

    /**
     * Callback for purchasing product
     */
    interface RequestPurchaseProductsHandler {
        void callback(boolean status);
    }

    /**
     * Callback for getting errors from IAPHelper
     */
    interface ErrorHandler {
        void callback(String component, String errorMsg);
    }

    /**
     * Callback for getting debug information from IAPHelper
     */
    interface DebugHandler {
        void callback(String component, String msg);
    }

    /**
     * Verification Payload callback
     */
    interface CustomVerifyPayloadHandler {
        boolean callback(Object obj);
    }
}
