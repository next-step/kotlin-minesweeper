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
                    it.value.size == minesweeperInfo.columnCount
                }
            }
        )
    }
}
