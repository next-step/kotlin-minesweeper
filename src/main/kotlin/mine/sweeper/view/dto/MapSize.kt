package mine.sweeper.view.dto

import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Width

data class MapSize(
    val height: Height,
    val width: Width,
) {
    fun area(): Int {
        return height.value * width.value
    }
}
