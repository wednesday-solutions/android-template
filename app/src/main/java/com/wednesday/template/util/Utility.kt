package com.wednesday.template.util

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

private val progressViewTag = 1119
private val progressContainerTag = 1120

fun addProgressIndicator(context: Context, parentViewGroup: ViewGroup): RelativeLayout {

    parentViewGroup.isEnabled = false
    val progressBarContainer = getContainerView(parentViewGroup, progressContainerTag)

    if (progressBarContainer != null) {
        progressBarContainer.bringToFront()
        return progressBarContainer
    }

    val container = createContainerView(context)

    container.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))

    val progressBar = ProgressBar(context)

    val progressBarParams = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )
    progressBarParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
    progressBar.layoutParams = progressBarParams

    progressBar.tag = progressViewTag
    container.tag = progressContainerTag

    container.addView(progressBar)

    parentViewGroup.addView(container)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        container.translationZ = 10f
    }
    return container
}

fun removeProgressIndicator(parentViewGroup: ViewGroup) {
    removeContainerView(parentViewGroup, progressContainerTag)
}

private fun createContainerView(context: Context): RelativeLayout {
    val container = RelativeLayout(context)

    val params = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.MATCH_PARENT
    )
    container.layoutParams = params
    container.setOnClickListener { }
    return container
}

private fun getContainerView(parentViewGroup: ViewGroup, containerTag: Int): RelativeLayout? {

    var progressBarContainer: RelativeLayout? = null
    for (childIndex in parentViewGroup.childCount - 1 downTo 0) {
        val view = parentViewGroup.getChildAt(childIndex)
        if (view != null && view.tag != null && view.tag == containerTag) {
            progressBarContainer = view as RelativeLayout
            break
        }
    }

    return progressBarContainer
}

private fun removeContainerView(parentViewGroup: ViewGroup?, containerViewTag: Int) {
    if (parentViewGroup != null) {
        val container = getContainerView(parentViewGroup, containerViewTag)
        if (container != null) {
            parentViewGroup.removeView(container)
        }
    }
}

fun hideKeyboard(activity: FragmentActivity) {
    if (activity.currentFocus != null) {
        val inputManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}
