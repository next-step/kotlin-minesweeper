package minesweeper.domain

import minesweeper.domain.generator.PositionGenerator
import minesweeper.domain.generator.RandomPositionGenerator
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

class Minesweeper(
    private val input: InputView,
    private val output: OutputView,
    private val positionGenerator: PositionGenerator = RandomPositionGenerator()
) {
    fun start() {
        val width = input.getWidth()
        val height = input.getHeight()
        val mineCount = input.getMineCount()

        val boardMeta = BoardMeta(width, height, mineCount)
        val minePositions = positionGenerator.get(boardMeta)
        val board = Board.create(boardMeta.boardSize, minePositions)
        output.printStart(board)
    }
}
