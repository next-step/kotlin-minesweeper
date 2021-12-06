package minesweeper.domain

import minesweeper.exception.InvalidMinesCountRangeException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("지뢰 갯수(MinesCount)")
internal class MineBlockCountTest {

    @ParameterizedTest(name = "입력 값: {0}")
    @ValueSource(ints = [1, 10, 100, 1000, Integer.MAX_VALUE])
    fun `1이상의 값으로 이루어진다`(numberOfMines: Int) {
        val minesCount = MinesCount(numberOfMines)

        assertAll(
            { Assertions.assertThat(minesCount).isNotNull },
            { Assertions.assertThat(minesCount).isExactlyInstanceOf(MinesCount::class.java) },
        )
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @ValueSource(ints = [0, -1, -10, -100, Integer.MIN_VALUE])
    fun `0이하의 값으로 이루어질 수 없다`(numberOfMines: Int) {
        val exception = assertThrows<InvalidMinesCountRangeException> { MinesCount(numberOfMines) }

        Assertions.assertThat(exception.message).isEqualTo("'%s'는 올바른 MinesCount 의 범위가 아닙니다.".format(numberOfMines))
    }
}
