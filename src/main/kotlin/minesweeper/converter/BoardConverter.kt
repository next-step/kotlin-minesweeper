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
    ): BoardViewModel {
        val pointViewModels = List(setting.size.height.value) { row -> makeRow(setting, row, board) }
        return BoardViewModel(pointViewModels)
    }

    private fun makeRow(
        setting: MinesWeeperSetting,
        row: Int,
        board: Board,
    ) = List(setting.size.width.value) { col ->
        val point = Point(row, col)

        board.lands
            .find(point)
            ?.let { land -> PointViewModel(aroundMineCount = land.aroundMineCount, isOpened = land.isOpened()) }
            ?: PointViewModel(aroundMineCount = ZERO, isOpened = false)
    }
}
