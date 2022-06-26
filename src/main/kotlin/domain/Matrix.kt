package domain

import domain.geometric.Dimension
import domain.geometric.Location

class Matrix(
    val dimension: Dimension,
    val cells: List<Cell>
) {
    private val cellsByLocation: Map<Location, Cell> = cells.associateBy { it.location }

    init {
        require(cells.size == dimension.area) {
            "전체 칸 수는 차원의 넓이와 같아야 합니다. 전체 칸 수 = ${cells.size}, 입력된 차원의 넓이 = ${dimension.area}"
        }

        require(dimension.isFilled(cells.map { it.location })) {
            "주어진 칸으로 행렬의 모든 칸을 채울 수 없습니다."
        }
    }

    fun countMinesAround(location: Location): Int {
        if (location !in this) return -1
        return Direction.values().count {
            val aroundLocation = it.getAroundLocationOrNull(location)
            if (aroundLocation == null || aroundLocation !in this) {
                return@count false
            }
            cellsByLocation[aroundLocation] is Mine
        }
    }

    private operator fun contains(location: Location): Boolean {
        return cellsByLocation[location] != null
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

            val locations = dimension.fill()

            val miningLocations = locationSelector.select(numberOfMines.value, locations)

            val cells = locations.map {
                Cell.of(it, miningLocations)
            }

            return Matrix(dimension, cells)
        }
    }
}

private enum class Direction(val rowPower: Int, val columnPower: Int) {
    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    RIGHT(0, 1),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(1, 0),
    BOTTOM_LEFT(1, -1),
    LEFT(0, -1),
    TOP_LEFT(-1, -1);

    fun getAroundLocationOrNull(location: Location): Location? {
        val row = location.row.value + rowPower
        val column = location.column.value + columnPower
        return Location.ofOrNull(row, column)
    }
}
