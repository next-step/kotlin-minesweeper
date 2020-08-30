import model.Cell
import model.MINE_STRING
import model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    private val mineCell = Cell(true, Position(1, 1))

    @Test
    fun `Cell match 테스트`() {
        assertThat(mineCell.match(Cell(true, Position(1, 1)))).isTrue()
    }

    @Test
    fun `Cell match 테스트2`() {
        assertThat(mineCell.match(Cell(false, Position(2, 1)))).isFalse()
    }

    @Test
    fun `Mine 출력 테스트`() {
        assertThat(mineCell.toString()).isEqualTo(MINE_STRING)
    }

    @Test
    fun `NotMine 출력 테스트`() {
        assertThat(Cell(false, Position(1, 1)).toString()).isEqualTo("0")
    }

    @Test
    fun `Cell addCount 테스트`() {
        mineCell.addCount()
        assertThat(mineCell.aroundMineCount).isEqualTo(1)
    }
}
