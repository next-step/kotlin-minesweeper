package model.board

import model.Position
import model.Positions
import model.board.State.COVERED
import model.board.Contents.MINE
import model.board.Contents.ONE
import model.board.Contents.TWO
import model.board.Contents.ZERO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource

internal class BoardTest {
    @Test
    fun `생성자 인자로 받은 row list 가 변경되어도 Board 에 영향 주지 않는다`() {
        val rows = mutableListOf(
            Row(Cell.get(MINE, COVERED))
        )

        val board = Board(rows)
        assertThat(board.rows.size).isEqualTo(rows.size)

        rows.add(Row(Cell.get(MINE, COVERED)))
        assertThat(board.rows.size).isNotEqualTo(rows.size)
    }

    @Test
    fun `입력받은 row 의 길이가 다르면 예외 발생`() {
        assertThrows<IllegalArgumentException> {
            Board(
                Row(Cell.get(MINE, COVERED)),
                Row(Cell.get(MINE, COVERED), Cell.get(MINE, COVERED))
            )
        }
    }

    @ParameterizedTest
    @EmptySource
    fun `빈 rows 로는 Board 생성 불가`(rows: List<Row>) {
        assertThrows<IllegalArgumentException> {
            Board(rows)
        }
    }

    @ParameterizedTest
    @MethodSource("uncoverProvider")
    fun `특정 위치 cell 을 open 할 때 contents 결정`(board: Board, position: Position, contents: Contents) {
        board.uncover(position)
        assertThat(board.rows[position.heightIndex][position.widthIndex].contents).isEqualTo(contents)
    }

    @ParameterizedTest
    @MethodSource("uncoverPositionsProvider")
    fun `zero cell 을 open 하면 인접한 mine 이 아닌 셀 모두 open`(board: Board, position: Position, shouldOpen: List<Position>, shouldNotOpen: List<Position>) {
        board.uncover(position)

        shouldOpen.forEach {
            assertThat(board.rows[it.heightIndex][it.widthIndex].isUncovered).isTrue()
        }

        shouldNotOpen.forEach {
            assertThat(board.rows[it.heightIndex][it.widthIndex].isCovered).isTrue()
        }
    }

    companion object {
        /*
         * M 1 0 0
         * 1 2 1 1
         * 0 1 M 1
         * 0 1 1 1
         */
        private val board = BoardFactory().create(
            BoardSize(4, 4),
            Positions(Position.get(0, 0), Position.get(2, 2))
        )

        @JvmStatic
        fun uncoverProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        board,
                        Position.get(0, 3),
                        ZERO
                    )
                },
                Arguments {
                    arrayOf(
                        board,
                        Position.get(1, 0),
                        ONE
                    )
                },
                Arguments {
                    arrayOf(
                        board,
                        Position.get(0, 0),
                        MINE
                    )
                },
                Arguments {
                    arrayOf(
                        board,
                        Position.get(1, 1),
                        TWO
                    )
                }
            )
        }

        @JvmStatic
        fun uncoverPositionsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        board,
                        Position.get(0, 3),
                        listOf(
                            Position.get(0, 2),
                            Position.get(1, 1)
                        ),
                        listOf(
                            Position.get(1, 0),
                            Position.get(0, 0),
                            Position.get(2, 3)
                        )
                    )
                },
                Arguments {
                    arrayOf(
                        board,
                        Position.get(3, 1),
                        listOf(
                            Position.get(3, 1)
                        ),
                        listOf(
                            Position.get(3, 0)
                        )
                    )
                }
            )
        }
    }
}
