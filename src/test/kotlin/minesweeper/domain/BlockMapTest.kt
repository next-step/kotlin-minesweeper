package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.MineCount
import minesweeper.model.Point
import minesweeper.model.Width
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

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
        val mineCount = MineCount(1)
        val point = Point(2, 1)
        val mines = listOf(point)
        val mineGenerator = FakeMineGenerator(mines)

        // when
        val blockMap = BlockMap(height = height, width = width, mineCount = mineCount, mineGenerator = mineGenerator)

        // then
        assertThat(blockMap.find(point)?.state?.isMine()).isTrue
        assertThat(blockMap.find(Point(0, 0))?.state?.isMine()).isFalse
    }

    @Test
    fun `일반 블락의 주변 지뢰 개수를 확인할 수 있다`() {
        // given
        val width = Width(10)
        val height = Height(10)
        val mineCount = MineCount(1)
        val point = Point(2, 1)
        val mineGenerator = FakeMineGenerator(emptyList())
        val mineDetector = FakeMineDetector(point, 3)

        // when
        val blockMap = BlockMap(
            height = height,
            width = width,
            mineCount = mineCount,
            mineGenerator = mineGenerator,
            mineDetector = mineDetector
        )

        // then
        assertThat(blockMap.find(point)?.state?.countOfSurroundMines).isEqualTo(3)
    }
}
