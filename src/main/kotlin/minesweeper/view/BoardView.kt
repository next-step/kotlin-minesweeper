package minesweeper.view

import minesweeper.domain.Cells
import java.io.PrintWriter
import java.io.Writer
import java.lang.System.lineSeparator

class BoardView(private val cells: Cells, private val out: PrintWriter = PrintWriter(System.out)) {
    constructor(cells: Cells, writer: Writer) : this(cells, PrintWriter(writer, true))

    fun show() {
        out.print(rows().joinToString(lineSeparator(), transform = this::rowView))
        out.flush()
    }

    private fun rowView(it: Int): String = cells.subList(startIndex(it), endIndex(it))
        .map(::CellView)
        .joinToString(EMPTY)

    private fun rows() = (1..cells.height)

    private fun endIndex(row: Int) = startIndex(row) + cells.width

    private fun startIndex(row: Int) = (row - 1) * cells.width

    companion object {
        const val EMPTY = ""
    }
}
