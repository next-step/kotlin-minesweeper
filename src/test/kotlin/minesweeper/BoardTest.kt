package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardTest {
    @Test
    fun `지뢰 갯수 지정 가능하다`() {
        val fixture =
            testFixture(
                mutableMapOf(
                    Position(0, 0) to CellType.MINE,
                    Position(0, 1) to CellType.EMPTY,
                    Position(0, 2) to CellType.MINE,
                ),
            )
        val board = Board.initializeBoard(Dimensions(3, 3, 2), fixture)

        assertAll(
            { assertThat(board.checkMine(Position(0, 0))).isTrue() },
            { assertThat(board.checkMine(Position(0, 1))).isFalse() },
            { assertThat(board.checkMine(Position(0, 2))).isTrue() },
        )
    }

    @Test
    fun `지뢰 갯수가 일치하지 않으면 예외가 발생한다`() {
        val fixture =
            testFixture(
                mutableMapOf(
                    Position(0, 0) to CellType.MINE,
                    Position(1, 0) to CellType.EMPTY,
                    Position(2, 0) to CellType.EMPTY,
                ),
            )

        assertThatIllegalArgumentException()
            .isThrownBy { Board.initializeBoard(Dimensions(3, 1, 2), fixture) }
            .withMessage("지뢰 갯수가 일치하지 않습니다.")
    }

    private fun testFixture(data: MutableMap<Position, CellType>) =
        object : PositionProvider {
            override fun provide(dimensions: Dimensions): MutableMap<Position, CellType> {
                return data
            }
        }
}
