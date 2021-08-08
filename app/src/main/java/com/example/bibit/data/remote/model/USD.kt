package com.example.bibit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class USD(
    @SerializedName("TYPE") var type: String? = null,
    @SerializedName("MARKET") var market: String? = null,
    @SerializedName("FROMSYMBOL") var fromSymbol: String? = null,
    @SerializedName("PRICE") var price: Float? = null,
    @SerializedName("HIGHDAY") var highDay: Float? = null,
    @SerializedName("FLAGS") var flags: Int? = null
) : Parcelable