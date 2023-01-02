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
        val blockState = Normal.of(Point(0, 0))

        assertThat(blockState.isMine()).isFalse
        assertThat(blockState.isOpen()).isFalse
    }

    @Test
    fun `지뢰가 아닌 블록은 지뢰가 될 수 있다`() {
        val blockState = Normal.of(Point(0, 0))

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

    @Test
    fun `일반 블록은 자신의 주변 지뢰 개수를 알 수 있다`() {
        val blockState = Normal.of(Point(0, 0), 2)

        assertThat(blockState.countOfSurroundMines).isEqualTo(2)
    }

    @Test
    fun `지뢰 블록은 자신의 주변 지뢰 개수를 가질 수 없다`() {
        val blockState = Mine(Point(0, 0))

        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
            blockState.countOfSurroundMines
        }
            .withMessage("지뢰는 주변 지뢰 개수를 가지지 않습니다.")
    }

    @Test
    fun `일반 블록은 열 수 있다`() {
        val blockState = Normal.of(Point(0, 0))

        assertThat(blockState.open().isOpen()).isTrue
    }

    @Test
    fun `지뢰 블록은 열 수 없다`() {
        val blockState = Mine(Point(0, 0))

        assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
            blockState.open()
        }
            .withMessage("지뢰는 열 수 없습니다.")
    }
}
