package com.nutron.customview.widgets

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.widget.FrameLayout


class BaseCustomViewGroup : FrameLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()

        // Save Children's state as a Bundle
        val childrenStates = Bundle()
        for (i in 0 until childCount) {
            val id = getChildAt(i).id
            if (id != 0) {
                val childrenState = SparseArray<Parcelable>()
                getChildAt(i).saveHierarchyState(childrenState)
                childrenStates.putSparseParcelableArray(id.toString(), childrenState)
            }
        }

        // Save it to Parcelable
        val ss = BundleSavedState(superState)
        ss.bundle.putBundle("childrenStates", childrenStates)
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val ss = state as BundleSavedState
        super.onRestoreInstanceState(ss.getSuperState())

        // Restore SparseArray
        val childrenStates = ss.bundle.getBundle("childrenStates")
        // Restore Children's state
        for (i in 0 until childCount) {
            val id = getChildAt(i).id
            if (id != 0) {
                if (childrenStates.containsKey(id.toString())) {
                    val childrenState = childrenStates.getSparseParcelableArray<Parcelable>(id.toString())
                    getChildAt(i).restoreHierarchyState(childrenState)
                }
            }
        }
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        dispatchThawSelfOnly(container)
    }

}