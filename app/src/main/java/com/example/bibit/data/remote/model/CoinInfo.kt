package com.example.bibit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinInfo(
    @SerializedName("Id") var id: String? = null,
    @SerializedName("Name") var name: String? = null,
    @SerializedName("FullName") var fullName: String? = null,
    @SerializedName("Internal") var internal: String? = null,
    @SerializedName("ImageUrl") var imageUrl: String? = null,
    @SerializedName("Url") var url: String? = null,
    @SerializedName("Algorithm") var algorithm: String? = null,
    @SerializedName("ProofType") var proofType: String? = null,
    @SerializedName("Rating") var rating: Rating? = null,
    @SerializedName("BlockNumber") var blockNumber: Int? = null,
    @SerializedName("BlockTime") var blockTime: Float? = null,
    @SerializedName("BlockReward") var blockReward: Float? = null,
    @SerializedName("AssetLaunchDate") var assetLaunchDate: String? = null,
    @SerializedName("MaxSupply") var maxSupply: Float? = null,
    @SerializedName("Type") var type: Int? = null,
    @SerializedName("DocumentType") var documentType: String? = null
) : Parcelable