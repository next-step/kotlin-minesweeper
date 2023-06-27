package minesweeper

import kotlin.random.Random


class RandomMineLocationGenerator : MineLocationGenerator {

    override fun generateMineLocation(maximumOfX: Int, maximumOfY: Int): MineLocation {
        val coordinateOfX = Random.nextInt(0, maximumOfX + 1)
        val coordinateOfY = Random.nextInt(0, maximumOfY + 1)
        return MineLocation(coordinateOfX, coordinateOfY)
    }
}