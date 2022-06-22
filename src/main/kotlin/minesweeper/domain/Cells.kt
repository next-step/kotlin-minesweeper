package minesweeper.domain

class Cells(val cells: List<Cell>) : List<Cell> by cells {

    fun click(position: Position) {
        require(position in cells.map { it.position }) { "유효하지 않은 좌표 입니다." }
        cells.first { it.position == position }.apply {
            this.openNearCells(cells)
        }
    }

    fun state(): BoardState {
        return when {
            isBomb() -> BoardState.BOMB
            avoidAllMine() -> BoardState.WIN
            else -> BoardState.CONTINUE
        }
    }

    private fun isBomb(): Boolean {
        return cells.any { it.cellState.isBomb() }
    }

    private fun avoidAllMine(): Boolean {
        return cells.filter { it.isNonMine() }.all { it.isOpen() }
    }

    fun groupByPositionX(): List<List<Cell>> {
        return cells.groupBy { it.position.y }.map { it.value }
    }

    companion object {
        fun of(positions: Positions, minePositions: Positions): Cells {
            return positions
                .onEach { it.setNearPositions(positions) }
                .map { Cell.of(it, minePositions) }
                .let { Cells(it) }
        }
    }
}
