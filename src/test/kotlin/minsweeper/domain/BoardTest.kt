package minsweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class BoardTest {

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    fun `Board 사이즈에 벗어나는 Cell을 오픈하려고 하면 에러를 던져야 한다`(coordinate: Coordinate) {
        // given
        val boardSize = BoardSize(10, 10)
        val board = Board.of(boardSize, 1, BoardLinesGenerator(aroundMineCountJudge = AroundMineCountJudge()))

        // when
        val result = assertThrows<IllegalArgumentException> { board.open(coordinate) }

        // then
        assertThat(result.message).isEqualTo("Board 크기를 벗어날 수 없습니다")
    }

    companion object {
        @JvmStatic
        fun provideCoordinates(): List<Coordinate> = listOf(
            Coordinate(10, 9),
            Coordinate(9, 10),
            Coordinate(10, 10),
        )
    }

}