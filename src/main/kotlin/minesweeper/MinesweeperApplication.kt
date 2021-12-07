package minesweeper

import global.strategy.ConsoleInputStrategy
import global.strategy.ConsoleOutputStrategy
import minesweeper.domain.Board
import minesweeper.domain.MineCount
import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
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
