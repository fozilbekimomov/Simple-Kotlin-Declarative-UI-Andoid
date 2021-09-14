package uz.ultragroup.mod38_kotlin.helpers

import android.R
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.util.StateSet
import androidx.core.content.res.ResourcesCompat


/**
 * Get [StateListDrawable] given the `normalColor` and `pressedColor`
 * for dynamic button coloring
 *
 * @param normalColor  The color in normal state.
 * @param pressedColor The color in pressed state.
 * @return
 */
fun getStateListColoredDrawable(normalColor: Int, pressedColor: Int): StateListDrawable {
    val stateListDrawable = StateListDrawable()
    stateListDrawable.addState(intArrayOf(R.attr.state_pressed), ColorDrawable(pressedColor))
    stateListDrawable.addState(StateSet.WILD_CARD, ColorDrawable(normalColor))
    return stateListDrawable
}


/**
 * Get [StateListDrawable] given the `normalDrawable` and `pressedDrawable`
 * for dynamic button coloring
 *
 * @param normalDrawable  The drawable in normal state.
 * @param pressedDrawable The drawable in pressed state.
 * @return
 */
fun Context.getStateListDrawable(normalDrawable: Int, pressedDrawable: Int): StateListDrawable {
    val stateListDrawable = StateListDrawable()
    stateListDrawable.addState(
        intArrayOf(R.attr.state_checked),
        ResourcesCompat.getDrawable(resources, pressedDrawable, theme)
    )
    stateListDrawable.addState(
        StateSet.WILD_CARD,
        ResourcesCompat.getDrawable(resources, normalDrawable, theme)
    )
    return stateListDrawable
}

