package minesweeper.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MineCountTest {
    @ParameterizedTest
    @CsvSource(value = ["0:100", "101:100"], delimiter = ':')
    fun `MineCount - 범위 초과에 대한 예외처리 테스트`(mineCount: Int, maxMineCount: Int) {
        // given, when, then
        Assertions.assertThatThrownBy { MineCount(mineCount, maxMineCount) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("지뢰의 개수는 1 이상 $maxMineCount 이하의 정수여야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "mine"])
    fun `MineCount - 숫자 이외의 값 입력에 대한 예외처리 테스트`(input: String) {
        // given, when, then
        Assertions.assertThatThrownBy { MineCount(input, FIXED_MAX_MINE_COUNT) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("숫자 이외의 값은 입력할 수 없습니다.")
    }

    companion object {
        private const val FIXED_MAX_MINE_COUNT = 100
    }
}
