package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MinePositionsFactoryTest {
    private val minesweeperInfo = MinesweeperInfo(10, 10, 3)

    @Test
    fun `MinePositionsFactory를 구현하여 지뢰가 있는 좌표의 리스트를 구할 수 있다`() {
        val minePositions = listOf(CellPosition(0, 0), CellPosition(0, 1), CellPosition(0, 2))
        assertThat(getMinePositionsFactory(minePositions).create(minesweeperInfo)).isEqualTo(minePositions)
    }

    @Test
    fun `생성된 지뢰 목록에 중복이 있으면 IllegalArgumentException이 발생한다`() {
        val minePositions = listOf(CellPosition(0, 0), CellPosition(0, 0), CellPosition(0, 2))
        assertThrows<IllegalArgumentException> { getMinePositionsFactory(minePositions).create(minesweeperInfo) }
    }

    @Test
    fun `사용자가 입력한 지뢰 개수와 생성된 지뢰 개수가 다르면 IllegalArgumentException이 발생한다`() {
        val minePositions = listOf(CellPosition(0, 0), CellPosition(0, 1))
        assertThrows<IllegalArgumentException> { getMinePositionsFactory(minePositions).create(minesweeperInfo) }
    }

    @Test
    fun `사용자가 입력한 범위를 벗어나는 지뢰가 있으면 IllegalArgumentException이 발생한다`() {
        val minePositions = listOf(CellPosition(20, 0), CellPosition(0, 0), CellPosition(0, 2))
        assertThrows<IllegalArgumentException> { getMinePositionsFactory(minePositions).create(minesweeperInfo) }
    }

    private fun getMinePositionsFactory(minePositions: List<CellPosition>): MinePositionsFactory {
        return object : MinePositionsFactory() {
            override fun getMinePositionsFrom(minesweeperInfo: MinesweeperInfo): List<CellPosition> {
                return minePositions
            }
        }
    }
}
