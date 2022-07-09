package domain.validator

import domain.CellPosition
import domain.MinesweeperInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MinePositionsValidatorTest {
    @Test
    fun `MinePositionsValidator를 구현하여 지뢰 좌표 목록이 특정 조건을 만족하는지 검사할 수 있다`() {
        val validator = object : MinePositionsValidator {
            override fun validate(minePositions: List<CellPosition>, minesweeperInfo: MinesweeperInfo) {
                require(minePositions.isNotEmpty())
            }
        }
        assertThrows<IllegalArgumentException> {
            validator.validate(
                minePositions = listOf(),
                minesweeperInfo = MinesweeperInfo(10, 10, 10)
            )
        }
    }
}
