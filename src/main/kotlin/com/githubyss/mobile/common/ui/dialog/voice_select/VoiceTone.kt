package com.githubyss.mobile.common.ui.dialog.voice_select

import android.os.Parcel
import android.os.Parcelable


/**
 * VoiceTone
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/26 13:51:18
 */
data class VoiceTone(var id: Int, var name: String, var picUrl: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString() ?: "", parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(picUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VoiceTone> {
        override fun createFromParcel(parcel: Parcel): VoiceTone {
            return VoiceTone(parcel)
        }

        override fun newArray(size: Int): Array<VoiceTone?> {
            return arrayOfNulls(size)
        }
    }
}
