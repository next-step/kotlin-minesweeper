package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.LandmineCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberCell

class GameBoard private constructor(
    val area: Area,
    val cells: Cells,
) {
    init {
        require(cells.chunked(area.width).all { it.size == area.width }) {
            "게임판의 모든 행은 열 길이가 동일해야 합니다"
        }
    }

    fun find(location: Location): Cell? = cells.find { it.location == location }

    fun openAll(): GameBoard {
        val allOpenedCells = cells.map { if (it is ClosedCell) it.open() else it }
        return GameBoard(area, Cells(allOpenedCells))
    }

    fun currentState(): GameBoardState {
        return GameBoardState(
            countOfTotalCells = cells.size,
            countOfClosedCells = cells.count { it is ClosedCell },
            countOfNumberCells = cells.count { it is NumberCell },
            countOfLandmineCells = cells.count { it is LandmineCell },
        )
    }

    companion object {
        fun of(
            height: Int,
            width: Int,
        ): GameBoard {
            val area = Area(height = height, width = width)
            val cells = createCellsByArea(area)
            return GameBoard(area, Cells(cells))
        }

        fun from(cells: List<Cell>): GameBoard {
            val area = calculateArea(cells)
            return GameBoard(area, Cells(cells))
        }

        private fun createCellsByArea(area: Area): List<Cell> {
            return (0 until area.height * area.width)
                .map {
                    ClosedCell(
                        Location(
                            row = (it / area.width) + 1,
                            column = (it % area.width) + 1,
                        ),
                    )
                }
        }

        private fun calculateArea(cells: List<Cell>): Area {
            val maxRow = cells.maxOfOrNull { it.location.row } ?: 0
            val maxColumn = cells.maxOfOrNull { it.location.column } ?: 0
            return Area(height = maxRow, width = maxColumn)
        }
    }
}
