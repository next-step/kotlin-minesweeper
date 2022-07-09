package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OutOfBoundaryValidatorTest {
    @Test
    fun `OutOfBoundaryValidator를 구현하여 지뢰 찾기 게임판의 범위를 넘어서는 좌표가 있는지 검사할 수 있다`() {
        val minePositions = listOf(
            CellPosition(0, 0),
            CellPosition(0, 1),
            CellPosition(11, 1)
        )
        assertThrows<IllegalArgumentException> {
            OutOfBoundaryValidator.validate(
                minePositions = minePositions,
                minesweeperInfo = MinesweeperInfo(10, 10, 3)
            )
        }
    }
}
