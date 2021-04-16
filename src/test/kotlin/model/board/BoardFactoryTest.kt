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
            Position.get(1, 2),
            Position.get(3, 3)
        )

        val board = sut.create(boardSize, positions)

        assertThat(board.height).isEqualTo(boardSize.height)
        assertThat(board.width).isEqualTo(boardSize.width)
        positions.forEach {
            assertThat(board.getCell(it).contents).isEqualTo(Contents.MINE)
        }
    }
}
