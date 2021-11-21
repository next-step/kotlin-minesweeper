package domain

import exception.IllegalPositionException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PositionTest {

    @DisplayName("좌표는 1 보다 작을 수 없다.")
    @ParameterizedTest
    @CsvSource("-1,-3", "0,0", "2,-5", "0,3")
    fun illegalPosition(i: Int, j: Int) {
        assertThatExceptionOfType(IllegalPositionException::class.java)
            .isThrownBy { Position(i, j) }
    }

    @DisplayName("좌표가 같다면 같은 Position 으로 인식되어야 한다.")
    @ParameterizedTest
    @CsvSource("1,3", "2,5", "4,8")
    fun equals(i: Int, j: Int) {
        assertThat(Position(i, j)).isEqualTo(Position(i, j))
    }

    @DisplayName("default 좌표는 (1,1) 이다.")
    @Test
    fun default() {
        assertThat(Position()).isEqualTo(Position(1, 1))
    }
}
