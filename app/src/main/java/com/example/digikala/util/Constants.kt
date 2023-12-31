package com.example.digikala.util

import com.example.digikala.BuildConfig

object Constants {
    const val English_Lang = "en"
    const val Persian_Lang = "fa"
    const val Datastore_Name = "Digikala_data_store"
    const val Timeout_In_Second: Long = 60
    const val Base_Url = "https://truelearn-digikala.iran.liara.run/api/"
    const val Api_Key = BuildConfig.X_API_KEY
    const val Key = BuildConfig.KEY
    const val Iv = BuildConfig.IV
    const val Shopping_Cart_Table = "shopping_cart"
    const val Database_Name = "app_db"

    var App_Language = "App_Language"
    var User_Token = "User_Token"
    var User_Id = "User_Id"
    var User_Phone = "User_Phone"
    var User_Password = "User_Password"

    const val Zarinpal_Merchant_Id = ""


    const val DIGIJET_URL = "https://www.digikalajet.com/user/address"
    const val AUCTION_URL =
        "https://www.digistyle.com/sale-landing/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=style&promo_name=style&promo_position=circle_badge"
    const val DIGIPAY_URL =
        "https://www.digikala.com/my-digipay/?promo_name=my-digipay&promo_position=circle_badge"
    const val PINDO_URL =
        "https://www.pindo.ir/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=pindo&promo_name=pindo&promo_position=circle_badge"
    const val SHOPPING_URL =
        "https://www.digikala.com/landings/village-businesses/?promo_name=boomi-landing&promo_position=circle_badge"
    const val GIFT_CARD_URL =
        "https://www.digikala.com/landing/gift-card-landing/?promo_name=gift_landing&promo_position=circle_badge"
    const val DIGIPLUS_URL =
        "https://www.digikala.com/plus/landing/?promo_name=plus&promo_position=circle_badge"
    const val MORE_URL =
        "https://www.digikala.com/mehr/?promo_name=mehr&promo_position=circle_badge"
}