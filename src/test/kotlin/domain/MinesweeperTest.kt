package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MinesweeperTest {
    @Test
    fun `Minesweeper는 지뢰찾기 게임의 현황을 구성한다`() {
        val minesweeperInfo = MinesweeperInfo(10, 10, 10)
        val minesweeper = Minesweeper.from(minesweeperInfo)

        assertAll(
            { assertThat(minesweeper).hasSize(minesweeperInfo.rowCount) },
            {
                assertThat(minesweeper).allMatch {
                    it.size == minesweeperInfo.columnCount
                }
            }
        )
    }

    @Test
    fun `MinePositionsFactory를 전달하여 특정 위치에 지뢰를 생성할 수 있다`() {
        val minesweeperInfo = MinesweeperInfo(10, 10, 3)
        val minePositionsFactory = object : MinePositionsFactory {
            override fun create(minesweeperInfo: MinesweeperInfo): MinePositions {
                return MinePositions.of(
                    positions = listOf(CellPosition(0, 0), CellPosition(0, 1), CellPosition(0, 2)),
                    minesweeperInfo = minesweeperInfo
                )
            }
        }
        val minesweeper = Minesweeper.of(minesweeperInfo, minePositionsFactory)

        assertAll(
            { assertThat(minesweeper[CellPosition(0, 0)]).isEqualTo(Cell.Mine) },
            { assertThat(minesweeper[CellPosition(0, 1)]).isEqualTo(Cell.Mine) },
            { assertThat(minesweeper[CellPosition(0, 2)]).isEqualTo(Cell.Mine) },
            { assertThat(minesweeper[CellPosition(1, 0)]).isEqualTo(Cell.Land.TWO) },
            { assertThat(minesweeper[CellPosition(1, 1)]).isEqualTo(Cell.Land.THREE) },
            { assertThat(minesweeper[CellPosition(1, 2)]).isEqualTo(Cell.Land.TWO) },
            { assertThat(minesweeper[CellPosition(1, 3)]).isEqualTo(Cell.Land.ONE) },
            { assertThat(minesweeper[CellPosition(0, 3)]).isEqualTo(Cell.Land.ONE) }
        )
    }
}
