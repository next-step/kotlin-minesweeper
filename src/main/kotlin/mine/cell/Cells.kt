package mine.cell

import mine.GameStatus
import mine.Height
import mine.Width

/**
 * 셀 전체 관리
 * */
class Cells(val values: List<Cell>) {
    private fun isMineCell(position: Position): Boolean = findCell(position).isMineCell()

    private fun clickCell(position: Position) =
        findCell(position)
            .aroundAllPosition()
            .asSequence()
            .filterNot { isMineCell(it) }
            .map { it.aroundAllPosition() }
            .flatten()
            .forEach {
                open(it)
            }

    private fun open(position: Position) = findCell(position).open()

    private fun isLastCell(): Boolean = values.count { !it.isClicked } == 0

    fun row(): Int = values.maxOf { it.position.x }
    fun column(): Int = values.maxOf { it.position.y }

    fun rowOfCells(row: Int): Cells =
        values.filter { it.position.x == row }.sortedBy { it.position.y }.let(::Cells)

    fun findCell(position: Position): Cell {
        require(position.x <= row() && position.y <= column())
        return values.first { it.position == position }
    }

    fun clickedCell(position: Position): GameStatus {
        if (isMineCell(position)) return GameStatus.GAMEOVER
        clickCell(position)
        if (isLastCell()) return GameStatus.WIN
        return GameStatus.CONTINUE
    }

    companion object {
        private fun List<Cell>.isMineCell(position: Position): Boolean = this.any {
            it.isSamePosition(position)
        }

        private fun Position.isAroundCell(mineList: List<Cell>): Int =
            this.aroundPosition()
                .map { mineList.isMineCell(it) }
                .count { it }

        private fun createList(width: Width, height: Height): List<Position> {
            val size = width.value * height.value
            return List(size) { index: Int ->
                val x = (index / width.value)
                val y = (index % height.value)
                Position(x, y)
            }.shuffled()
        }

        fun createCells(width: Width, height: Height, mineCount: Int): Cells {
            val positions = createList(width, height)
            val mines = positions.take(mineCount).map { MineCell(it) }
            val nones = positions
                .filterIndexed { index, _ -> index >= mineCount }
                .map { NoneCell(it, it.isAroundCell(mines)) }
            return Cells(mines + nones)
        }
    }
}
