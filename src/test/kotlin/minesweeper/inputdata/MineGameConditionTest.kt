package minesweeper.inputdata

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import java.lang.IllegalArgumentException

internal class MineGameConditionTest {

    @Test
    @DisplayName("map 크기 보다 지뢰의 개수가 많을 수 없다.")
    fun mapSizeCompare() {
        assertThrows(IllegalArgumentException::class.java) {
            MineGameCondition(PositiveNumber(2), PositiveNumber(3), PositiveNumber(8))
        }
    }
}
