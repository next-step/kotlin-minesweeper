package minesweeper.domain.block

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("지뢰(MineBlock)")
internal class MineBlockTest {

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["0:0", "10:10", "0:10", "10:0"], delimiter = ':')
    fun `위치로 이루어진다`(x: Int, y: Int) {
        val position = Position(x, y)

        val mines = MineBlock(position)

        assertAll(
            { Assertions.assertThat(mines).isNotNull },
            { Assertions.assertThat(mines).isExactlyInstanceOf(MineBlock::class.java) },
        )
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["0:0", "10:10", "0:10", "10:0"], delimiter = ':')
    fun `지뢰가 아니다`(x: Int, y: Int) {
        val position = Position(x, y)

        val mines = MineBlock(position)

        Assertions.assertThat(mines.isMines()).isTrue
    }
}
