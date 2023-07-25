package minesweeper.domain

import minesweeper.domain.field.Mark
import minesweeper.domain.field.Position
import minesweeper.ui.InputView
import minesweeper.ui.OutputView
import kotlin.random.Random

class Minesweeper(private val input: InputView, private val output: OutputView) {
    fun start() {
        val width = input.getWidth()
        val height = input.getHeight()
        val mineCount = input.getMineCount()

        val board = create(width, height)
        val minePositions = generateMinePositions(width, height, mineCount)
        val mineBoard = board.plantMines(minePositions)
        output.printStart(mineBoard)
    }

    private fun create(width: Int, height: Int): Board {
        val board = initBaseBoard(width, height)
        return Board(board.toMap())
    }

    private fun generateMinePositions(width: Int, height: Int, mineCount: Int): Set<Position> {
        return generateSequence { generateRandomPosition(width, height) }
            .distinct()
            .take(mineCount)
            .toSet()
    }

    private fun generateRandomPosition(width: Int, height: Int): Position {
        return Position(Random.nextInt(0, width), Random.nextInt(0, height))
    }

    private fun initBaseBoard(width: Int, height: Int): MutableMap<Position, Mark> {
        return generateBoardPositions(width, height)
            .associateWith { Mark.SAFE }
            .toMutableMap()
    }

    private fun generateBoardPositions(width: Int, height: Int): List<Position> {
        return (0 until width).flatMap { i ->
            (0 until height).map { j ->
                Position(i, j)
            }
        }
    }
}
