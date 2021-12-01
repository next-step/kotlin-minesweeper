package mine

import mine.cell.Cells
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellsTest {
    @Test
    fun `10 * 10의 보드 크기를 만들면 100개의 cell이 생성된다`() {
        val width = Width(10)
        val height = Height(10)

        val cells = Cells.createCells(width, height, 0)

        assertThat(cells.values.size).isEqualTo(100)
    }
}
