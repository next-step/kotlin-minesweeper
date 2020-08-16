package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlockTest {
    @Test
    fun `Position들을 가지고 지뢰가 아닌 일반 칸 만들기`() {
        // given
        val positions = listOf(Position(1, 1), Position(2, 1))
        val expectedBlocks = listOf(
            Block(1, 1, false),
            Block(2, 1, false)
        )

        // then
        assertThat(Block.ofNormalsFrom(positions)).isEqualTo(expectedBlocks)
    }

    @Test
    fun `Position들을 가지고 지뢰 칸 만들기`() {
        // given
        val positions = listOf(Position(1, 1), Position(2, 1))
        val expectedBlocks = listOf(
            Block(1, 1, true),
            Block(2, 1, true)
        )

        // then
        assertThat(Block.ofMinesFrom(positions)).isEqualTo(expectedBlocks)
    }
}
