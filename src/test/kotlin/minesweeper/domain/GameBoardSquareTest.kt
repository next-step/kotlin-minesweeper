package minesweeper.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class GameBoardSquareTest {

    @ParameterizedTest
    @EnumSource(SquareValueType::class)
    fun `게임판 사각형 1개는 지뢰 혹은 빈값을 가진다`(squareValueType: SquareValueType) {
        // given
        val gameBoardSquare = GameBoardSquare(squareValueType)
        val isValueMine = squareValueType == SquareValueType.MINE

        // when
        val actual = gameBoardSquare.isMine()

        // then
        Assertions.assertThat(actual).isEqualTo(isValueMine)
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
