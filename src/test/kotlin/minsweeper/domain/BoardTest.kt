package minsweeper.domain

import minsweeper.getFixtureBoard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

    @Test
    fun `Board를 오픈할 때 일반 셀에 지뢰수가 1이상인 경우 해당 셀만 오픈되어야 한다`() {
        // given
        val board = getFixtureBoard()

        // when
        board.open(Coordinate.of(0, 1))

        // then
        assertThat(board.boardLines.lines[0].cells[0].isOpened).isFalse()
        assertThat(board.boardLines.lines[0].cells[1].isOpened).isTrue()
        assertThat(board.boardLines.lines[0].cells[2].isOpened).isFalse()
        assertThat(board.boardLines.lines[1].cells[0].isOpened).isFalse()
        assertThat(board.boardLines.lines[1].cells[1].isOpened).isFalse()
        assertThat(board.boardLines.lines[1].cells[2].isOpened).isFalse()
        assertThat(board.boardLines.lines[2].cells[0].isOpened).isFalse()
        assertThat(board.boardLines.lines[2].cells[1].isOpened).isFalse()
        assertThat(board.boardLines.lines[2].cells[2].isOpened).isFalse()
    }

    @Test
    fun `Board를 오픈할 때 일반 셀에 지뢰수가 0이면 근접한 셀은 오픈되어야 한다`() {
        // given
        val board = getFixtureBoard()

        // when
        board.open(Coordinate.of(0, 0))

        // then
        assertThat(board.boardLines.lines[0].cells[0].isOpened).isTrue()
        assertThat(board.boardLines.lines[0].cells[1].isOpened).isTrue()
        assertThat(board.boardLines.lines[0].cells[2].isOpened).isFalse()
        assertThat(board.boardLines.lines[1].cells[0].isOpened).isTrue()
        assertThat(board.boardLines.lines[1].cells[1].isOpened).isTrue()
        assertThat(board.boardLines.lines[1].cells[2].isOpened).isTrue()
        assertThat(board.boardLines.lines[2].cells[0].isOpened).isTrue()
        assertThat(board.boardLines.lines[2].cells[1].isOpened).isTrue()
        assertThat(board.boardLines.lines[2].cells[2].isOpened).isTrue()
    }

    companion object {
        @JvmStatic
        fun provideCoordinates(): List<Coordinate> = listOf(
            Coordinate.of(10, 9),
            Coordinate.of(9, 10),
            Coordinate.of(10, 10),
        )
    }

}