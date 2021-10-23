package model

import model.CellType.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import view.OutputView

internal class BoardTest {
    /*
    1 * 1 0
    1 1 1 0
    1 2 1 1
    * 2 * 1
     */
    private val board = Board(
        Row(
            Cell(Plain, Position(0, 0, MAX)),
            Cell(Mine, Position(1, 0, MAX)),
            Cell(Plain, Position(2, 0, MAX)),
            Cell(Plain, Position(3, 0, MAX))
        ),
        Row(
            Cell(Plain, Position(0, 1, MAX)),
            Cell(Plain, Position(1, 1, MAX)),
            Cell(Plain, Position(2, 1, MAX)),
            Cell(Plain, Position(3, 1, MAX))
        ),
        Row(
            Cell(Plain, Position(0, 2, MAX)),
            Cell(Plain, Position(1, 2, MAX)),
            Cell(Plain, Position(2, 2, MAX)),
            Cell(Plain, Position(3, 2, MAX))
        ),
        Row(
            Cell(Mine, Position(0, 3, MAX)),
            Cell(Plain, Position(1, 3, MAX)),
            Cell(Mine, Position(2, 3, MAX)),
            Cell(Plain, Position(3, 3, MAX))
        )
    )

    @ParameterizedTest
    @MethodSource("positionProvider")
    fun `open`(position: Position) {
        board.open(position)
    }

    @Test
    fun `결과 테스트`() {
        assertThat(board.over()).isFalse()
        board.open(Position(0, 0, MAX))
        assertThat(board.over()).isFalse()
        board.open(Position(1, 0, MAX))
        assertThat(board.over()).isTrue()
        assertThat(board.winning()).isFalse()
        assertThat(board.lost()).isTrue()
    }

    companion object {
        private const val MAX = 3

        @JvmStatic
        fun positionProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Position(IndexWithMax(0, MAX), IndexWithMax(0, MAX))
                    )
                }
            )
        }
    }
}