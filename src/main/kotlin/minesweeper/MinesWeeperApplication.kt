package minesweeper

import minesweeper.converter.BoardConverter
import minesweeper.domain.Board
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.domain.point.Land
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

    val openedLands = mutableSetOf<Land>()

    if (!board.existUnopenedLand(openedLands)) {
        ResultView.print(isWin = true)
        return
    }

    while (processOpen(board, openedLands)) {
        BoardView.print(BoardConverter.toModel(setting, board, openedLands))
    }
}

private fun processOpen(
    board: Board,
    openedLands: MutableSet<Land>,
): Boolean {
    val point = PointSelectView.parsePoint()

    if (board.isMine(row = point.first, col = point.second)) {
        ResultView.print(isWin = false)
        return false
    }

    openedLands.addAll(board.open(point))

    if (!board.existUnopenedLand(openedLands)) {
        ResultView.print(isWin = true)
        return false
    }

    return true
}
