import model.Type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TypeTest {
    @Test
    fun `Type 테스트 - None,Mine`() {
        assertThat(Type.NONE.nextValue()).isEqualTo(Type.NONE)
        assertThat(Type.MINE.nextValue()).isEqualTo(Type.MINE)
    }

    @Test
    fun `Type 테스트 - Others`() {
        assertThat(Type.ONE.nextValue()).isEqualTo(Type.TWO)
        assertThat(Type.FOUR.nextValue()).isEqualTo(Type.FIVE)
    }
}
