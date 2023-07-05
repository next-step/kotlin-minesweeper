package mine.sweeper.view.dto

import mine.sweeper.application.value.Height
import mine.sweeper.application.value.Width

data class MapSize(
    val height: Height,
    val width: Width,
) {
    fun area(): Int {
        return height.value * width.value
    }
}
