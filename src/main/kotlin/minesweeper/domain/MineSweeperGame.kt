package minesweeper.domain

class MineSweeperGame(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int
) {
    val cells = Array(height) { Array(width) { Cell() } }
}
