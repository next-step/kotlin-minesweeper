package minesweeper.inputdata

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import java.lang.IllegalArgumentException

internal class PositiveNumberTest {

    @Test
    @DisplayName("0보다 큰수가 아니면 에러를 반환한다.")
    fun positiveNumberException() {
        assertThrows(IllegalArgumentException::class.java) {
            PositiveNumber(0)
        }
    }
}
