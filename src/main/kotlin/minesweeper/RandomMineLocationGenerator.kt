package minesweeper

import kotlin.random.Random


class RandomMineLocationGenerator : MineLocationGenerator {

    override fun generateMineLocation(maximumOfX: Int, maximumOfY: Int): List<Location> {
        val locations = mutableListOf<Location>()
        val coordinateOfX = Random.nextInt(0, maximumOfX + 1)
        val coordinateOfY = Random.nextInt(0, maximumOfY + 1)
        Location(coordinateOfX, coordinateOfY)
        return locations.toList()
    }
}