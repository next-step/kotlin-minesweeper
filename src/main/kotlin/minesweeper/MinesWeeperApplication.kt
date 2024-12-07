package minesweeper

import minesweeper.converter.BoardConverter
import minesweeper.domain.Board
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.domain.point.Mines
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

    val model = BoardConverter.toModel(setting, board)
    MinesWeeperStartView.print(model)
}
