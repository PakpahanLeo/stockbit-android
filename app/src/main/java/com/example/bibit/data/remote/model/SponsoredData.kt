package com.example.bibit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SponsoredData(
    @SerializedName("CoinInfo") var coinInfo: CoinInfo? = null
) : Parcelable