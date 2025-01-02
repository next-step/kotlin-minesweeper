package controller

import domain.Cells
import domain.Col
import domain.Coordinate
import domain.MineBoard
import domain.MineSweeperGame
import domain.MineSweeperMetric
import domain.Row
import domain.strategy.MineCellGenerator
import domain.strategy.RandomMineCellGenerator
import view.InputView
import view.OutputView

class MineSweeperController {
    fun run() {
        val mineSweeperMetric = initializeMineSweeperMetric()
        val mineCellGenerator: MineCellGenerator = RandomMineCellGenerator(mineSweeperMetric)
        val cells = Cells.of(mineCellGenerator)
        val mineBoard = MineBoard(cells)

        OutputView.showMineSweeperBoard(mineBoard)

        gameLoop(mineBoard)
    }

    private fun initializeMineSweeperMetric(): MineSweeperMetric {
        val mineBoardHeight = InputView.inputMineBoardHeight()
        val mineBoardWidth = InputView.inputMineBoardWidth()
        val mineCount = InputView.inputMineCount()
        return MineSweeperMetric(
            mineBoardHeight = mineBoardHeight,
            mineBoardWidth = mineBoardWidth,
            mineCount = mineCount,
        )
    }

    private fun gameLoop(mineBoard: MineBoard) {
        val mineSweeperGame = MineSweeperGame(mineBoard)
        while (mineSweeperGame.isContinueGame()) {
            val requestOpenCoordinate = parseToCoordinateOrThrow(InputView.askMineCoordinate())
            mineSweeperGame.openAdjacentCell(requestOpenCoordinate)
            OutputView.showMineSweeperBoard(mineBoard)
        }

        val result = mineSweeperGame.getGameResult()
        OutputView.showGameResult(result)
    }

    private fun parseToCoordinateOrThrow(value: String): Coordinate {
        return value.replace(" ", "")
            .split(",")
            .also { require(it.size == 2) { INVALID_COORDINATE + value } }
            .map { it.toIntOrNull() ?: throw IllegalArgumentException(INVALID_COORDINATE) }
            .let { (row, col) -> Coordinate(Row(row), Col(col)) }
    }

    companion object {
        private const val INVALID_COORDINATE = "두가지 숫자를 콤마(,)로 구분해서 입력해주세요 "
    }
}
