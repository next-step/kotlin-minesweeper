package minesweeper.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameBoardSquareTest {

    @Test
    fun `게임판 사각형 1개는 처음 생성시에 빈값을 가진다`() {
        // given
        val gameBoardSquare = GameBoardSquare(SquareValueType.EMPTY)

        // when
        val actual = gameBoardSquare.isMine()

        // then
        Assertions.assertThat(actual).isEqualTo(false)
    }

    @Test
    fun `게임판 사각형 1개는 처음 생성할때 지뢰가 되면 안된다`() {
        assertThrows<IllegalArgumentException> {
            GameBoardSquare(SquareValueType.MINE)
        }
    }

    @Test
    fun `게임판 내부의 값을 지뢰로 변경한다`() {
        // given
        val boardSquare = GameBoardSquare(SquareValueType.EMPTY)

        // when
        boardSquare.insertMine()
        val actual = boardSquare.isMine()

        // then
        Assertions.assertThat(actual).isTrue()
    }
}
