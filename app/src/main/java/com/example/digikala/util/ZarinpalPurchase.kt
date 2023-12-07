package com.example.digikala.util

import android.app.Activity
import android.util.Log
import com.example.digikala.util.Constants.Zarinpal_Merchant_Id
import com.zarinpal.ZarinPalBillingClient
import com.zarinpal.billing.purchase.Purchase
import com.zarinpal.client.BillingClientStateListener
import com.zarinpal.client.ClientState
import com.zarinpal.provider.core.future.FutureCompletionListener
import com.zarinpal.provider.core.future.TaskResult
import com.zarinpal.provider.model.response.Receipt

object ZarinpalPurchase {

    private val stateListener = object : BillingClientStateListener {
        override fun onClientServiceDisconnected() {
            Log.e("3636", "Billing client disconnected")
        }

        override fun onClientSetupFinished(state: ClientState) {

        }
    }

    fun purchase(
        activity: Activity,
        amount: Long,
        description: String,
        onCompletePurchase: (String) -> Unit
    ) {
        val client = ZarinPalBillingClient.newBuilder(activity)
            .enableShowInvoice()
            .setListener(stateListener)
            .build()

        val purchase = Purchase.newBuilder()
            .asPaymentRequest(
                Zarinpal_Merchant_Id,
                amount,
                "https://truelearn.ir/",
                description
            )
            .build()

        client.launchBillingFlow(purchase, object : FutureCompletionListener<Receipt> {
            override fun onComplete(task: TaskResult<Receipt>) {
                if (task.isSuccess) {
                    val receipt = task.success
                    receipt?.transactionID?.let {
                        onCompletePurchase(it)
                        Log.e("3636", it)
                    }
                } else {
                    task.failure?.printStackTrace()
                }
            }

        })

    }


    fun fakePurchase(
        activity: Activity,
        amount: Long,
        description: String,
        onCompletePurchase: (String) -> Unit
    ) {
        Thread.sleep(1000)
        onCompletePurchase(generateRandomString(8))
    }

    private fun generateRandomString(length: Int): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(length) { chars.random() }.joinToString("")
    }

}