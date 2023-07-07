package mine.sweeper.view.dto

import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Width

data class MapSize(
    val height: Height,
    val width: Width,
) {
    val area: Int
        get() = height.value * width.value
}
