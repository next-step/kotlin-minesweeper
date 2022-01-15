package mine_tdd

import mine_tdd.Board.Companion.findMineCount
import mine_tdd.cell.MineCell
import mine_tdd.cell.Position
import mine_tdd.cell.Position.Companion.findNearPosition
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `Position(0,0)의 주변 Position 리스트를 가져온다`() {
        val position = Position(0, 0)
        val expectList = listOf(
            Position(0, 1),
            Position(1, 0),
            Position(1, 1),
        )

        val nearPositions = position.findNearPosition()

        Assertions.assertThat(nearPositions.size).isEqualTo(3)
        Assertions.assertThat(nearPositions).isEqualTo(expectList)
    }

    @Test
    fun `Positiion(1,1) 주변 Position의 지뢰 개수를 구할 수 있다`() {
        val position = Position(1, 1)
        val mineCellList = listOf(
            MineCell(Position(0, 0)),
            MineCell(Position(1, 0)),
            MineCell(Position(4, 0)),
        )

        val count = position.findMineCount(mineCellList)

        Assertions.assertThat(count).isEqualTo(2)
    }

    @Test
    fun `Position()에는 음수를 입력할 수 없다`() {
        val x = -1
        val y = 0

        assertThrows<IllegalArgumentException> { Position(x, y) }
    }
}
