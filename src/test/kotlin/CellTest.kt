import model.Cell
import model.Position
import model.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTest {
    private val mineCell = Cell(Position(1, 1), Value.MINE)
    private val zeroCell = Cell(Position(1, 1), Value.ZERO)

    @Test
    fun `Cell match 테스트`() {
        assertThat(mineCell.match(Position(1, 1))).isTrue()
    }

    @Test
    fun `Cell match 테스트2`() {
        assertThat(mineCell.match(Position(2, 1))).isFalse()
    }

    @Test
    fun `Cell addCount 테스트`() {
        zeroCell.addCount()
        assertThat(zeroCell.value.string).isEqualTo("1")
    }

    @Test
    fun `Cell isMine 테스트1`() {
        assertThat(mineCell.isMine()).isTrue()
    }

    @Test
    fun `Cell isMine 테스트2`() {
        assertThat(zeroCell.isMine()).isFalse()
    }
}
