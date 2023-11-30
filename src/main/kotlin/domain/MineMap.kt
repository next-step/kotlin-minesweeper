package domain

import kotlin.random.Random

class MineMap(
    val height: Int,
    val width: Int,
    mineCount: Int = 10
) {

    private val mineMap: List<List<Spot>> =
        (initialList(mineCount, true) + initialList(height * width - mineCount, false))
            .shuffled()
            .chunked(width)

    init {
        require(height > 0)
        require(width > 0)
        require(mineCount in 0 until height * width)
    }

    fun get(x: Int, y: Int): Spot {
        return mineMap[y][x]
    }

    private fun initialList(size: Int, isMine: Boolean): List<Spot> =
        List(size) { Spot(isMine) }
}
