import domain.Width
import io.kotest.assertions.throwables.shouldThrow
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class WidthTest {

    @Test
    fun `Width 0 이하이면 IllegalArgumentException`() {
        shouldThrow<IllegalArgumentException> {
            Width(0)
        }
    }

    @Test
    fun `value 잘 들어갔는지 확인`() {
        Assertions.assertThat(
            Width(2).value
        ).isEqualTo(2)
    }
}
