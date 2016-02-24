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

import java.util.ArrayList;

public abstract class AbstractIAPHelper implements IAPHelperInterface {
    /**
     * Callbacks
     */
    protected CustomVerifyPayloadHandler customVerifyPayloadHandler = null;
    protected RequestProductsCompletionHandler completionHandler = null;
    protected RequestPurchaseProductsHandler purchaseHandler = null;
    protected ErrorHandler errorHandler = null;
    protected DebugHandler debugHandler = null;

    /**
     * Public security key
     */
    protected static String IAPHelperProductPurchasedPublicKey = "";

    /**
     * Products identification
     */
    protected ArrayList<String> productIdentifiers = new ArrayList<>();
    protected ArrayList<String> purchasedProductIdentifiers = new ArrayList<>();

    /**
     * Set secure public key
     * @param key - secure key
     */
    public void setIAPHelperPublicKey(String key) {
        IAPHelperProductPurchasedPublicKey = key;
    }

    /**
     * Get secure public key
     * @return secure key
     */
    public String getIAPHelperPublicKey() {
        return IAPHelperProductPurchasedPublicKey;
    }

    /**
     * Set callback for getting errors
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.ErrorHandler}
     */
    public void setErrorHandler(ErrorHandler handler) {
        errorHandler = handler;
    }

    /**
     * Callback for verification payload
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.CustomVerifyPayloadHandler}
     */
    public void setCustomVerifyPayloadHandler(CustomVerifyPayloadHandler handler) {
        customVerifyPayloadHandler = handler;
    }

    /**
     * Set product identifiers
     * @param productIds - list with products IDs
     */
    public void setProductIdentifiers(String[] productIds) {
        for (int i = 0; i < productIds.length; i++) {
            productIdentifiers.add(productIds[i]);
        }
    }

    /**
     * Function for checking product status
     * @param productIdentifier - Product identification
     * @return true if product purchased else false
     */
    public boolean isProductPurchased(String productIdentifier) {
        return purchasedProductIdentifiers.contains(productIdentifier);
    }
}
