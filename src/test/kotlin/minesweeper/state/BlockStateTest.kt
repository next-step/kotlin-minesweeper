package minesweeper.state

import minesweeper.model.Point
import minesweeper.state.BlockState.Mine
import minesweeper.state.BlockState.Normal
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class BlockStateTest {
    @Test
    fun `지뢰가 아닌 블록은 지뢰가 아니다`() {
        val blockState = Normal(Point(0, 0))

        assertThat(blockState.isMine()).isFalse
    }

    @Test
    fun `지뢰가 아닌 블록은 지뢰가 될 수 있다`() {
        val blockState = Normal(Point(0, 0))

        assertThat(blockState.mine().isMine()).isTrue
    }

    @Test
    fun `지뢰인 블록은 지뢰이다`() {
        val blockState = Mine(Point(0, 0))

        assertThat(blockState.isMine()).isTrue
    }

    @Test
    fun `지뢰인 블록은 지뢰가 될 수 없다`() {
        val blockState = Mine(Point(0, 0))

        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
            blockState.mine()
        }
            .withMessage("이미 지뢰입니다.")
    }
}
