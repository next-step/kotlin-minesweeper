package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.domain.point.Mines
import minesweeper.view.dto.BoardDto
import minesweeper.view.input.MinesWeeperSettingView
import minesweeper.view.output.MinesWeeperStartView

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

    MinesWeeperStartView.print(BoardDto.from(board))
}
