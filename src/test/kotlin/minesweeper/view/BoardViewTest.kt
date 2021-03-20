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

    @Test
    internal fun `폭탄을 출력한다`() {
        val out = StringWriter()
        BoardView(Cells(listOf(Cell(), Cell()), width = 2), out).show()
        assertThat(out.toString()).isEqualTo(
            """
            ◻️💣
            """.trimIndent()
        )
    }

    class BoardView(private val cells: Cells, private val out: PrintWriter) {
        constructor(cells: Cells, writer: Writer) : this(cells, PrintWriter(writer, true))

        fun show() {
            val height = cells.size / cells.width
            val result = (1..height).joinToString(System.lineSeparator()) {
                val startIndex = (it - 1) * cells.width
                val endIndex = startIndex + cells.width
                cells.subList(startIndex, endIndex).joinToString("") { "◻️" }
            }
            out.print(result)
        }
    }
}
