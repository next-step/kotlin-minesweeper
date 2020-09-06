import model.Size
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class SizeTest {
    @Test
    fun `Size Invalid Error Case Test1`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Size(4)
        }
    }

    @Test
    fun `Size Invalid Error Case Test2`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Size(16)
        }
    }
}
