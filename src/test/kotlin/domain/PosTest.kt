package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PosTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 10, 100])
    internal fun `위치가 생성된다`(input: Int) {
        // given
        // when, then
        assertDoesNotThrow { Pos.of(input) }
    }

    @Test
    internal fun `위치가 음수면 예외가 발생한다`() {
        // given
        // when, then
        assertThatIllegalArgumentException().isThrownBy { Pos.of(-1) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 10])
    internal fun `10까지의 좌표는 동일 개체를 반환한다`(input: Int) {
        // given
        val pos1 = Pos.of(input)
        val pos2 = Pos.of(input)

        // when, then
        assertThat(pos1).isSameAs(pos2)
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 50])
    internal fun `11 좌표 부터는 다른 개체를 반환한다`(input: Int) {
        // given
        val pos1 = Pos.of(input)
        val pos2 = Pos.of(input)

        // when, then
        assertThat(pos1).isNotSameAs(pos2)
    }
}
