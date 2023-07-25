package minesweeper.domain

import minesweeper.domain.field.Mark
import minesweeper.domain.field.Position
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

        val board = create(width, height)
        val minePositions = positionGenerator.get(width, height, mineCount)
        val mineBoard = board.plantMines(minePositions)
        output.printStart(mineBoard)
    }

    private fun create(width: Int, height: Int): Board {
        val board = generateAllPositions(width, height)
            .associateWith { Mark.SAFE }
            .toMutableMap()
        return Board(board.toMap())
    }

    private fun generateAllPositions(width: Int, height: Int): List<Position> {
        return (0 until width).flatMap { i ->
            (0 until height).map { j ->
                Position(i, j)
            }
        }
    }
}
