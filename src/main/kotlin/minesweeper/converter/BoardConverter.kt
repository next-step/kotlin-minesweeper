package minesweeper.converter

import minesweeper.common.ZERO
import minesweeper.config.MinesWeeperSetting
import minesweeper.domain.Board
import minesweeper.domain.point.Land
import minesweeper.domain.point.Mine
import minesweeper.view.model.BoardViewModel
import minesweeper.view.model.PointViewModel

object BoardConverter {
    fun toModel(
        setting: MinesWeeperSetting,
        board: Board,
    ): BoardViewModel {
        val boardModel = List(setting.height) { row -> convertRow(setting, board, row) }
        return BoardViewModel(board = boardModel)
    }

    private fun convertRow(
        setting: MinesWeeperSetting,
        board: Board,
        row: Int,
    ) = List(setting.width) { col ->
        when (val point = board.points[row].cols[col]) {
            is Mine -> PointViewModel(aroundMineCount = ZERO, isOpened = false)
            is Land -> PointViewModel(aroundMineCount = point.aroundMineCount, isOpened = point.isOpened())
        }
    }
}
