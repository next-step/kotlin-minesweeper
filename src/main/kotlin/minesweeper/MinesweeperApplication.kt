package minesweeper

import global.strategy.ConsoleInputStrategy
import global.strategy.ConsoleOutputStrategy
import minesweeper.domain.Height
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

        val board = (0 until height.height)
            .map { (0 until width).map { 99 }.toMutableList() }
            .toMutableList()

        setMine(numberOfMines, width, height.height, board)

        for ((x, mutableList) in board.withIndex()) {
            for ((y, element) in mutableList.withIndex()) {
                setNumber(x, y, width, height.height, board)
            }
        }

        println(
            board.joinToString(separator = "\n") {
                it.fold(StringBuilder()) { acc: StringBuilder, i: Int -> acc.append("$i\t") }
            }
        )

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

    fun setNumber(row: Int, column: Int, width: Int, height: Int, board: MutableList<MutableList<Int>>) {
        if (board[row][column] == 99) {
            board[row][column] = getMineNumber(row, column, width, height, board);
        }
    }

    private fun getMineNumber(
        row: Int,
        column: Int,
        width: Int,
        height: Int,
        board: MutableList<MutableList<Int>>
    ): Int {
        var mineCnt = 0;
        if (isExistMine(row - 1, column - 1, width, height, board)) mineCnt++;
        if (isExistMine(row - 1, column, width, height, board)) mineCnt++;
        if (isExistMine(row - 1, column + 1, width, height, board)) mineCnt++;
        if (isExistMine(row, column - 1, width, height, board)) mineCnt++;
        if (isExistMine(row, column + 1, width, height, board)) mineCnt++;
        if (isExistMine(row + 1, column - 1, width, height, board)) mineCnt++;
        if (isExistMine(row + 1, column, width, height, board)) mineCnt++;
        if (isExistMine(row + 1, column + 1, width, height, board)) mineCnt++;
        return mineCnt;
    }

    private fun isExistMine(
        row: Int,
        column: Int,
        width: Int,
        height: Int,
        board: MutableList<MutableList<Int>>
    ): Boolean {
        if (row < 0 || row >= width || column < 0 || column >= height) {
            return false;
        }

        return board[row][column] == -1
    }

    private fun askHeight(): Height {
        return try {
            Height(inputView.askHeight())
        } catch (e: Exception) {
            errorView.alert(e.message.toString())
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
