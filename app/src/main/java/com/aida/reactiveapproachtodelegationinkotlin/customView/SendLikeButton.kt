package com.aida.reactiveapproachtodelegationinkotlin.customView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.aida.reactiveapproachtodelegationinkotlin.R
import kotlin.properties.Delegates


class SendLikeButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr) {

    var state by Delegates.observable<ButtonState>(
        ButtonState.ShowUnLiked
    ) { p, oldValue, newValue ->
        when (newValue) {
            ButtonState.SendComment -> {
                when (oldValue) {
                    ButtonState.ShowUnLiked -> morph(unlikeToSend)
                    ButtonState.ShowLiked -> morph(likeToSend)
                }
            }
            ButtonState.ShowLiked -> {
                when (oldValue) {
                    ButtonState.ShowUnLiked -> setBackgroundResource(redLike)
                    ButtonState.SendComment -> morph(sendToLike)
                }
            }
            ButtonState.ShowUnLiked -> {
                when (oldValue) {
                    ButtonState.SendComment -> morph(sendToUnlike)
                    ButtonState.ShowLiked, ButtonState.ShowUnLiked -> setBackgroundResource(grayLike)
                }
            }
        }
    }

    private val sendToLike by bindAnimation(R.drawable.avd_send_to_favorite)

    private val likeToSend by bindAnimation(R.drawable.avd_favorite_to_send)

    private val unlikeToSend by bindAnimation(R.drawable.avd_gray_favorite_to_send)

    private val sendToUnlike by bindAnimation(R.drawable.avd_send_to_gray_favorite)

    private val redLike by lazy { R.drawable.ic_favorite_red_24dp }

    private val grayLike by lazy { R.drawable.ic_favorite_gray_24dp }

    private fun morph(avd: AnimatedVectorDrawableCompat?) {
        background = avd
        avd?.start()
    }
}

fun View.bindAnimation(animateRes: Int) = lazy {
    AnimatedVectorDrawableCompat.create(context, animateRes)
}

sealed class ButtonState {
    object ShowLiked : ButtonState()
    object ShowUnLiked : ButtonState()
    object SendComment : ButtonState()
}
