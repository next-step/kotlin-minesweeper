package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineCountValidatorTest {
    @Test
    fun `MineCountValidator를 구현하여 지뢰 좌표 목록의 크기가 사용자가 입력한 지뢰 개수와 일치하는지 검사할 수 있다`() {
        val minePositions = listOf(
            CellPosition(0, 0),
            CellPosition(0, 1)
        )
        assertThrows<IllegalArgumentException> {
            MineCountValidator.validate(
                minePositions = minePositions,
                minesweeperInfo = MinesweeperInfo(10, 10, 3)
            )
        }
    }
}
