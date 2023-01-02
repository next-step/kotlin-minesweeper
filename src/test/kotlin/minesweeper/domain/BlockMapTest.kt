package minesweeper.domain

import minesweeper.model.BlockOpenResult
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

    @Test
    fun `지뢰가 없는 인접한 칸이 모두 열리게 된다`() {
        // given
        /**
         * 1 1 1 0 0
         * 1 * 1 0 0
         * 1 1 1 1 1
         * 0 0 1 2 *
         * 0 0 1 * 2
         */
        val width = Width(5)
        val height = Height(5)
        val mineCount = MineCount(3)
        val mines = listOf(Point(1, 1), Point(3, 4), Point(4, 3))
        val point = Point(0, 4)
        val mineGenerator = FakeMineGenerator(mines)

        // when
        val blockMap = BlockMap(
            height = height,
            width = width,
            mineCount = mineCount,
            mineGenerator = mineGenerator,
        )

        // then
        assertThat(blockMap.open(point)).isEqualTo(BlockOpenResult.OPENED)
        assertThat(blockMap.find(point)?.state?.isOpen()).isTrue
        assertThat(blockMap.countOpenBlocks()).isEqualTo(9)
    }

    @Test
    fun `이미 열려 있는 블록을 열면 이미 열었다는 결과 확인`() {
        // given
        val width = Width(10)
        val height = Height(10)
        val mineCount = MineCount(1)
        val point = Point(0, 0)
        val mines = listOf(Point(2, 1))
        val mineGenerator = FakeMineGenerator(mines)

        // when
        val blockMap = BlockMap(height = height, width = width, mineCount = mineCount, mineGenerator = mineGenerator)

        // then
        assertThat(blockMap.open(point)).isEqualTo(BlockOpenResult.OPENED)
        assertThat(blockMap.open(point)).isEqualTo(BlockOpenResult.ALREADY_OPENED)
    }

    @Test
    fun `지뢰 블록을 열게 되면 게임에서 진다`() {
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
        assertThat(blockMap.open(point)).isEqualTo(BlockOpenResult.MINE)
    }
}
