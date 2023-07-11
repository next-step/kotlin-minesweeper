package minesweeper.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineLocationValidatorTest {
    @Test
    fun `지뢰의 위치가 중복된 곳이 있는지 확인한다`() {
        // given
        val height = 10
        val width = 10
        val mineLocationLists = listOf(
            MineLocation(0, 0),
            MineLocation(1, 6),
            MineLocation(6, 3),
            MineLocation(9, 9)
        )
        val board: Array<Array<GameBoardSquare>> =
            Array(height) { Array(width) { GameBoardSquare(SquareValueType.EMPTY) } }

        // when
        mineLocationLists.forEach {
            val (x, y) = it
            board[y][x] = GameBoardSquare(SquareValueType.MINE)
        }
        val listBoard = board.map { it.toList() }
        val actual = MineLocationValidator.validateMineLocation(listBoard, MineLocation(0, 0))

        // then
        Assertions.assertThat(actual).isEqualTo(true)
    }

    @ParameterizedTest
    @CsvSource("-1,0", "0,-1", "11,0", "0,11", delimiter = ',')
    fun `게임판의 인덱스를 벗어난 지뢰의 위치를 생성하면 IllegaArgumentException을 throw 한다`(x: Int, y: Int) {
        // given
        val height = 10
        val width = 10
        val board: Array<Array<GameBoardSquare>> =
            Array(height) { Array(width) { GameBoardSquare(SquareValueType.EMPTY) } }

        // when
        val listBoard = board.map { it.toList() }

        // then
        assertThrows<IllegalArgumentException> {
            MineLocationValidator.validateMineLocation(
                listBoard,
                MineLocation(x, y)
            )
        }
    }
}
