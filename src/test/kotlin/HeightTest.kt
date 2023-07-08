import domain.Height
import io.kotest.assertions.throwables.shouldThrow
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HeightTest {
    @Test
    fun `Height 0 이하이면 IllegalArgumentException`() {
        shouldThrow<IllegalArgumentException> {
            Height(0)
        }
    }

    @Test
    fun `value 잘 들어갔는지 확인`() {
        Assertions.assertThat(
            Height(2).value
        ).isEqualTo(2)
    }
}
