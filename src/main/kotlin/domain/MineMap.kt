package domain

import kotlin.random.Random

class MineMap(
    val height: Int,
    val width: Int
) {

    private val mineMap: Array<Array<Spot>> = Array(height) { Array(width) { Spot(Random.nextBoolean()) } }

    fun get(x: Int, y: Int): Spot {
        return mineMap[y][x]
    }
}
