package com.example.bibit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weiss(
    @SerializedName("Rating") var rating: String? = null,
    @SerializedName("TechnologyAdoptionRating") var technologyAdoptionRating: String? = null,
    @SerializedName("MarketPerformanceRating") var marketPerformanceRating: String? = null
) : Parcelable