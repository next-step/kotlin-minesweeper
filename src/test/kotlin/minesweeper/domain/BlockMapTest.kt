package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Point
import minesweeper.model.Width
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BlockMapTest {
    @Test
    fun `입력받은 width, height 만큼 block을 생성할 수 있다`() {
        // given
        val width = 10
        val height = 10
        val mineCount = 10

        // when
        val blockMap = BlockMap(width, height, mineCount)

        // then
        assertThat(blockMap.width).isEqualTo(width)
        assertThat(blockMap.height).isEqualTo(height)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `너비 1개 미만으로 입력시 에러가 발생한다`(width: Int) {
        val height = 10
        val mineCount = 10

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            BlockMap(width, height, mineCount)
        }
            .withMessage("너비는 1개 이상이어야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `높이 1개 미만으로 입력시 에러가 발생한다`(height: Int) {
        val width = 10
        val mineCount = 10

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            BlockMap(width, height, mineCount)
        }
            .withMessage("높이는 1개 이상이어야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `지뢰 개수 1개 미만으로 입력시 에러가 발생한다`(mineCount: Int) {
        val width = 10
        val height = 10

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            BlockMap(width, height, mineCount)
        }
            .withMessage("지뢰 개수는 1개 이상이어야 합니다.")
    }

    @Test
    fun `지뢰 개수가 너비 * 높이 보다 많으면 에러가 발생한다`() {
        val width = 10
        val height = 10
        val mineCount = 101

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            BlockMap(width, height, mineCount)
        }
            .withMessage("지뢰 개수는 너비 * 높이 보다 작거나 같아야 합니다.")
    }

    @Test
    fun `지뢰 생성기를 통해서 지뢰 블락을 생성할 수 있다`() {
        // given
        val width = Width(10)
        val height = Height(10)
        val mineCount = MineCount(10)
        val point = Point(2, 1)
        val mines = listOf(point)
        val mineGenerator = FakeMinGenerator(mines)

        // when
        val blockMap = BlockMap(height = height, width = width, mineCount = mineCount, mineGenerator = mineGenerator)

        // then
        assertThat(blockMap.find(point)?.state?.isMine()).isTrue
    }
}
