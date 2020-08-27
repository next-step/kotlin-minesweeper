import model.cell.Cell
import model.cell.MineType
import model.cell.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    private val testCell = Cell(MineType.MINE, Position(1, 1))

    @Test
    fun `Cell match 테스트`() {
        assertThat(testCell.match(Position(1, 1))).isEqualTo(true)
    }

    @Test
    fun `Cell match 테스트2`() {
        assertThat(testCell.match(Position(1, 2))).isEqualTo(false)
    }

    @Test
    fun `Cell addCount 테스트`() {
        testCell.addCount()
        assertThat(testCell.aroundMineCount).isEqualTo(1)
    }
}
