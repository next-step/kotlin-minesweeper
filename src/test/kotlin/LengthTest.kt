import model.Length
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class LengthTest {
    @Test
    fun `Length 테스트 - 비정상 생성1`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Length(4)
        }
    }

    @Test
    fun `Length 테스트 - 비정상 생성2`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Length(16)
        }
    }
}
