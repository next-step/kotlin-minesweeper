package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.Height
import minesweeper.domain.MineIndexes
import minesweeper.domain.MineMap
import minesweeper.domain.MineMapSize
import minesweeper.domain.MinePoint
import minesweeper.domain.Width
import org.junit.jupiter.api.Test

class MineMapTest {
    @Test
    fun `지뢰 개수가 지도 크기를 초과하면 예외가 발생한다`() {
        val height = Height(1)
        val width = Width(1)
        val mineMapSize = MineMapSize(width, height)
        val mineIndexes = MineIndexes(listOf(1, 2, 3))

        shouldThrow<IllegalArgumentException> { MineMap(mineMapSize, mineIndexes) }
    }

    @Test
    fun `mineIndexes에 표시된 위치에 지뢰가 심어진다`() {
        val height = Height(10)
        val width = Width(10)
        val mineMapSize = MineMapSize(width, height)
        val mineIndexes = MineIndexes(listOf(1, 2, 3))
        val mineMap = MineMap(mineMapSize, mineIndexes)

        mineMap.getPoint(1).shouldBeTypeOf<MinePoint>()
        mineMap.getPoint(2).shouldBeTypeOf<MinePoint>()
        mineMap.getPoint(3).shouldBeTypeOf<MinePoint>()
    }
}
