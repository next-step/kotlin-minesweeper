package minesweeper.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

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
}
