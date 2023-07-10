import domain.MineCount
import io.kotest.assertions.throwables.shouldThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineCountTest {
    @Test
    fun `mineCount 음수이면 IllegalArgumentException`() {
        shouldThrow<IllegalArgumentException> {
            MineCount(-1)
        }
    }

    @Test
    fun `value 잘 들어갔는지 확인`() {
        assertThat(
            MineCount(2).value
        ).isEqualTo(2)
    }
}
