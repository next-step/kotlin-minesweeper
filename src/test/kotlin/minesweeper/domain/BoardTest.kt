package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BoardTest {

    @Test
    fun `지뢰찾기 보드 생성이 정상적으로 된다`() {
        assertDoesNotThrow {
            Board(
                height = 3,
                width = 3,
                mineCount = 2,
            )
        }
    }

    @CsvSource(
        "0, 0, 0",
        "0, 0, -1",
        "0, -1, 0",
        "-1, 0, 0",
        "-1, -1, -1",
    )
    @ParameterizedTest
    fun `지뢰찾기 보드에 0 이하의 값이 들어오면 예외가 발생한다`(rows: Int, columns: Int, mineCount: Int) {
        assertThrows<IllegalArgumentException> {
            Board(
                height = rows,
                width = columns,
                mineCount = mineCount,
            )
        }
    }

    @Test
    fun `지뢰찾기 보드에 지뢰 개수가 전체 칸의 개수보다 크면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Board(
                height = 3,
                width = 3,
                mineCount = 10,
            )
        }
    }

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `지뢰찾기 보드를 생성하면 지뢰가 심어진다`(mineCount: Int) {
        val board = Board(
            height = 5,
            width = 5,
            mineCount = mineCount,
        )

        val expected = board.countMines()
        mineCount shouldBe expected
    }

    @Test
    fun `지뢰찾기 지뢰 개수를 정상적으로 센다`() {
        // 아래와 같은 모양으로 지뢰를 심는다.
        // X . .
        // . . X
        // X X .

        val board = Board(
            height = 3,
            width = 3,
            mineCount = 2,
            minePlacementStrategy = FixedMinePlacementStrategy(listOf(0, 5, 6, 7)),
        ).board

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
