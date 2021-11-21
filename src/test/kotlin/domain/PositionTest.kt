package domain

import exception.IllegalPositionException
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PositionTest {

    @DisplayName("Position 은 1 보다 작을 수 없다")
    @ParameterizedTest
    @CsvSource("-1,-3", "0,0", "2,-5", "0,3")
    fun illegalPosition(i: Int, j: Int) {
        assertThatExceptionOfType(IllegalPositionException::class.java)
            .isThrownBy { Position(i, j) }
    }
}
