import model.MineCount
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class MineCountTest {
    @Test
    fun `MineCount - 비정상 생성 테스트1`() {
        assertThatExceptionOfType(Exception::class.java).isThrownBy {
            MineCount(0, 10)
        }
    }

    @Test
    fun `MineCount - 비정상 생성 테스트2`() {
        assertThatExceptionOfType(Exception::class.java).isThrownBy {
            MineCount(11, 10)
        }
    }
}
