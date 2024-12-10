package minesweeper

import minesweeper.converter.BoardConverter
import minesweeper.domain.Board
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.domain.point.Mines
import minesweeper.view.input.MinesWeeperSettingView
import minesweeper.view.input.PointSelectView
import minesweeper.view.output.BoardView
import minesweeper.view.output.MinesWeeperStartView
import minesweeper.view.output.ResultView

fun main() {
    val setting = MinesWeeperSettingView.parse()

    val mines =
        Mines(
            height = Height(setting.height),
            width = Width(setting.width),
            count = MineCount(setting.minesCount),
            DefaultMineGenerator(),
        )

    val board =
        Board(
            height = Height(setting.height),
            width = Width(setting.width),
            mines = mines,
        )

    MinesWeeperStartView.print()

    if (!board.existUnopenedLand()) {
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

    board.open(point.first, point.second)

    if (!board.existUnopenedLand()) {
        ResultView.print(isWin = true)
        return false
    }

    return true
}
