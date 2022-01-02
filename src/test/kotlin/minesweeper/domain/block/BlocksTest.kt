package minesweeper.domain.block

import minesweeper.fixture.BoardFixture.TEST_MINE_BLOCK_GENERATE_STRATEGY
import minesweeper.fixture.BoardFixture.createBlocks
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("블록들(Blocks)")
internal class BlocksTest {

    @ParameterizedTest(name = "입력값 {0}, {1}, {2}")
    @CsvSource(
        value = [
            "1:1:true", "1:2:true", "1:3:true",
            "2:1:false", "2:2:false", "2:3:false",
            "3:1:false", "3:2:false", "3:3:false"
        ],
        delimiter = ':'
    )
    fun `특정 위치가 지뢰인지 확인한다`(x: Int, y: Int, expected: Boolean) {
        val blocks = createBlocks(3, 3, 3, TEST_MINE_BLOCK_GENERATE_STRATEGY)
        val position = Position(x, y)

        assertThat(blocks.isMinePosition(position)).isEqualTo(expected)
    }

    @Test
    fun `일반 블록이 모두 오픈되지 않았다면 false 를 반환한다`() {
        val blocks = createBlocks(1, 2, 1, TEST_MINE_BLOCK_GENERATE_STRATEGY)

        assertThat(blocks.isEmptyBlocksAllOpened()).isFalse
    }

    @Test
    fun `일반 블록이 모두 오픈되었다면 true 를 반환한다`() {
        val blocks = createBlocks(1, 2, 1, TEST_MINE_BLOCK_GENERATE_STRATEGY)
        val openedBlocks = blocks.open(Position(1, 2))

        assertThat(openedBlocks.isEmptyBlocksAllOpened()).isTrue
    }
}
