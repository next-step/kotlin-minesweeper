package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class MinesweeperFactoryTest {
    @Test
    fun `MinesweeperFactory를 구현하여 MinesweeperStartInfo를 받아 Minesweeper를 만드는 팩토리를 만들 수 있다`() {
        val minesweeperFactory = object : MinesweeperFactory() {
            override fun getMinePositions(minesweeperInfo: MinesweeperInfo): List<CellPosition> {
                return listOf(CellPosition(0, 0))
            }
        }
        val minesweeper = minesweeperFactory.create(MinesweeperInfo(10, 12, 1))

        assertAll(
            { assertThat(minesweeper).hasSize(10) },
            { assertThat(minesweeper).allMatch { row -> row.size == 12 } },
            { assertThat(minesweeper[0][0]).isEqualTo(Cell.Mine) },
            { assertThat(minesweeper[1][0]).isEqualTo(Cell.Land.ONE) },
            { assertThat(minesweeper[0][1]).isEqualTo(Cell.Land.ONE) }
        )
    }

    @Test
    fun `사용자의 입력과 같은 개수의 지뢰가 생성되어야 한다`() {
        val minesweeperFactory = object : MinesweeperFactory() {
            override fun getMinePositions(minesweeperInfo: MinesweeperInfo): List<CellPosition> {
                return listOf(CellPosition(0, 0))
            }
        }

        assertThrows<IllegalArgumentException> {
            minesweeperFactory.create(MinesweeperInfo(5, 5, 2))
        }
    }
}
