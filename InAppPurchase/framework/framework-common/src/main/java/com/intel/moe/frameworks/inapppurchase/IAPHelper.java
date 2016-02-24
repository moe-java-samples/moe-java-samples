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

import java.lang.Override;
import java.lang.reflect.InvocationTargetException;

/**
 * Common class for using store API
 */
public class IAPHelper extends AbstractIAPHelper {
    AbstractIAPHelper iapHelper = null;

    /**
     * Detect Android platform
     * @return false if platform isn't Android else true
     */
    private boolean isAndroid() {
        if (!System.getProperty("os.name", "generic").equals("Linux") ||
                !System.getProperty("java.runtime.name").equals("Android Runtime"))
            return false;
        try {
            Class.forName("com.intel.moe.frameworks.inapppurchase.android.PlatformIAPHelper");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Detect iOS platform
     * @return false if platform isn't iOS else true
     */
    private boolean isIOS() {
        if (!System.getProperty("os.name", "generic").equals("Darwin") ||
                !System.getProperty("java.runtime.name").equals("Android Runtime"))
            return false;
        try {
            Class.forName("com.intel.moe.frameworks.inapppurchase.ios.PlatformIAPHelper");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }


    public IAPHelper() {
        if (isAndroid()) {
            try {
                iapHelper = (AbstractIAPHelper) Class.forName("com.intel.moe.frameworks.inapppurchase.android.PlatformIAPHelper").getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (isIOS()) {
            try {
                iapHelper = (AbstractIAPHelper) Class.forName("com.intel.moe.frameworks.inapppurchase.ios.PlatformIAPHelper").getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("IAPHelper [init]: Unknown platform!");
        }
    }

    /**
     * Restore transactions
     */
    @Override
    public void restoreCompletedTransactions() {
        if (iapHelper != null) {
            iapHelper.restoreCompletedTransactions();
        }
    }

    /**
     * Close helper
     */
    @Override
    public void closeHelper() {
        if (iapHelper != null) {
            iapHelper.closeHelper();
        }
    }

    /**
     * Set secure public key
     * @param key - secure key
     */
    @Override
    public void setIAPHelperPublicKey(String key) {
        if (iapHelper != null) {
            iapHelper.setIAPHelperPublicKey(key);
        }
    }

    /**
     * Get secure public key
     * @return secure key
     */
    @Override
    public String getIAPHelperPublicKey() {
        if (iapHelper != null) {
            return iapHelper.getIAPHelperPublicKey();
        }
        return null;
    }

    /**
     * Set debug handler for get information from IAPHelper
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.DebugHandler}
     */
    @Override
    public void setDebugHandler(DebugHandler handler) {
        if (iapHelper != null) {
            iapHelper.setDebugHandler(handler);
        }
    }

    /**
     * Set product identifiers
     * @param productIdentifiers - list with products IDs
     */
    @Override
    public void setProductIdentifiers(String[] productIdentifiers) {
        if (iapHelper != null) {
            iapHelper.setProductIdentifiers(productIdentifiers);
        }
    }

    /**
     * Set Android Context
     * @param ctx - Android context
     */
    @Override
    public void setContext(Object ctx) {
        if (iapHelper != null) {
            iapHelper.setContext(ctx);
        }
    }

    /**
     * Init IAPHelper
     */
    @Override
    public void initHelper() {
        if (iapHelper != null) {
            iapHelper.initHelper();
        }
    }

    /**
     * Get list of products
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.RequestProductsCompletionHandler}
     */
    @Override
    public void requestProductsWithCompletionHandler(RequestProductsCompletionHandler handler) {
        if (iapHelper != null) {
            iapHelper.requestProductsWithCompletionHandler(handler);
        }
    }

    /**
     * Purchase product
     * @param product - {@link CommonProductDetails}
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.RequestPurchaseProductsHandler}
     */
    @Override
    public void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler) {
        if (iapHelper != null) {
            iapHelper.purchaseProduct(product, handler);
        }
    }

    /**
     * Purchase product
     * @param product - {@link CommonProductDetails}
     * @param handler - {@link com.intel.moe.frameworks.inapppurchase.IAPHelperInterface.RequestPurchaseProductsHandler}
     * @param verificationPayload - string with hash sum for check purchase
     */
    @Override
    public void purchaseProduct(CommonProductDetails product, RequestPurchaseProductsHandler handler, String verificationPayload) {
        if (iapHelper != null) {
            iapHelper.purchaseProduct(product, handler, verificationPayload);
        }
    }

    /**
     * Check that you can make payments
     * @return true if can make payments else false
     */
    @Override
    public boolean canMakePayments() {
        if (iapHelper != null) {
            return iapHelper.canMakePayments();
        }
        return false;
    }

    /**
     * Handle activity result
     * @param requestCode
     * @param resultCode
     * @param data
     * @return
     */
    @Override
    public boolean handleActivityResult(int requestCode, int resultCode, Object data) {
        if (iapHelper != null) {
            return iapHelper.handleActivityResult(requestCode, resultCode, data);
        }
        return false;
    }

    /**
     * Function for checking product status
     * @param productID - Product identification
     * @return true if product purchased else false
     */
    @Override
    public boolean isProductPurchased(String productID) {
        if (iapHelper != null) {
            return iapHelper.isProductPurchased(productID);
        }
        return false;
    }
}
