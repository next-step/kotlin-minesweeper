package minesweeper

import global.strategy.ConsoleInputStrategy
import global.strategy.ConsoleOutputStrategy
import minesweeper.domain.Board
import minesweeper.domain.MineCount
import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.RandomMineBlockGenerateStrategy
import minesweeper.ui.ErrorView
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

class MinesweeperApplication(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val errorView: ErrorView
) {
    fun execute() {
        val area = askArea()
        val mineCount = askMineCount()
        val board = Board.of(area, mineCount, RandomMineBlockGenerateStrategy)
        resultView.startGame(board)
        val openPosition = askOpenPosition()
        val openedBoard = board.open(openPosition)
        openedBoard?.let { resultView.startGame(it) }

        val openPosition2 = askOpenPosition()
        val openedBoard2 = openedBoard!!.open(openPosition2)
        openedBoard2?.let { resultView.startGame(it) }
    }

    private fun askOpenPosition(): Position =
        try {
            val openPosition = inputView.askOpenPosition().split(", ")
            Position(Integer.valueOf(openPosition[0]), Integer.valueOf(openPosition[1]))
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
            askOpenPosition()
        }

    private fun askArea(): Area =
        try {
            Area(askWidth(), askHeight())
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
            askArea()
        }

    private fun askHeight(): Height =
        try {
            Height(inputView.askHeight())
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
            askHeight()
        }

    private fun askWidth(): Width =
        try {
            Width(inputView.askWidth())
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
            askWidth()
        }

    private fun askMineCount(): MineCount =
        try {
            MineCount(inputView.askMineCount())
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
            askMineCount()
        }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    val outputView = ResultView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    MinesweeperApplication(inputView, outputView, errorView).execute()
}
