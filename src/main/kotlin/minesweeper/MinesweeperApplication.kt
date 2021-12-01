package minesweeper

import global.strategy.ConsoleInputStrategy
import global.strategy.ConsoleOutputStrategy
import minesweeper.ui.ErrorView
import minesweeper.ui.InputView
import minesweeper.ui.OutputView
import kotlin.random.Random

class MinesweeperApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val errorView: ErrorView
) {
    fun execute() {

        val height = askHeight()
        val width = inputView.askWidth()
        val numberOfMines = inputView.askNumberOfMines()

        val board = (0 until height)
            .map { (0 until width).toMutableList() }
            .toMutableList()

        setMine(numberOfMines, width, height, board)

        outputView.startGame()
        println(
            board.joinToString(separator = "\n") {
                it.fold(StringBuilder()) { acc: StringBuilder, i: Int ->
                    acc.append(
                        if (i == -1) "*" else "C"
                    )
                }
            }
        )
    }

    // 랜덤 값으로 지뢰 주입
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

    private fun askHeight(): Int {
        return try {
            inputView.askHeight()
        } catch (e: Exception) {
            errorView.alert(e.message!!)
            askHeight()
        }
    }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    val outputView = OutputView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    MinesweeperApplication(inputView, outputView, errorView).execute()
}
