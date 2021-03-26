import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class BoardGeneratorTest {

    @Test
    fun `지뢰 갯수가 0보다 크지 않으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BoardGenerator(10, 10, 0)
        }
    }

    @Test
    fun `지뢰 갯수가 너비와 높이를 곱한 값보다 크면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BoardGenerator(5, 5, 50)
        }
    }

    @Test
    fun `입력한 지뢰 갯수만큼 지뢰가 생성된 cell들을 생성한다`() {
        val mineCount = 7
        val cells = BoardGenerator(10, 10, mineCount).generateRandomCell()
        assertThat(cells.filter { it.isMine }.size).isEqualTo(mineCount)
    }
}
