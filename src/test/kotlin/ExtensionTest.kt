import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExtensionTest {

    @Test
    fun `숫자가 아닌 문자열이 입력되었을때는 예외와 커스텀 에러 메시지가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            "a".parseInt()
        }
    }

    @Test
    fun `null이 입력되었을때는 예외를 발생시킨다`() {
        val empty: String? = null
        assertThrows<IllegalArgumentException> {
            empty.parseInt()
        }
    }

    @Test
    fun `정수인 문자열은 정상적으로 int형으로 변환된다`() {
        assertThat("7".parseInt()).isEqualTo(7)
    }
}
