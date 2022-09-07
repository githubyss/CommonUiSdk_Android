package com.githubyss.mobile.common.ui.app.widget.widget_player

import android.os.Parcel
import android.os.Parcelable


/**
 * PlayData
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:16:46
 */
data class PlayData(val title: String) : Parcelable {

    /** ****************************** Object ****************************** */

    /**  */
    companion object CREATOR : Parcelable.Creator<PlayData> {
        override fun createFromParcel(parcel: Parcel): PlayData {
            return PlayData(parcel)
        }

        override fun newArray(size: Int): Array<PlayData?> {
            return arrayOfNulls(size)
        }
    }


    /** ****************************** Constructors ****************************** */

    /**  */
    constructor(parcel: Parcel) : this(parcel.readString() ?: "")


    /** ****************************** Override ****************************** */

    /**  */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    /**  */
    override fun describeContents(): Int {
        return 0
    }
}
