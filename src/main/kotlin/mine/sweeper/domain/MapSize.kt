package mine.sweeper.domain

import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.Width

class MapSize(
    private val height: Height,
    private val width: Width
) {
    fun height(): Int {
        return height.value
    }

    fun width(): Int {
        return width.value
    }

    fun area(): Int {
        return height.value * width.value
    }
}
