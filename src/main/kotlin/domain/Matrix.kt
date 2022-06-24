package domain

import domain.geometric.Dimension
import domain.geometric.Location
import domain.geometric.LocationValue

class Matrix(
    val dimension: Dimension,
    val cells: List<Cell>
) {

    init {
        require(cells.size == dimension.area) {
            "전체 칸 수는 차원의 넓이와 같아야 합니다. 전체 칸 수 = ${cells.size}, 입력된 차원의 넓이 = ${dimension.area}"
        }
    }

    private object MatrixFiller {
        fun fill(dimension: Dimension): List<Location> {
            val rows = (0 until dimension.height).toList()
            val columns = (0 until dimension.width).toList()
            return rows.flatMap { row ->
                mapToLocation(columns, row)
            }
        }

        private fun mapToLocation(
            columns: List<Int>,
            row: Int
        ): List<Location> = columns.map { col ->
            Location(LocationValue(row), LocationValue(col))
        }
    }

    companion object {
        fun of(
            dimension: Dimension,
            numberOfMines: NumberOfMines,
            locationSelector: LocationSelector = RandomLocationSelector
        ): Matrix {
            require(numberOfMines.value <= dimension.area) {
                "지뢰 개수는 전체 칸 수보다 많을 수 없습니다. 전체 칸 수 = ${dimension.area}, 입력된 지뢰 개수 = ${numberOfMines.value}"
            }
            val locations = MatrixFiller.fill(dimension)
            val miningLocations = locationSelector.select(numberOfMines.value, locations)
            val cells = locations.map {
                mapToCell(it, miningLocations)
            }

            return Matrix(dimension, cells)
        }

        private fun mapToCell(location: Location, miningLocations: List<Location>): Cell {
            return when (location) {
                in miningLocations -> Cell.mine(location)
                else -> Cell.safe(location)
            }
        }
    }
}
