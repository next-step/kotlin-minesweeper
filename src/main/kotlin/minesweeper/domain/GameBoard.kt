package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location
import minesweeper.domain.cell.NumberOfAdjacentMines
import minesweeper.domain.strategy.AdjacentLocationDirection

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

    fun open(location: Location): GameBoard {
        val foundedCell = this.find(location) ?: throw IllegalArgumentException("해당 위치를 가진 셀이 존재하지 않습니다")
        if (foundedCell !is ClosedCell) {
            return this
        }
        if (foundedCell.hasLandmine) {
            val cellsWithOpenCells = cells.map { if (it == foundedCell && it is ClosedCell) it.open() else it }
            return GameBoard(area, Cells(cellsWithOpenCells))
        }
        if (foundedCell.numberOfAdjacentLandmines > NumberOfAdjacentMines.ZERO) {
            val cellsWithOpenCells = cells.map { if (it == foundedCell && it is ClosedCell) it.open() else it }
            return GameBoard(area, Cells(cellsWithOpenCells))
        }

        val candidates = mutableListOf<Cell>()
        val queue = ArrayDeque<Cell>()
        val visitedCells = mutableListOf<Cell>()

        visitedCells.add(foundedCell)
        queue.add(foundedCell)
        candidates.add(foundedCell)

        while (queue.isNotEmpty()) {
            val nextCell = queue.removeFirst()
            val allAdjacentLocations =
                AdjacentLocationDirection.allAdjacentLocations(nextCell.location)
            allAdjacentLocations.forEach { adjacentLocation ->
                val adjacentCell = find(adjacentLocation)
                adjacentCell?.let { cell ->
                    if (cell is ClosedCell && cell !in visitedCells && !cell.hasLandmine) {
                        visitedCells.add(cell)
                        candidates.add(cell)

                        if (cell.numberOfAdjacentLandmines == NumberOfAdjacentMines.ZERO) {
                            queue.add(cell)
                        }
                    }
                }
            }
        }

        val cellsWithOpenCells = cells.map { if (candidates.contains(it) && it is ClosedCell) it.open() else it }
        return GameBoard(area, Cells(cellsWithOpenCells))
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
