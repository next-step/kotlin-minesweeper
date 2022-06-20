package domain

import FixtureBuilder.Companion.MineAllocatorFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinesweeperTest {

    @Test
    fun `지뢰가 있는 자리인지 확인`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 1
        val place = setOf(Place(0, PlaceType.MINE))

        val mineAllocatorFixture = MineAllocatorFixture(place)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[0][0].placeType).isEqualTo(PlaceType.MINE)
    }

    @Test
    fun `지뢰가 없는 자리인지 확인`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 1
        val place = setOf(Place(0, PlaceType.NOT_MINE))

        val mineAllocatorFixture = MineAllocatorFixture(place)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[2][2].placeType).isEqualTo(PlaceType.NOT_MINE)
    }
}
