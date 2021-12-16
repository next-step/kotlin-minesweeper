package mine

import mine.cell.Cells
import mine.cell.NoneCell
import mine.cell.Position
import mine.view.OutputView
import mine.view.OutputView.name
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
        val position = Position(1, 1)
        val cell = cells.findCell(position)
        val expectedAroundCells = listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )

        Assertions.assertThat(cell.aroundPosition()).isEqualTo(expectedAroundCells)
    }

    @Test
    fun `(3,4) position인 cell의 주변 셀 리스트를 구한다`() {
        val position = Position(3, 3)
        val cell = cells.findCell(position)
        val expectedAroundCells = listOf(
            Position(2, 2),
            Position(2, 3),
            Position(2, 4),
            Position(3, 2),
            Position(3, 4),
            Position(4, 2),
            Position(4, 3),
            Position(4, 4),
        )

        Assertions.assertThat(cell.aroundPosition()).isEqualTo(expectedAroundCells)
    }

    @Test
    fun `지뢰가 아닌 셀이 open되었다면 이름은 지뢰와 같은 이름으로 표시된다`() {
        val cell = NoneCell(Position(0, 0), 2)

        cell.isClicked = false

        Assertions.assertThat(cell.name()).isEqualTo(OutputView.CELL_NAME)
    }

    @Test
    fun `지뢰가 아닌 셀이 open되었다면 이름은 주위 지뢰 개수로 표시된다`() {
        val cell = NoneCell(Position(0, 0), 2)

        cell.open()

        Assertions.assertThat(cell.name()).isEqualTo("2")
    }

    @Test
    fun `셀의 주위 지뢰의 개수를 open할 수 있다`() {
        val cell = NoneCell(Position(0, 0), 0)

        cell.open()

        Assertions.assertThat(cell.isClicked).isEqualTo(true)
        Assertions.assertThat(cell.name()).isEqualTo("0")
    }

    @Test
    fun `Position(0,0)인 셀과 Position(1,1)이 같은 위치값을 가지고 있는지 비교할 수 있다`() {
        val cell1 = NoneCell(Position(0, 0), 0)

        val compare = cell1.isSamePosition(Position(1, 1))

        Assertions.assertThat(compare).isEqualTo(false)
    }

    @Test
    fun `Position(0,0)인 셀과 Position(0,0)이 같은 위치값을 가지고 있는지 비교할 수 있다`() {
        val cell1 = NoneCell(Position(0, 0), 0)

        val compare = cell1.isSamePosition(Position(0, 0))

        Assertions.assertThat(compare).isEqualTo(true)
    }
}
