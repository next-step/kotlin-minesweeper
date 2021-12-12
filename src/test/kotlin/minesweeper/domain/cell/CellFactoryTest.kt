package minesweeper.domain.cell

import minesweeper.domain.board.MineCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class CellFactoryTest {

    @Test
    fun `cell이 mine이라면 MineCell이다`() {
        val result = CellFactory.getCell(true, MineCount(1))

        assertThat(result).isInstanceOf(MineCell::class.java)
    }

    @Test
    fun `cell이 mine이 아니라면 BlockCell이다`() {
        val result = CellFactory.getCell(false, MineCount(1))

        assertThat(result).isInstanceOf(BlockCell::class.java)
    }

    @Test
    fun `cell이 mine이 아니라면 주위 지뢰 갯수를 갖는다`() {
        val result = CellFactory.getCell(false, MineCount(1))

        assertThat((result as BlockCell).aroundMineCount).isEqualTo(MineCount(1))
    }
}
