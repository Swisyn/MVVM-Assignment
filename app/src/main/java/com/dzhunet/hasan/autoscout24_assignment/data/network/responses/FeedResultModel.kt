package com.dzhunet.hasan.autoscout24_assignment.data.network.responses

import android.os.Parcelable
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class FeedResultModel(
    @SerializedName("colour")
    val colour: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("firstRegistration")
    val firstRegistration: String?,
    @SerializedName("fuel")
    val fuel: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("images")
    val images: MutableList<Image>?,
    @SerializedName("make")
    val make: String?,
    @SerializedName("mileage")
    val mileage: Int?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("modelline")
    val modelline: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("seller")
    val seller: Seller?
) : Parcelable {

    @Keep
    @Parcelize
    data class Image(
        @SerializedName("url")
        val url: String?
    ) : Parcelable

    @Keep
    @Parcelize
    data class Seller(
        @SerializedName("city")
        val city: String?,
        @SerializedName("phone")
        val phone: String?,
        @SerializedName("type")
        val type: String?
    ) : Parcelable
}




