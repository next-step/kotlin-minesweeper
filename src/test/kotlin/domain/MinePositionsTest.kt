package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MinePositionsTest {
    @Test
    fun `MinePositions는 생성되어야 할 지뢰의 좌표 목록을 보관한다`() {
        val positions = listOf(CellPosition(0, 0), CellPosition(0, 1))
        val minePositions = MinePositions.of(
            positions = positions,
            minesweeperInfo = MinesweeperInfo(10, 10, 2)
        )

        assertThat(minePositions.value).isEqualTo(positions)
    }

    @Test
    fun `중복된 좌표가 있을 경우 IllegalArgumentException이 발생한다`() {
        val positions = listOf(
            CellPosition(0, 0),
            CellPosition(0, 0),
            CellPosition(0, 2)
        )
        assertThrows<IllegalArgumentException> {
            MinePositions.of(
                positions = positions,
                minesweeperInfo = MinesweeperInfo(10, 10, 3)
            )
        }
    }

    @Test
    fun `지뢰 좌표 목록의 크기가 사용자가 입력한 지뢰 개수와 일치하지 않을 경우 IllegalArgumentException이 발생한다`() {
        val positions = listOf(
            CellPosition(0, 0),
            CellPosition(0, 2)
        )
        assertThrows<IllegalArgumentException> {
            MinePositions.of(
                positions = positions,
                minesweeperInfo = MinesweeperInfo(10, 10, 3)
            )
        }
    }

    @Test
    fun `지뢰 찾기 게임판의 범위를 넘어서는 좌표가 있을 경우 IllegalArgumentException이 발생한다`() {
        val positions = listOf(
            CellPosition(0, 0),
            CellPosition(0, 1),
            CellPosition(11, 1)
        )
        assertThrows<IllegalArgumentException> {
            MinePositions.of(
                positions = positions,
                minesweeperInfo = MinesweeperInfo(10, 10, 3)
            )
        }
    }
}
