package mine

import mine.cell.Cells
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellsTest {
    @Test
    fun `보드 크기의 cell 개수를 생성한다`() {
        val width = Width(10)
        val height = Height(10)

        val cells = Cells.createCells(width, height)

        assertThat(cells.cellList.size).isEqualTo(100)
    }
}
