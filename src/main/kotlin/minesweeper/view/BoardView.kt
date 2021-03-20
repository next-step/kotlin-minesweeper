package minesweeper.view

import minesweeper.domain.Cells
import java.io.PrintWriter
import java.io.Writer

class BoardView(private val cells: Cells, private val out: PrintWriter) {
    constructor(cells: Cells, writer: Writer) : this(cells, PrintWriter(writer, true))

    fun show() {
        val height = cells.size / cells.width
        val result = (1..height).joinToString(System.lineSeparator()) {
            val startIndex = (it - 1) * cells.width
            val endIndex = startIndex + cells.width
            cells.subList(startIndex, endIndex).map(::CellView).joinToString("")
        }
        out.print(result)
    }
}
