package minesweeper

import io.kotest.matchers.shouldBe
import minesweeper.domain.Height
import minesweeper.domain.MineMapSize
import minesweeper.domain.Width
import org.junit.jupiter.api.Test

class MineMapSizeTest {
    @Test
    fun `size는 width와 height의 value의 곱이다`() {
        val width = Width(5)
        val height = Height(5)
        val mineMapSize = MineMapSize(width, height)

        mineMapSize.size() shouldBe 25
    }
}
