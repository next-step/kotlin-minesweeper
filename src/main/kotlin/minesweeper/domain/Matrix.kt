package minesweeper.domain

import minesweeper.domain.field.Field
import minesweeper.domain.field.Mine
import minesweeper.domain.field.Safe

class Matrix(val rows: List<List<Field>>) {
    val width = rows[0].size
    val height = rows.size
    val coordinates = rows
        .indices
        .flatMap { rows: Int -> rowsCoordinates(rows) }

    fun open(coordinate: Coordinate) {
        val field = rows[coordinate.rows][coordinate.cols]
        field.open()

        if (noMinesAround(coordinate)) this.openAround(coordinate)

        canOpenFields(coordinate)
            .filter { noMinesAround(it) }
            .forEach { this.openAround(it) }
    }

    fun landMine(coordinate: Coordinate) {
        rows[coordinate.rows][coordinate.cols].landMine()
    }

    fun aroundMineCount(coordinate: Coordinate): Int {
        return CoordinateDirection.around(coordinate)
            .filter { it.rows in 0 until width && it.cols in 0 until height }
            .count { rows[it.rows][it.cols].land is Mine }
    }

    private fun openAround(coordinate: Coordinate) {
        rows[coordinate.rows][coordinate.cols].open()

        canOpenFields(coordinate)
            .filter { !noMinesAround(it) }
            .forEach { rows[it.rows][it.cols].open() }

        canOpenFields(coordinate)
            .filter { noMinesAround(it) }
            .forEach { openAround(it) }
    }

    private fun canOpenFields(coordinate: Coordinate) =
        CoordinateDirection.around(coordinate)
            .asSequence()
            .filter { it.rows in 0 until height && it.cols in 0 until width }
            .filter { rows[it.rows][it.cols].isClosed() }
            .toList()

    private fun noMinesAround(coordinate: Coordinate) =
        rows[coordinate.rows][coordinate.cols].land is Safe && rows[coordinate.rows][coordinate.cols].aroundMineCount() == 0

    private fun rowsCoordinates(rowIndex: Int) =
        (0 until rows[rowIndex].size).map { cols: Int -> Coordinate(rowIndex, cols) }

    companion object {
        fun of(width: Int, height: Int) = Matrix(createRows(height, width))

        private fun createRows(height: Int, width: Int): List<List<Field>> =
            List(height) { createCols(width) }

        private fun createCols(width: Int): List<Field> = List(width) { Field() }
    }
}
