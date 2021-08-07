package com.example.bibit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MetaData(
    @SerializedName("Count") var count: Int? = null
) : Parcelable
