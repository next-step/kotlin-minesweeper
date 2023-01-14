package minesweeper.domain

import java.util.LinkedList
import java.util.Queue

class Map(val cells: List<Cell>, val status: Status = Status.PROCESSING) {

    fun isProcessing(): Boolean = status == Status.PROCESSING

    fun open(cellPosition: CellPosition): Map {
        val cell = cells.find { cell -> cell.cellPosition == cellPosition }
            ?: throw IllegalArgumentException("좌표에 벗어난 값입니다.")

        if (cell.isMine()) {
            return Map(cells, status.next(EVENT.LOSE))
        }

        val openedCells = getNearBlankCells(cell)
            .plus(cell as Cell.Blank)
            .map { blankCell: Cell.Blank -> blankCell.open() }

        val newCells = replaceCell(openedCells)

        val nextStatus = if (isAllOpen(newCells)) status.next(EVENT.WIN) else status

        return Map(newCells, nextStatus)
    }

    private fun replaceCell(openedCells: List<Cell.Blank>): List<Cell> =
        cells.map { cell ->
            val findCell = openedCells.find { openCell -> openCell.cellPosition == cell.cellPosition }
            findCell ?: cell
        }

    private fun isAllOpen(cells: List<Cell>): Boolean =
        cells.filterIsInstance<Cell.Blank>()
            .all { cell -> cell.isOpen }

    private fun getNearBlankCells(targetCell: Cell): List<Cell.Blank> {
        val nearCellPositions = targetCell.cellPosition.getNear()

        return cells.filter { cell -> nearCellPositions.contains(cell.cellPosition) }
            .filterIsInstance<Cell.Blank>()
    }

    companion object {
        fun create(meta: MapMeta): Map {
            val totalCellCount = meta.height * meta.width
            val blankCount = totalCellCount - meta.mineCount

            val cellPositions = initPosition(meta.width, meta.height).shuffled()
            val cells = initCell(
                cellPositions = cellPositions.toCollection(LinkedList()),
                blankCellCount = blankCount,
                mineCellCount = meta.mineCount
            )

            val sortedCells = initMineCount(cells).sortedBy { cell -> cell.cellPosition }

            return Map(sortedCells)
        }

        private fun initPosition(rowSize: Int, columSize: Int): List<CellPosition> =
            (0 until rowSize).flatMap { row ->
                List(columSize) { colum ->
                    val xPosition = Position(row)
                    val yPosition = Position(colum)
                    CellPosition(xPosition, yPosition)
                }
            }

        private fun initCell(cellPositions: Queue<CellPosition>, blankCellCount: Int, mineCellCount: Int): List<Cell> {
            val blankCells = List(blankCellCount) { Cell.Blank(cellPositions.poll()) }
            val mineCells = List(mineCellCount) { Cell.Mine(cellPositions.poll()) }

            return blankCells + mineCells
        }

        private fun initMineCount(cells: List<Cell>): List<Cell> =
            cells.map { cell ->
                if (cell is Cell.Mine) return@map cell

                val mineCount = countNearMine(
                    cells = cells,
                    cellPosition = cell.cellPosition
                )

                Cell.Blank(cell.cellPosition, mineCount)
            }

        private fun countNearMine(cells: List<Cell>, cellPosition: CellPosition): Int {
            val nearCells: List<CellPosition> = cellPosition.getNear()

            return cells.count { cell -> cell.mineCountIn(nearCells) }
        }
    }
}
