package com.githubyss.common.ui.dialog.voice_select

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.databinding.ObservableField


/**
 * VoiceTone
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/26 13:51:18
 */
class VoiceTone(var id: Int, var name: String, @DrawableRes var picDrawableId: Int, @VoiceToneSelectState selected: String) : Parcelable/*, BaseObservable()*/ {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object CREATOR : Parcelable.Creator<VoiceTone> {
        override fun createFromParcel(parcel: Parcel): VoiceTone {
            return VoiceTone(parcel)
        }

        override fun newArray(size: Int): Array<VoiceTone?> {
            return arrayOfNulls(size)
        }
    }


    /** ****************************** Properties ****************************** */

    /**  */
    val selected by lazy { ObservableField<String>() }
    // val selected by lazy { MutableLiveData<String>() }

    // @VoiceToneSelectState
    // var selected: String = selected
    //     // @Bindable get
    //     set(value) {
    //         if (selected != value) {
    //             field = value
    //             notifyChange()
    //         }
    //     }


    /** ****************************** Constructors ****************************** */

    /**  */
    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString() ?: "", parcel.readInt(), parcel.readString() ?: "")

    init {
        this.selected.set(selected)
        // this.selected.value = selected
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(picDrawableId)
        parcel.writeString(selected.get())
        // parcel.writeString(selected.value)
    }

    /**  */
    override fun describeContents(): Int {
        return 0
    }
}
