package minesweeper.domain.block

import org.assertj.core.api.Assertions.assertThat
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

        val mineBlock = MineBlock(position)

        assertAll(
            { assertThat(mineBlock).isNotNull },
            { assertThat(mineBlock).isExactlyInstanceOf(MineBlock::class.java) },
        )
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["0:0", "10:10", "0:10", "10:0"], delimiter = ':')
    fun `지뢰가 아니다`(x: Int, y: Int) {
        val position = Position(x, y)

        val mineBlock = MineBlock(position)

        assertThat(mineBlock.isMine).isTrue
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["0:0", "10:10", "0:10", "10:0"], delimiter = ':')
    fun `안열린 상태 여부를 반환한다`(x: Int, y: Int) {
        val position = Position(x, y)

        val mineBlock = MineBlock(position)
        assertThat(mineBlock.isOpened()).isFalse
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["0:0", "10:10", "0:10", "10:0"], delimiter = ':')
    fun `열린 상태 여부를 반환한다`(x: Int, y: Int) {
        val position = Position(x, y)

        val mineBlock = MineBlock(position)
        val actual = mineBlock.open()
        assertThat(actual.isOpened()).isTrue
    }
}
