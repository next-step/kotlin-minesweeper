package minesweeper.converter

import minesweeper.common.ZERO
import minesweeper.config.MinesWeeperSetting
import minesweeper.domain.Board
import minesweeper.domain.Point
import minesweeper.view.model.BoardViewModel
import minesweeper.view.model.PointViewModel

object BoardConverter {
    fun toModel(
        setting: MinesWeeperSetting,
        board: Board,
    ): BoardViewModel =
        BoardViewModel(
            List(setting.height) { row ->
                makeRow(setting, row, board)
            },
        )

    private fun makeRow(
        setting: MinesWeeperSetting,
        row: Int,
        board: Board,
    ) = List(setting.width) { col ->
        val point = Point(row, col)
        board.lands.find(point)?.let { land ->
            PointViewModel(aroundMineCount = land.aroundMineCount, isOpened = land.isOpened())
        } ?: PointViewModel(aroundMineCount = ZERO, isOpened = false)
    }
}
