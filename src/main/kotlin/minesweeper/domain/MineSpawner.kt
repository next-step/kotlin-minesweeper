package minesweeper.domain

import kotlin.random.Random

interface MineSpawner {
    fun spawn(boardSize: BoardSize, count: MineCount): List<Coordinate>
}

object RandomMineSpawner : MineSpawner {
    override fun spawn(boardSize: BoardSize, count: MineCount): List<Coordinate> {
        val mines = mutableSetOf<Coordinate>()
        while (count > mines.size) {
            val randomY = Random.nextInt(boardSize.height)
            val randomX = Random.nextInt(boardSize.width)
            mines.add(Coordinate(randomX, randomY))
        }
        return mines.toList()
    }
}
