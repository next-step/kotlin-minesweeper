package minesweeper.converter

import minesweeper.config.MinesWeeperSetting
import minesweeper.domain.Board
import minesweeper.domain.point.Land
import minesweeper.view.model.BoardViewModel
import minesweeper.view.model.PointViewModel

object BoardConverter {
    fun toModel(
        setting: MinesWeeperSetting,
        board: Board,
        openedLands: MutableSet<Land>,
    ): BoardViewModel {
        val boardModel =
            List(setting.height) { row ->
                List(setting.width) { col ->
                    PointViewModel(aroundMineCount = board.countAroundMines(row, col), isOpened = openedLands.contains(Land(row, col)))
                }
            }

        return BoardViewModel(board = boardModel)
    }
}
