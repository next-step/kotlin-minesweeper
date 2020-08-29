import model.Cell
import model.MINE_STRING
import model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    private val mineCell = Cell(true, Position(1, 1))
    private val notMineCell = Cell(false, Position(1, 1))
    private val targetCell1 = Cell(true, Position(1, 1))
    private val targetCell2 = Cell(false, Position(2, 1))

    @Test
    fun `Cell match 테스트`() {
        assertThat(mineCell.match(targetCell1)).isEqualTo(true)
    }

    @Test
    fun `Cell match 테스트2`() {
        assertThat(mineCell.match(targetCell2)).isEqualTo(false)
    }

    @Test
    fun `Mine 출력 테스트`() {
        assertThat(mineCell.toString()).isEqualTo(MINE_STRING)
    }

    @Test
    fun `NotMine 출력 테스트`() {
        assertThat(notMineCell.toString()).isEqualTo(notMineCell.aroundMineCount.toString())
    }

    @Test
    fun `Cell addCount 테스트`() {
        mineCell.addCount()
        assertThat(mineCell.aroundMineCount).isEqualTo(1)
    }
}
