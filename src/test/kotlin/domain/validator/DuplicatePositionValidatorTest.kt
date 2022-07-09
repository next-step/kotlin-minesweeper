package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DuplicatePositionValidatorTest {
    @Test
    fun `DuplicatePositionValidator를 구현하여 중복된 좌표가 있는지 검사할 수 있다`() {
        val minePositions = listOf(
            CellPosition(0, 0),
            CellPosition(0, 0),
            CellPosition(0, 2)
        )
        assertThrows<IllegalArgumentException> {
            DuplicatePositionValidator.validate(
                minePositions = minePositions,
                minesweeperInfo = MinesweeperInfo(10, 10, 3)
            )
        }
    }
}
