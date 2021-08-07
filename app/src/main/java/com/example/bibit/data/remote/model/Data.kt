package com.example.bibit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.graphics.Movie





@Parcelize
data class Data(
    @SerializedName("CoinInfo") var coinInfo: CoinInfo? = null,
    @SerializedName("RAW") var raw: Raw? = null,
    @SerializedName("DISPLAY") var Display: Display? = null
) : Parcelable


