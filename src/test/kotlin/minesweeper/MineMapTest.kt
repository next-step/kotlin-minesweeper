package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.MineMap
import minesweeper.domain.Width
import org.junit.jupiter.api.Test

class MineMapTest {
    @Test
    fun `지뢰 개수가 지도 크기를 초과하면 예외가 발생한다`() {
        val height = Height(1)
        val width = Width(1)
        val mineCount = MineCount(2)

        shouldThrow<IllegalArgumentException> { MineMap(height, width, mineCount) }
    }
}
