package minesweeper.domain.board

import minesweeper.domain.RandomGenerator
import minesweeper.domain.cell.BlockCell
import minesweeper.domain.cell.MineCell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class BoardFactoryTest {

    @Test
    fun `height, width로 Board를 생성하면 height X width 갯수만큼 Cell이 생성된다`() {
        val factory = BoardFactory(RangeFromZeroGenerator)
        val height = Height(3)
        val width = Width(3)

        val result = factory.createBy(height, width, MineCount(3))

        assertThat(result.cells.cells).hasSize(9)
    }

    @Test
    fun `mineCount 갯수만큼 MineCell이 생성되고, 나머지는 BlockCell로 생성된다`() {
        val factory = BoardFactory(RangeFromZeroGenerator)
        val height = Height(3)
        val width = Width(3)
        val mineCount = MineCount(3)

        val result = factory.createBy(height, width, mineCount)

        val cells = result.cells.cells.toSortedMap().values.toList()
        assertAll(
            { assertThat(cells.slice(0..2)).hasOnlyElementsOfType(MineCell::class.java) },
            { assertThat(cells.slice(3..8)).hasOnlyElementsOfType(BlockCell::class.java) },
        )
    }
}

private object RangeFromZeroGenerator : RandomGenerator {

    override fun generate(start: Int, until: Int, count: Int): List<Int> {
        return (0 until count).toList()
    }
}
