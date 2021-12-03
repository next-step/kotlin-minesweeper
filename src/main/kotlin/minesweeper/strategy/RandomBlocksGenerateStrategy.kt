package minesweeper.strategy

import minesweeper.domain.block.Block
import minesweeper.domain.block.Cell
import minesweeper.domain.block.Position
import kotlin.random.Random

object RandomBlocksGenerateStrategy : BlocksGenerateStrategy {
    override fun generate(width: Int, height: Int, minesCount: Int): List<Block> {
        val map: List<Cell> = (0 until height).flatMap { x -> (0 until width).map { y -> Position(x, y) } }
            .map { position -> Cell(position) }
        return map
    }

    /**
     * (0 until height).map {
     *     (0 until width)
     * .map { 99 }
     * .toMutableList()
     * }.toMutableList().shuffle()
     * */
    fun setMine(numberOfMines: Int, width: Int, height: Int, board: MutableList<MutableList<Int>>) {
        var numberOfMines = numberOfMines
        while (numberOfMines-- > 0) {
            val row = Random.nextInt(height)
            val column = Random.nextInt(width)

            if (board[row][column] == -1) {
                numberOfMines++
            }
            if (board[row][column] != -1) {
                board[row][column] = -1
            }
        }
    }
}
