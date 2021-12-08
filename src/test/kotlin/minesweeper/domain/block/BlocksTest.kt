package minesweeper.domain.block

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class BlocksTest {

    @Test
    fun `지뢰가 존재하지않으면 예외를 던진다`() {
        val givenBlocks = listOf(
            None(Position(0, 0)),
            None(Position(0, 1)),
            None(Position(1, 0)),
            None(Position(1, 1))
        )

        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { Blocks(givenBlocks) }.withMessage("최소 1개 이상 지뢰가 있어야합니다.")
    }

    @Test
    fun `좌표를 입력받아 블록을 찾을 수 있다`() {
        val givenMine = Mine(Position(0, 0))
        val givenNone1 = None(Position(0, 1))
        val givenNone2 = None(Position(1, 0))
        val givenNone3 = None(Position(1, 1))
        val givenBlocks = listOf(
            givenMine,
            givenNone1,
            givenNone2,
            givenNone3
        )
        val blocks = Blocks(givenBlocks)

        val actual = blocks.findBlock(0, 0)

        assertThat(actual).isEqualTo(givenMine)
    }

    @Test
    fun `게임 종료여부를 리턴한다`() {
        val givenMine = Mine(Position(0, 0))
        val givenNone1 = None(Position(0, 1))
        val givenNone2 = None(Position(1, 0))
        val givenNone3 = None(Position(1, 1))
        val givenBlocks = listOf(
            givenMine,
            givenNone1,
            givenNone2,
            givenNone3
        )
        val blocks = Blocks(givenBlocks)

        assertThat(blocks.isWinGame()).isFalse
    }
}
