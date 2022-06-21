package domain

import java.util.*

object MineFactory {
    fun of(width: Int, height: Int): Mine {
        val (x, y) = mineCoordinate(width, height)
        return Mine(x, y)
    }

    private fun mineCoordinate(maxWidth: Int, maxHeight: Int): Pair<Int, Int> {
        val random = Random().nextInt(maxWidth * maxHeight)
        return Pair(random / maxWidth, random % maxWidth)
    }
}
