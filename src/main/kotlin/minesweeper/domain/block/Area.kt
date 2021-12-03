package minesweeper.domain.block

import minesweeper.domain.Height
import minesweeper.domain.Width

class Area(private val width: Width, private val height: Height) {

    fun area(): Int = Math.multiplyExact(width.width, height.height)
}
