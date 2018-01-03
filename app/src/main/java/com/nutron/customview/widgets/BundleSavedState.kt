package com.nutron.customview.widgets

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View


class BundleSavedState : View.BaseSavedState {

    var bundle = Bundle()

    constructor(source: Parcel): super(source) {
        bundle = source.readBundle()
    }

    constructor(superState: Parcelable) : super(superState)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeBundle(bundle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BundleSavedState> {
        override fun createFromParcel(parcel: Parcel): BundleSavedState {
            return BundleSavedState(parcel)
        }

        override fun newArray(size: Int): Array<BundleSavedState?> {
            return arrayOfNulls(size)
        }
    }


}