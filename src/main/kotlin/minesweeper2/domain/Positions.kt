package minesweeper2.domain

import minesweeper2.model.PositionLocation
import kotlin.random.Random

class Positions(
    val positions: Array<Array<Position>>,
) {
    val rows: Int = positions.size
    val cols: Int = positions[0].size

    fun position(rows: Int, cols: Int): Position {
        return positions[rows][cols]
    }

    fun updatePositionValue(rows: Int, cols: Int, value: Int) {
        positions[rows][cols] = Position(value)
    }

    fun mineCount(): Int {
        return positions
            .asSequence()
            .flatMap { it.asSequence() }
            .filter { it.value == -1 }
            .count()
    }

    fun open(positionLocation: PositionLocation) {
        val position = positions[positionLocation.row][positionLocation.col]
        position.isOpened = true
        position.isVisited = true

        val findPositions = FindPosition.positionLocations(positionLocation, rows, cols)

        val isMineNotExists = findPositions.none { isMinePosition(it.row, it.col) }

        if (isMineNotExists) {
            openPositions(findPositions)
            addFindOpenPositions(findPositions).forEach { open(it) }
        }
    }

    fun notOpenPositionCount(): Int {
        return positions
            .asSequence()
            .flatMap { it.asSequence() }
            .filter { !it.isOpened }
            .count()
    }

    fun isMinePosition(row: Int, col: Int): Boolean {
        return position(row, col).value == -1
    }

    private fun openPositions(findPositions: List<PositionLocation>) {
        findPositions
            .forEach { (row, col) -> positions[row][col].isOpened = true }
    }

    private fun addFindOpenPositions(findPositions: List<PositionLocation>): List<PositionLocation> {
        return findPositions
            .filter { isNotVisited(it.row, it.col) }
    }

    private fun isNotVisited(row: Int, col: Int): Boolean {
        return !position(row, col).isVisited
    }

    companion object {
        fun from(rows: Rows, cols: Cols, mine: Mine): Positions {
            val rowsValue = rows.value
            val colsValue = cols.value
            val positionArray = Array(rowsValue) {
                Array(colsValue) {
                    Position()
                }
            }

            repeat(mine.value) {
                addMine(rowsValue, colsValue, positionArray)
            }

            return Positions(positionArray)
        }

        private fun addMine(
            rowsValue: Int,
            colsValue: Int,
            positionArray: Array<Array<Position>>,
        ) {
            var randomRowValue = Random.nextInt(rowsValue)
            var randomColValue = Random.nextInt(colsValue)

            while (positionArray[randomRowValue][randomColValue].value == -1) {
                randomRowValue = Random.nextInt(rowsValue)
                randomColValue = Random.nextInt(colsValue)
            }

            positionArray[randomRowValue][randomColValue] = Position(-1)
        }
    }
}
