package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.point.Mines
import minesweeper.view.dto.BoardDto
import minesweeper.view.input.MinesWeeperSettingView
import minesweeper.view.output.MinesWeeperStartView

fun main() {
    val setting = MinesWeeperSettingView.parse()

    val mines =
        Mines(
            height = setting.height,
            width = setting.width,
            count = setting.minesCount,
            DefaultMineGenerator(),
        )

    val board =
        Board(
            height = setting.height,
            width = setting.width,
            mines = mines,
        )

    MinesWeeperStartView.print(BoardDto.from(board))
}
