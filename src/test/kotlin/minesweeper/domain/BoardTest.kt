package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardTest {

    @Test
    fun `지뢰찾기 보드 생성이 정상적으로 된다`() {
        val boardConfig = BoardConfig(
            height = 3,
            width = 3,
            mineCount = 2,
        )

        assertDoesNotThrow {
            Board(boardConfig)
        }
    }

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `지뢰찾기 보드를 생성하면 지뢰가 심어진다`(mineCount: Int) {
        val boardConfig = BoardConfig(
            height = 5,
            width = 5,
            mineCount = mineCount,
        )

        val board = Board(boardConfig)

        val expected = board.countMines()
        mineCount shouldBe expected
    }

    @Test
    fun `지뢰찾기 지뢰 개수를 정상적으로 센다`() {
        // 아래와 같은 모양으로 지뢰를 심는다.
        // X . .
        // . . X
        // X X .
        val boardConfig = BoardConfig(
            height = 3,
            width = 3,
            mineCount = 4,
            minePlacementStrategy = FixedMinePlacementStrategy(listOf(0, 5, 6, 7)),
        )

        val board = Board(boardConfig).board

        board[0][0].shouldBeInstanceOf<Mine>()
        board[0][1].shouldBeInstanceOf<Land>()
        board[0][2].shouldBeInstanceOf<Land>()
        board[1][0].shouldBeInstanceOf<Land>()
        board[1][1].shouldBeInstanceOf<Land>()
        board[1][2].shouldBeInstanceOf<Mine>()
        board[2][0].shouldBeInstanceOf<Mine>()
        board[2][1].shouldBeInstanceOf<Mine>()
        board[2][2].shouldBeInstanceOf<Land>()

        (board[0][1] as Land).adjacentMines shouldBe 2
        (board[0][2] as Land).adjacentMines shouldBe 1
        (board[1][0] as Land).adjacentMines shouldBe 3
        (board[1][1] as Land).adjacentMines shouldBe 4
        (board[2][2] as Land).adjacentMines shouldBe 2
    }
}
