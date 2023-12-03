package minesweeper

import minesweeper.domain.MineSweeper
import minesweeper.domain.MineSweeperWidth
import minesweeper.domain.board.size.MineSweeperBoardSize
import minesweeper.domain.mine.Mine
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

fun main() {
    OutputView.enterHeight()
    val height = InputView.inputMineSweeperGameValue()
    OutputView.enterWidth()
    val width = InputView.inputMineSweeperGameValue()
    OutputView.enterMineCount()
    val mineCount = InputView.inputMineSweeperGameValue()
    val mine = Mine(mineCount)
    val mineSweeperBoardSize = MineSweeperBoardSize(width = width, height = height)

    val mineSweeper = MineSweeper(board = mineSweeperBoardSize)
    OutputView.startMineSweeper()

    val notContainedMineCount = mineSweeperBoardSize.getBoardFullSize() - mine.mineCount
    val notContainedMineList = MineSweeperWidth.newInstance(widthSize = notContainedMineCount)
    val mineList = MineSweeperWidth.newInstance(widthSize = mine.mineCount, mineSweeperShape = mine.mineShape)

    val mineSweeperList = (notContainedMineList + mineList).shuffled()

    val mineSweeperBoard = mineSweeper.createBoard(mineSweeperList)
    OutputView.mineSweeperInitializePrinter(mineSweeperBoard)
}
