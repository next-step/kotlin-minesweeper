package minesweeper.view

import minesweeper.domain.MotherCellTest.Cell
import minesweeper.domain.MotherCellTest.Cells
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer

class BoardViewTest {
    @Test
    internal fun `높이 너비로 화면에 출력한다`() {
        val out = StringWriter()
        BoardView(Cells(listOf(Cell(), Cell(), Cell(), Cell()), width = 2), out).show()
        assertThat(out.toString()).isEqualTo(
            """
            ◻️◻️
            ◻️◻️
            """.trimIndent()
        )
    }

    class BoardView(cells: Cells, private val out: PrintWriter) {
        constructor(cells: Cells, writer: Writer) : this(cells, PrintWriter(writer, true))

        fun show() {
            out.println("◻️◻️")
            out.print("◻️◻️")
        }
    }
}
