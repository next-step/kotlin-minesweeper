package minesweeper

import minesweeper.config.BoardSize
import minesweeper.config.MinesWeeperSetting
import minesweeper.converter.BoardConverter
import minesweeper.domain.Board
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Mines
import minesweeper.domain.Width
import minesweeper.view.input.PointSelectView
import minesweeper.view.input.SettingInputView
import minesweeper.view.output.BoardView
import minesweeper.view.output.ResultView
import minesweeper.view.output.StartView

fun main() {
    val (width, height, minesCount) = SettingInputView.parse()
    val size = BoardSize(height = Height(height), width = Width(width))
    val setting = MinesWeeperSetting(size = size, minesCount = MineCount(minesCount))
    val mines = Mines(generator = DefaultMineGenerator(), setting = setting)
    val board = Board(size = size, mines = mines)

    StartView.print()

    if (!board.canOpen()) {
        ResultView.print(isWin = true)
        return
    }

    while (processOpen(board)) {
        BoardView.print(BoardConverter.toModel(setting, board))
    }
}

private fun processOpen(board: Board): Boolean {
    val (row, col) = PointSelectView.parsePoint()

    if (board.isMine(row = row, col = col)) {
        ResultView.print(isWin = false)
        return false
    }

    board.openArea(row, col)

    if (!board.canOpen()) {
        ResultView.print(isWin = true)
        return false
    }

    return true
}
