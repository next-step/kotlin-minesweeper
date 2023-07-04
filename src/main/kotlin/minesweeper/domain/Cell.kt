package minesweeper.domain

sealed class Cell

class Mine : Cell()

class Normal(
    adjacentMineCount: Int = 0,
) : Cell() {
    var adjacentMineCount: Int = adjacentMineCount
        private set

    fun increaseAdjacentMineCount() {
        adjacentMineCount++
    }
}
