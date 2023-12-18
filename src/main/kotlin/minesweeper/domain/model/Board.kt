package minesweeper.domain.model

import minesweeper.domain.model.cell.Cell
import minesweeper.domain.model.cell.CellState

class Board private constructor(
    private val cells: List<List<Cell>>
) : List<List<Cell>> by cells {

    init {
        val flatCells = cells.flatten()

        require(!flatCells.all { cell -> cell.isMine() }) {
            "보드의 모든 셀이 지뢰여서는 안된다."
        }
    }

    companion object {
        fun create(width: Width, height: Height, mineCount: MineCount): Board {
            val mineCellStates = List(mineCount.toInt()) { CellState.MINE }
            val noneCellStates = List((width.toInt() * height.toInt()) - mineCount.toInt()) { CellState.NONE }
            val cells = (mineCellStates + noneCellStates)
                .shuffled()
                .chunked(width.toInt())
                .toCellList()

            return Board(cells)
        }
    }
}

private fun List<List<CellState>>.toCellList(): List<List<Cell>> {
    return this.mapIndexed { rowIndex, rowCells ->
        rowCells.mapIndexed { columnIndex, cellStatus ->
            var countOfMinesNearby = 0
            if (rowIndex != 0 && columnIndex != 0 && this[rowIndex - 1][columnIndex - 1].isMine()) countOfMinesNearby++
            if (rowIndex != 0 && this[rowIndex - 1][columnIndex].isMine()) countOfMinesNearby++
            if (rowIndex != 0 && columnIndex != lastIndex && this[rowIndex - 1][columnIndex + 1].isMine()) countOfMinesNearby++
            if (columnIndex != 0 && this[rowIndex][columnIndex - 1].isMine()) countOfMinesNearby++
            if (columnIndex != lastIndex && this[rowIndex][columnIndex + 1].isMine()) countOfMinesNearby++
            if (rowIndex != lastIndex && columnIndex != 0 && this[rowIndex + 1][columnIndex - 1].isMine()) countOfMinesNearby++
            if (rowIndex != lastIndex && this[rowIndex + 1][columnIndex].isMine()) countOfMinesNearby++
            if (rowIndex != lastIndex && columnIndex != lastIndex && this[rowIndex + 1][columnIndex + 1].isMine()) countOfMinesNearby++
            Cell.create(cellStatus, countOfMinesNearby)
        }
    }
}
