package minesweeper.domain.block

import minesweeper.exception.InvalidAdjacentMineCountRangeException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("지뢰 개수(AdjacentMineCount)")
internal class AdjacentMineCountTest {

    @ParameterizedTest(name = "입력 값: {0}")
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8])
    fun `지뢰 개수는 0과 8사이의 숫자로 이루어진다`(adjacentMineCountInt: Int) {
        val adjacentMineCount = AdjacentMineCount.from(adjacentMineCountInt)

        assertAll(
            { assertThat(adjacentMineCount).isNotNull },
            { assertThat(adjacentMineCount).isExactlyInstanceOf(AdjacentMineCount::class.java) }
        )
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @ValueSource(ints = [-1, -2, -10, Integer.MIN_VALUE])
    fun `지뢰 개수는 음수로 이루어지지 않는다`(adjacentMineCountInt: Int) {
        val exception = assertThrows<InvalidAdjacentMineCountRangeException> { AdjacentMineCount.from(adjacentMineCountInt) }

        assertThat(exception.message).isEqualTo("'%s'는 올바른 AdjacentMineCount 의 범위가 아닙니다.".format(adjacentMineCountInt))
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @ValueSource(ints = [9, 10, 100, Integer.MAX_VALUE])
    fun `지뢰 개수는 8초과의 수로 이루어지지 않는다`(adjacentMineCountInt: Int) {
        val exception = assertThrows<InvalidAdjacentMineCountRangeException> { AdjacentMineCount.from(adjacentMineCountInt) }

        assertThat(exception.message).isEqualTo("'%s'는 올바른 AdjacentMineCount 의 범위가 아닙니다.".format(adjacentMineCountInt))
    }
}
