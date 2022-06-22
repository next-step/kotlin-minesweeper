package minesweeper.domain

interface MineSpawner {
    fun spawn(area: Area, count: MineCount): Coordinates
}

object RandomMineSpawner : MineSpawner {
    override fun spawn(area: Area, count: MineCount): Coordinates = Coordinates
        .coordinatesInArea(area)
        .shuffled()
        .take(count.toInt())
        .toCoordinates()
}
