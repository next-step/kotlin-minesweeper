package domain

import domain.pos.AbstractPos
import domain.pos.RelativePos
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class AbstractPosTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 10, 100])
    internal fun `위치가 생성된다`(input: Int) {
        // given
        // when, then
        assertDoesNotThrow { AbstractPos.of(input) }
    }

    @Test
    internal fun `위치가 음수면 예외가 발생한다`() {
        // given
        // when, then
        assertThatIllegalArgumentException().isThrownBy { AbstractPos.of(-2) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 10])
    internal fun `10까지의 좌표는 동일 개체를 반환한다`(input: Int) {
        // given
        val abstractPos1 = AbstractPos.of(input)
        val abstractPos2 = AbstractPos.of(input)

        // when, then
        assertThat(abstractPos1).isSameAs(abstractPos2)
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 50])
    internal fun `11 좌표 부터는 다른 개체를 반환한다`(input: Int) {
        // given
        val abstractPos1 = AbstractPos.of(input)
        val abstractPos2 = AbstractPos.of(input)

        // when, then
        assertThat(abstractPos1).isNotSameAs(abstractPos2)
    }

    @Test
    internal fun `두 Pos의 합이 음수이면 true가 반환된다`() {
        // given
        val abstractPos1 = AbstractPos.of(0)
        val abstractPos2 = RelativePos.of(-1)

        // when
        val isNotPossiblePlus = abstractPos1.isNotPossiblePlus(abstractPos2)

        // then
        assertThat(isNotPossiblePlus).isTrue
    }

    @ParameterizedTest
    @CsvSource(value = ["0, 1", "1, -1"])
    internal fun `두 Pos의 합이 0 또는 양수이면 false가 반환된다`(input1: Int, input2: Int) {
        // given
        val pos1 = AbstractPos.of(input1)
        val pos2 = RelativePos.of(input2)

        // when
        val isNotPossiblePlus = pos1.isNotPossiblePlus(pos2)

        // then
        assertThat(isNotPossiblePlus).isFalse
    }
}
