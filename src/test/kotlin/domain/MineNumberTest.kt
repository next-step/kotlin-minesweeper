package domain

import exception.IllegalMineNumberException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class MineNumberTest {
    @DisplayName("MineNumber 는 음수가 될 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5, -6, -7, -8, -9])
    fun illegal(number: Int) {
        assertThatExceptionOfType(IllegalMineNumberException::class.java)
            .isThrownBy { MineNumber(number) }
    }

    @DisplayName("MineNumber 의 초기값은 0 이다.")
    @Test
    fun default() {
        val mineNumber = MineNumber()
        assertAll(
            { assertThat(mineNumber.number).isZero },
            { assertThat(mineNumber.isBlank()).isTrue }
        )
    }

    @DisplayName("MineNumber 를 ++ 하면 증가한다.")
    @ParameterizedTest
    @CsvSource("3,2", "4,8", "7,2")
    fun inc(number: Int, increment: Int) {
        var mineNumber = MineNumber(number)
        repeat(increment) { mineNumber++ }
        assertAll(
            { assertThat(mineNumber.number).isEqualTo(number + increment) },
            { assertThat(mineNumber.isBlank()).isFalse }
        )
    }

    @DisplayName("숫자가 같다면 같은 MineNumber 로 인식되어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    fun equals(number: Int) {
        assertThat(MineNumber(number)).isEqualTo(MineNumber(number))
    }
}
