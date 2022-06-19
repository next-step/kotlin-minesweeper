package domain

import FixtureBuilder.Companion.MineAllocator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinesweeperTest {

    @Test
    fun `지뢰가 있는 자리인지 확인`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 1
        val allocatedMine = setOf(0)

        val mineAllocator = MineAllocator(allocatedMine)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocator)

        assertThat(minesweeper.board[0][0]).isEqualTo('*')
    }

    @Test
    fun `지뢰가 없는 자리인지 확인`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 1
        val allocatedMine = setOf(0)

        val mineAllocator = MineAllocator(allocatedMine)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocator)

        assertThat(minesweeper.board[2][2]).isEqualTo('B')
    }
}
