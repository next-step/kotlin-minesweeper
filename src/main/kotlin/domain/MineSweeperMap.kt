package domain

class MineSweeperMap(val property: Property, minePositionGenerator: MinePositionGenerator) {
    val value: Array<Array<Cell>> = init(minePositionGenerator)

    fun getCellByPosition(position: Position): Cell {
        val rowIndex = position.row.value - INDEX_VALUE_FOR_CONVENIENCE
        val columnIndex = position.column.value - INDEX_VALUE_FOR_CONVENIENCE
        return value[rowIndex][columnIndex]
    }

    fun getCellsByPositions(positions: Positions): Cells {
        return positions.value.map { getCellByPosition(it) }.toCells()
    }

    fun existsOpenMine(): Boolean {
        return value.any { row -> row.any { it.property.isOpen() && it.property.isMine() } }
    }

    fun getCloseCellCount(): Int {
        return value.sumOf { row -> row.count { !it.property.isOpen() } }
    }

    private fun init(minePositionGenerator: MinePositionGenerator): Array<Array<Cell>> {
        val minePositions = minePositionGenerator.generate()
        return with(property) {
            (MAP_START_INDEX_VALUE..height.value).map { row ->
                (MAP_START_INDEX_VALUE..width.value).map { column ->
                    val position = Position.of(row, column)
                    val validPositions = position.getValidAdjacentPositions(height, width)
                    val cellProperty = CellPropertyFactory.create(minePositions.contains(position)) {
                        validPositions.getAroundMineCount(minePositions)
                    }
                    Cell(position, cellProperty)
                }.toTypedArray()
            }.toTypedArray()
        }
    }

    data class Property(
        val height: PositiveNumber,
        val width: PositiveNumber,
    )

    companion object {
        const val MAP_START_INDEX_VALUE = 1
        const val INDEX_VALUE_FOR_CONVENIENCE = 1
    }
}
