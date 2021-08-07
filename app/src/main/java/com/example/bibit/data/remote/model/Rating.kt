package com.example.bibit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    @SerializedName("Weiss") var weiss: Weiss? = null
) : Parcelable