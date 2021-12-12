package minesweeper.domain.block

import minesweeper.fixture.BoardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("일반 셀(Cell)")
internal class EmptyBlockTest {

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["0:0", "10:10", "0:10", "10:0"], delimiter = ':')
    fun `위치로 이루어진다`(x: Int, y: Int) {
        val position = Position(x, y)

        val emptyBlock = EmptyBlock(position)

        assertAll(
            { assertThat(emptyBlock).isNotNull },
            { assertThat(emptyBlock).isExactlyInstanceOf(EmptyBlock::class.java) },
        )
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["0:0", "10:10", "0:10", "10:0"], delimiter = ':')
    fun `지뢰가 아니다`(x: Int, y: Int) {
        val position = Position(x, y)

        val emptyBlock = EmptyBlock(position)

        assertThat(emptyBlock.isMine).isFalse
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["1:0:2", "1:1:3", "1:2:2", "2:0:0", "2:1:0", "2:2:0"], delimiter = ':')
    fun `지뢰 개수를 반환한다`(blockX: Int, blockY: Int, expected: Int) {
        val board = BoardFixture.createBoard(3, 3, 3)
        val position = Position(blockX, blockY)

        val emptyBlock = EmptyBlock(position)
        val actual = emptyBlock.adjacentMineCount(board)
        assertThat(actual).isEqualTo(AdjacentMineCount.from(expected))
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["0:0:0", "10:10:1", "0:10:4", "10:0:8"], delimiter = ':')
    fun `안열린 상태 여부를 반환한다`(x: Int, y: Int, count: Int) {
        val position = Position(x, y)

        val emptyBlock = EmptyBlock(position)
        assertThat(emptyBlock.isOpened()).isFalse
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["0:0:0", "10:10:1", "0:10:4", "10:0:8"], delimiter = ':')
    fun `열린 상태 여부를 반환한다`(x: Int, y: Int, count: Int) {
        val position = Position(x, y)

        val emptyBlock = EmptyBlock(position)
        val actual = emptyBlock.open()
        assertThat(actual.isOpened()).isTrue
    }
}
