package minesweeper.domain

interface MineSpawner {
    fun spawn(boardSize: BoardSize, count: MineCount): Coordinates
}

object RandomMineSpawner : MineSpawner {
    override fun spawn(boardSize: BoardSize, count: MineCount): Coordinates = Coordinates
        .coordinatesInArea(boardSize.height, boardSize.width)
        .shuffled()
        .take(count.toInt())
        .toCoordinates()
}
