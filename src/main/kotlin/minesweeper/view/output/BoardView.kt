package minesweeper.view.output

import minesweeper.common.PointViewModels
import minesweeper.view.model.BoardViewModel

object BoardView {
    private const val POINT_SIGNATURE = "C"

    fun print(model: BoardViewModel) {
        model.board.forEach { rows -> println(makeRow(rows)) }
        println()
    }

    private fun makeRow(rows: PointViewModels) =
        rows.joinToString(" ") { point ->
            if (point.isOpened) point.aroundMineCount.toString() else POINT_SIGNATURE
        }
}
