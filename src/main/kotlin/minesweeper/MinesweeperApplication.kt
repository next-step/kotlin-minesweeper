package minesweeper

import global.strategy.ConsoleInputStrategy
import global.strategy.ConsoleOutputStrategy
import minesweeper.domain.Height
import minesweeper.domain.MinesCount
import minesweeper.domain.Width
import minesweeper.domain.block.Area
import minesweeper.ui.ErrorView
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

class MinesweeperApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val errorView: ErrorView
) {
    fun execute() {

        val area = askArea()
        val minesCount = askMinesCount()
        // Board.of(area, minesCount)
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
    val outputView = OutputView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    MinesweeperApplication(inputView, outputView, errorView).execute()
}
