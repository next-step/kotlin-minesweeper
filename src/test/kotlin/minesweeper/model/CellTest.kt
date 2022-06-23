package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("셀 테스트")
class CellTest {

    @Test
    fun `주변 8개 지뢰의 개수를 찾는 기능이 정상 동작`() {
        // given
        // * 1 0
        // 2 2 1
        // 1 * 1
        val `p(0,0)` = Cell.mine(CellPosition.of(0, 0))
        val `p(1,0)` = Cell.nonMine(CellPosition.of(1, 0))
        val `p(2,0)` = Cell.nonMine(CellPosition.of(2, 0))
        val `p(0,1)` = Cell.nonMine(CellPosition.of(0, 1))
        val `p(1,1)` = Cell.nonMine(CellPosition.of(1, 1))
        val `p(2,1)` = Cell.nonMine(CellPosition.of(2, 1))
        val `p(0,2)` = Cell.nonMine(CellPosition.of(0, 2))
        val `p(1,2)` = Cell.mine(CellPosition.of(1, 2))
        val `p(2,2)` = Cell.nonMine(CellPosition.of(2, 2))

        val cells = Cells(
            listOf(
                `p(0,0)`, `p(1,0)`, `p(2,0)`,
                `p(0,1)`, `p(1,1)`, `p(2,1)`,
                `p(0,2)`, `p(1,2)`, `p(2,2)`
            )
        )

        // when, then
        assertAll(
            "find surrounding mine count sum test",
            { assertThat(`p(1,0)`.findSurroundingMineCountSum(cells)).isEqualTo(1) },
            { assertThat(`p(2,0)`.findSurroundingMineCountSum(cells)).isEqualTo(0) },
            { assertThat(`p(0,1)`.findSurroundingMineCountSum(cells)).isEqualTo(2) },
            { assertThat(`p(1,1)`.findSurroundingMineCountSum(cells)).isEqualTo(2) },
            { assertThat(`p(2,1)`.findSurroundingMineCountSum(cells)).isEqualTo(1) },
            { assertThat(`p(0,2)`.findSurroundingMineCountSum(cells)).isEqualTo(1) },
            { assertThat(`p(2,2)`.findSurroundingMineCountSum(cells)).isEqualTo(1) }
        )
    }
}
