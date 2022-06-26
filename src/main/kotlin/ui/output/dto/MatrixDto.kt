package ui.output.dto

import domain.Cell
import domain.Matrix

data class MatrixDto(
    val lines: List<Line>,
) {
    companion object {
        fun from(matrix: Matrix): MatrixDto {
            val lines = matrix.cells.chunked(
                matrix.dimension.width
            ).map {
                Line.of(matrix, it)
            }
            return MatrixDto(lines)
        }
    }
}

data class Line(
    val displayedCells: List<DisplayedCell>
) {
    companion object {
        fun of(matrix: Matrix, cells: List<Cell>): Line {
            val displayedCells = cells.map {
                if (it is Cell.Mine) return@map DisplayedCell.MINE_DISPLAY_VIEW
                DisplayedCell.from(matrix.countMinesAround(it.location))
            }
            return Line(displayedCells)
        }
    }
}

data class DisplayedCell(
    val displayView: String
) {
    companion object {
        val MINE_DISPLAY_VIEW = DisplayedCell("*")
        fun from(count: Int): DisplayedCell = DisplayedCell("$count")
    }
}
