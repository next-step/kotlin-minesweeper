package minesweeper

import global.strategy.split.SingleCommaSplitStrategy
import global.strategy.split.SplitStrategy
import global.strategy.ui.ConsoleInputStrategy
import global.strategy.ui.ConsoleOutputStrategy
import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Position
import minesweeper.domain.block.strategy.RandomMineBlockGenerateStrategy
import minesweeper.domain.board.Board
import minesweeper.domain.board.MineCount
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
        var board = Board.of(area, mineCount, RandomMineBlockGenerateStrategy)
        resultView.startGame()
        board = open(board, SingleCommaSplitStrategy)
        resultView.showResult(board)
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

    private fun open(board: Board, splitStrategy: SplitStrategy): Board {
        val openPosition = askOpenPosition(splitStrategy)
        val board = board.open(openPosition)
        if (board.isFinish()) {
            return board
        }
        resultView.showBoard(board)
        return open(board, splitStrategy)
    }

    private fun askOpenPosition(splitStrategy: SplitStrategy): Position =
        try {
            val openPosition = splitStrategy.split(inputView.askOpenPosition())
            Position(openPosition.first(), openPosition.last())
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
            askOpenPosition(splitStrategy)
        }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    val outputView = ResultView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    MinesweeperApplication(inputView, outputView, errorView).execute()
}
