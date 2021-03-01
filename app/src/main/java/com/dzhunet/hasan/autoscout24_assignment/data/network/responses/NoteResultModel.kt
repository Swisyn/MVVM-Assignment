package com.dzhunet.hasan.autoscout24_assignment.data.network.responses

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
data class NoteResultModelItem(
    @SerializedName("note")
    val note: String,
    @SerializedName("vehicleId")
    val vehicleId: Int
) : Parcelable