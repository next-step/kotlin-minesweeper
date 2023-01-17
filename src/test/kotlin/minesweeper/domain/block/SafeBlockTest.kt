package minesweeper.domain.block

import minesweeper.domain.exception.ExceptionReason
import minesweeper.domain.exception.MineSweeperException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SafeBlockTest {

    @Test
    fun `0보다 작은 수는 생성될 수 없다`() {
        val exception = assertThrows<MineSweeperException> {
            SafeBlock(-1)
        }

        assertThat(exception.reason).isEqualTo(ExceptionReason.ILLEGAL_NEAR_MINE_RANGE)
    }

    @DisplayName("블록 생성 테스트")
    @ParameterizedTest(name = "{0}는 생성된다")
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8])
    fun `0~8 까지 들어갈 수 있다`(nearMineCount: Int) {
        assertThat(SafeBlock(nearMineCount)).isExactlyInstanceOf(SafeBlock::class.java)
    }

    @Test
    fun `8보다 큰 수는 생성될 수 없다`() {
        val exception = assertThrows<MineSweeperException> {
            SafeBlock(9)
        }

        assertThat(exception.reason).isEqualTo(ExceptionReason.ILLEGAL_NEAR_MINE_RANGE)
    }
}
