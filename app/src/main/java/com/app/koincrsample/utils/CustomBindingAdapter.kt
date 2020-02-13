package com.app.koincrsample.utils

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation
import com.app.koincrsample.R
import java.util.concurrent.atomic.AtomicBoolean


/**
 * @AUTHOR Pushkar Srivastava
 * @date 12/02/2020
 */
@BindingAdapter(value = ["app:error_text"], requireAll = false)
fun setErrorText(editText: EditText, strOrResId: Any) {
    if (strOrResId is Int) {
        editText.error = editText.context.getString(strOrResId)
    } else {
        editText.error = strOrResId as String
    }
}

@BindingAdapter(value = ["app:image_path"], requireAll = false)
fun setImage(imageView: ImageView, imagePath: String) {
    imageView.load(imagePath) {
        placeholder(R.drawable.ic_user_placeholder)
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSingleClickListener(it))
    } ?: setOnClickListener(null)
}

class OnSingleClickListener(
    private val clickListener: View.OnClickListener,
    private val intervalMs: Long = 1000
) : View.OnClickListener {
    private var canClick = AtomicBoolean(true)

    override fun onClick(v: View?) {
        if (canClick.getAndSet(false)) {
            v?.run {
                postDelayed({
                    canClick.set(true)
                }, intervalMs)
                clickListener.onClick(v)
            }
        }
    }
}





