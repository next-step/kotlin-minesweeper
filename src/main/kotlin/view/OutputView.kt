package view

import domain.Cell
import domain.CellType

class OutputView {
    fun outputGameStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun outputCells(cells: List<Cell>) {
        val heightDividedCells = cells.groupBy { it.position.y }
        heightDividedCells.keys.sorted().forEach { height ->
            heightDividedCells.getValue(height).joinToString(separator = SPACING) { it.toView() }.also { println(it) }
        }
    }

    private fun Cell.toView(): String = when (type) {
        CellType.GROUND -> GROUND_VIEW
        CellType.MINE -> MINE_VIEW
    }

    companion object {
        private const val GROUND_VIEW = "C"
        private const val MINE_VIEW = "*"
        private const val SPACING = " "
    }
}
