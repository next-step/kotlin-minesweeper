package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinesweeperCellTest {

    @Test
    fun `지뢰 cell 확인 - 지뢰 없음`() {
        val minesweeperCell = MinesweeperCell()
        assertThat(minesweeperCell.isMine).isEqualTo(false)
    }

    @Test
    fun `지뢰 cell 확인 - 지뢰 셋팅`() {
        val minesweeperCell = MinesweeperCell()
        minesweeperCell.setMine()
        assertThat(minesweeperCell.isMine).isEqualTo(true)
    }
}
