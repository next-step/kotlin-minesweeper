package mine.sweeper.domain

import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Width

class MapSize(
    val height: Height,
    val width: Width,
) {
    fun area(): Int {
        return height.value * width.value
    }
}
