package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.os.Parcel
import android.os.Parcelable


/**
 * ScheduleData
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/25 13:45:30
 */
data class ScheduleData(val time: String, val todo: String) : Parcelable {

    /** ****************************** Object ****************************** */

    /**  */
    companion object CREATOR : Parcelable.Creator<ScheduleData> {
        override fun createFromParcel(parcel: Parcel): ScheduleData {
            return ScheduleData(parcel)
        }

        override fun newArray(size: Int): Array<ScheduleData?> {
            return arrayOfNulls(size)
        }
    }


    /** ****************************** Constructors ****************************** */

    /**  */
    constructor(parcel: Parcel) : this(parcel.readString() ?: "", parcel.readString() ?: "")


    /** ****************************** Override ****************************** */

    /**  */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(time)
        parcel.writeString(todo)
    }

    /**  */
    override fun describeContents(): Int {
        return 0
    }
}
