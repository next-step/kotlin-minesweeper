package mine

import mine.cell.Cells
import mine.cell.Position
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CellTest {
    private lateinit var cells: Cells
    private val width = Width(10)
    private val height = Height(10)

    @BeforeEach
    fun setup() {
        cells = Cells.createCells(width, height, 4)
    }

    @Test
    fun `(1,1) position인 cell의 주변 셀 리스트를 구한다`() {
        val position = Position(1,1)
        val cell = cells.findCell(position)
        val expectedAroundCells = listOf(
            Position(1,2),
            Position(2,1),
            Position(2,2)
        )

        Assertions.assertThat(cell.aroundPosition()).isEqualTo(expectedAroundCells)
    }

    @Test
    fun `(3,4) position인 cell의 주변 셀 리스트를 구한다`() {
        val position = Position(3,3)
        val cell = cells.findCell(position)
        val expectedAroundCells = listOf(
            Position(2,2),
            Position(2,3),
            Position(2,4),
            Position(3,2),
            Position(3,4),
            Position(4,2),
            Position(4,3),
            Position(4,4),
        )

        Assertions.assertThat(cell.aroundPosition()).isEqualTo(expectedAroundCells)
    }
}
