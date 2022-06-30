package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MinesweeperFactoryTest {
    @Test
    fun `MinesweeperFactory를 구현하여 MinesweeperStartInfo를 받아 Minesweeper를 만드는 팩토리를 만들 수 있다`() {
        val minesweeperFactory = object : MinesweeperFactory {
            override fun create(minesweeperInfo: MinesweeperInfo): Minesweeper {
                val rows = listOf(Row(listOf(Cell.Mine, Cell.Land.ZERO)))
                return Minesweeper(rows)
            }
        }
        val minesweeper = minesweeperFactory.create(MinesweeperInfo(0, 0, 0))

        assertAll(
            { assertThat(minesweeper).hasSize(1) },
            { assertThat(minesweeper.first()).hasSize(2) },
            { assertThat(minesweeper.first()).contains(Cell.Mine, Cell.Land.ZERO) }
        )
    }
}
