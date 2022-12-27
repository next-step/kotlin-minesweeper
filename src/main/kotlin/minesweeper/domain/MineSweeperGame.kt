package minesweeper.domain

class MineSweeperGame(
    height: Int,
    private val width: Int,
    mineCount: Int,
    randomCoordinateGenerator: RandomCoordinateGenerator
) {
    val cells = Array(height) { Array(width) { Cell() } }
    private val minesCoordinates = randomCoordinateGenerator.generate(width, height, mineCount)

    init {
        minesCoordinates.forEach {
            cells[it.y][it.x].locateMine()
        }
    }
}
