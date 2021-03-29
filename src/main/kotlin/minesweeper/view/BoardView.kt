package minesweeper.view

import minesweeper.domain.Board
import java.io.PrintWriter
import java.io.Writer
import java.lang.System.lineSeparator

class BoardView(private val board: Board, private val out: PrintWriter = PrintWriter(System.out)) {
    constructor(board: Board, writer: Writer) : this(board, PrintWriter(writer, true))

    fun show() {
        out.print(rows() mapBy this::rowView)
        out.flush()
    }

    private fun rowView(it: Int): String = board.subList(startIndex(it), endIndex(it))
        .map(::CellView)
        .joinToString(EMPTY)

    private fun rows() = (1..board.height)

    private fun endIndex(row: Int) = startIndex(row) + board.width

    private fun startIndex(row: Int) = (row - 1) * board.width

    companion object {
        const val EMPTY = " "
    }
}

private infix fun IntRange.mapBy(transform: (it: Int) -> String): String {
    return joinToString(lineSeparator(), transform = transform)
}
