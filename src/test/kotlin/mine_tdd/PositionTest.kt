package mine_tdd

import mine_tdd.cell.Position
import mine_tdd.cell.Position.Companion.findNearPosition
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun `생성되지 않은 셀의 위치를 찾을 경우 null을 반환한다`() {
        val position = Position(6, 0)

        val board = Board.createBoard(Width(5), Height(5))

        Assertions.assertThat(board.findCell(position)).isNull()
    }

    @Test
    fun `Position(1,1)의 주변 Position 리스트를 가져온다`() {
        val position = Position(1, 1)
        val expectList = listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )

        val nearPositions = position.findNearPosition()

        Assertions.assertThat(nearPositions.size).isEqualTo(8)
        Assertions.assertThat(nearPositions).isEqualTo(expectList)
    }
}
