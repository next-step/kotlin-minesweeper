package model.board

import model.Position
import model.Positions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardFactoryTest {
    private val sut = BoardFactory()

    @Test
    fun `인자로 들어온 값들에 알맞은 Board 생성`() {
        val boardSize = BoardSize(5, 4)
        val positions = Positions(
            Position.get(1, 2)
        )

        val board = sut.create(boardSize, positions)

        board.rows.forEachIndexed { index, row ->
            assertThat(row.width).isEqualTo(4)
            if (index == 1) {
                assertThat(row[2].isMine).isTrue()
            }
        }
        assertThat(board.rows.size).isEqualTo(5)
    }
}
