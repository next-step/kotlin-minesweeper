package minesweeper

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
    val setting = SettingInputView.parse()

    val mines =
        Mines(
            generator = DefaultMineGenerator(),
            height = Height(setting.height),
            width = Width(setting.width),
            count = MineCount(setting.minesCount),
        )

    val board =
        Board(
            height = Height(setting.height),
            width = Width(setting.width),
            mines = mines,
        )

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
    val point = PointSelectView.parsePoint()

    if (board.isMine(row = point.first, col = point.second)) {
        ResultView.print(isWin = false)
        return false
    }

    board.openArea(point.first, point.second)

    if (!board.canOpen()) {
        ResultView.print(isWin = true)
        return false
    }

    return true
}
