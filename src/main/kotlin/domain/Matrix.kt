package domain

import domain.geometric.Dimension
import domain.geometric.Location
import domain.geometric.LocationValue

class Matrix(
    private val dimension: Dimension
) {
    private val cellsByLocation: MutableMap<Location, Cell>
    val cells
        get() = cellsByLocation.values

    init {
        val cells = CellFiller.fill(dimension)
        cellsByLocation = cells.associateBy { it.location }
            .toMutableMap()
    }

    private object CellFiller {
        fun fill(dimension: Dimension): List<Cell> {
            val rows = (0 until dimension.height).toList()
            val columns = (0 until dimension.width).toList()
            return rows.flatMap { row ->
                columns.map { col ->
                    Location(LocationValue(row), LocationValue(col))
                }
            }.map {
                Cell.safe(it)
            }
        }
    }
}
