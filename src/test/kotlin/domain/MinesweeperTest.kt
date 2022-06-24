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

    @Test
    fun `인근 지뢰의 개수가 0개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 1
        val minePlace = setOf(Place(11, PlaceType.MINE))

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[0][0].nearMineCount).isEqualTo(0)
    }

    @Test
    fun `인근 지뢰의 개수가 1개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 1
        val minePlace = setOf(Place(11, PlaceType.MINE))

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[1][1].nearMineCount).isEqualTo(1)
    }

    @Test
    fun `인근 지뢰의 개수가 2개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 2
        val minePlace = setOf(
            Place(11, PlaceType.MINE),
            Place(12, PlaceType.MINE),
        )

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[1][2].nearMineCount).isEqualTo(2)
    }

    @Test
    fun `인근 지뢰의 개수가 3개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 3
        val minePlace = setOf(
            Place(11, PlaceType.MINE),
            Place(12, PlaceType.MINE),
            Place(13, PlaceType.MINE),
        )

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[3][2].nearMineCount).isEqualTo(3)
    }

    @Test
    fun `인근 지뢰의 개수가 4개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 4
        val minePlace = setOf(
            Place(6, PlaceType.MINE),
            Place(11, PlaceType.MINE),
            Place(12, PlaceType.MINE),
            Place(13, PlaceType.MINE),
        )

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[1][2].nearMineCount).isEqualTo(4)
    }

    @Test
    fun `인근 지뢰의 개수가 5개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 5
        val minePlace = setOf(
            Place(6, PlaceType.MINE),
            Place(8, PlaceType.MINE),
            Place(11, PlaceType.MINE),
            Place(12, PlaceType.MINE),
            Place(13, PlaceType.MINE),
        )

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[1][2].nearMineCount).isEqualTo(5)
    }

    @Test
    fun `인근 지뢰의 개수가 6개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 6
        val minePlace = setOf(
            Place(1, PlaceType.MINE),
            Place(6, PlaceType.MINE),
            Place(8, PlaceType.MINE),
            Place(11, PlaceType.MINE),
            Place(12, PlaceType.MINE),
            Place(13, PlaceType.MINE),
        )

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[1][2].nearMineCount).isEqualTo(6)
    }

    @Test
    fun `인근 지뢰의 개수가 7개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 7
        val minePlace = setOf(
            Place(1, PlaceType.MINE),
            Place(2, PlaceType.MINE),
            Place(6, PlaceType.MINE),
            Place(8, PlaceType.MINE),
            Place(11, PlaceType.MINE),
            Place(12, PlaceType.MINE),
            Place(13, PlaceType.MINE),
        )

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[1][2].nearMineCount).isEqualTo(7)
    }

    @Test
    fun `인근 지뢰의 개수가 8개인 경우에 대한 테스트`() {
        val minesweeperWidth = 5
        val minesweeperHeight = 5
        val mineCount = 8
        val minePlace = setOf(
            Place(1, PlaceType.MINE),
            Place(2, PlaceType.MINE),
            Place(3, PlaceType.MINE),
            Place(6, PlaceType.MINE),
            Place(8, PlaceType.MINE),
            Place(11, PlaceType.MINE),
            Place(12, PlaceType.MINE),
            Place(13, PlaceType.MINE),
        )

        val mineAllocatorFixture = MineAllocatorFixture(minePlace)
        val minesweeper =
            Minesweeper(MinesweeperProperty(minesweeperWidth, minesweeperHeight, mineCount), mineAllocatorFixture)

        assertThat(minesweeper.board[1][2].nearMineCount).isEqualTo(8)
    }
}
