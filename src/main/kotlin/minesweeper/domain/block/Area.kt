package minesweeper.domain.block

import minesweeper.domain.Height
import minesweeper.domain.Width

class Area(private val width: Width, private val height: Height) {

    fun width(): Int = width.width

    fun height(): Int = height.height

    fun area(): Int = Math.multiplyExact(width.width, height.height)
}
