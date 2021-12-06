package minesweeper

import global.strategy.ConsoleInputStrategy
import global.strategy.ConsoleOutputStrategy
import minesweeper.domain.Board
import minesweeper.domain.MinesCount
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
        val minesCount = askMinesCount()
        val board = Board.of(area, minesCount, RandomMineBlockGenerateStrategy)
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

    private fun askMinesCount(): MinesCount =
        try {
            MinesCount(inputView.askMinesCount())
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
            askMinesCount()
        }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    val outputView = ResultView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    MinesweeperApplication(inputView, outputView, errorView).execute()
}
