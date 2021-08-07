package com.example.bibit.data.remote.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("Message") var message: String? = null,
    @SerializedName("Type") var type: Int? = null,
    @SerializedName("MetaData") var metaData: MetaData? = null,
    @SerializedName("SponsoredData") var sponsoredData: List<SponsoredData>? = null,
    @SerializedName("Data") var data: ArrayList<Data>? = null
)
