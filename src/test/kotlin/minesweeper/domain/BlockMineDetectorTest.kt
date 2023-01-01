package minesweeper.domain

import minesweeper.model.Height
import minesweeper.model.Point
import minesweeper.model.Width
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlockMineDetectorTest {
    @Test
    fun `3x3 중앙 위치(Point) 주변에 지뢰 개수를 확인 할 수 있다`() {
        // given
        val point = Point(1, 1)
        val mines = listOf(Point(0, 0), Point(1, 0), Point(2, 2))
        val blockMineDetector = BlockMineDetector(Width(3), Height(3))

        // when
        val mineCount = blockMineDetector.detect(point, mines)

        // then
        assertThat(mineCount).isEqualTo(3)
    }

    @Test
    fun `2x2 맵에서 시작 포인트(x=0,y=0) 주변에 지뢰 개수를 확인 할 수 있다`() {
        // given
        val point = Point(0, 0)
        val mines = listOf(Point(0, 1), Point(1, 0))
        val blockMineDetector = BlockMineDetector(Width(2), Height(2))

        // when
        val mineCount = blockMineDetector.detect(point, mines)

        // then
        assertThat(mineCount).isEqualTo(2)
    }

    @Test
    fun `2x2 맵에서 배열 마지막 포인트(x=1,y=1) 주변에 지뢰 개수를 확인 할 수 있다`() {
        // given
        val point = Point(1, 1)
        val mines = listOf(Point(0, 0), Point(1, 0))
        val blockMineDetector = BlockMineDetector(Width(2), Height(2))

        // when
        val mineCount = blockMineDetector.detect(point, mines)

        // then
        assertThat(mineCount).isEqualTo(2)
    }

    @Test
    fun `2x4 맵에서 특정 포인트(x=0, y=2) 주변에 지뢰 개수를 확인 할 수 있다`() {
        // given
        val point = Point(0, 2)
        val mines = listOf(Point(0, 3), Point(0, 1), Point(1, 2))
        val blockMineDetector = BlockMineDetector(Width(4), Height(2))

        // when
        val mineCount = blockMineDetector.detect(point, mines)

        // then
        assertThat(mineCount).isEqualTo(3)
    }

    @Test
    fun `2x4 맵에서 특정 포인트(x=1, y=1) 주변에 지뢰 개수를 확인 할 수 있다`() {
        // given
        val point = Point(1, 1)
        val mines = listOf(Point(1, 0), Point(0, 1), Point(1, 2))
        val blockMineDetector = BlockMineDetector(Width(4), Height(2))

        // when
        val mineCount = blockMineDetector.detect(point, mines)

        // then
        assertThat(mineCount).isEqualTo(3)
    }

    @Test
    fun `2x4 맵에서 특정 포인트(x=0, y=2) 주변에 있지 않는 지뢰는 감지되지 않는다`() {
        // given
        val point = Point(0, 2)
        val mines = listOf(Point(0, 0))
        val blockMineDetector = BlockMineDetector(Width(4), Height(2))

        // when
        val mineCount = blockMineDetector.detect(point, mines)

        // then
        assertThat(mineCount).isEqualTo(0)
    }

    @Test
    fun `2x4 맵에서 특정 포인트(x=1, y=1) 주변에 있지 않는 지뢰는 감지되지 않는다`() {
        // given
        val point = Point(1, 1)
        val mines = listOf(Point(1, 3))
        val blockMineDetector = BlockMineDetector(Width(4), Height(2))

        // when
        val mineCount = blockMineDetector.detect(point, mines)

        // then
        assertThat(mineCount).isEqualTo(0)
    }
}
