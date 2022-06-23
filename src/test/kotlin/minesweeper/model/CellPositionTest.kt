package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("셀 위치 테스트")
class CellPositionTest {

    @Test
    fun `주변 셀 위치 찾는 기능이 정상 동작`() {
        // given
        val cellPosition = CellPosition.of(1, 1)

        // when
        val surroundingPositions = cellPosition.findSurroundingCellPositions()

        // then
        val expected = setOf(
            CellPosition.of(0, 0),
            CellPosition.of(1, 0),
            CellPosition.of(2, 0),
            CellPosition.of(1, 0),
            CellPosition.of(1, 2),
            CellPosition.of(2, 0),
            CellPosition.of(2, 1),
            CellPosition.of(2, 2),
        )

        assertAll(
            "find surrounding cell positions test",
            { assertThat(surroundingPositions.size).isEqualTo(8) },
            { assertThat(surroundingPositions.containsAll(expected)).isTrue }
        )
    }

    @Test
    fun `셀 위치 비교 기능이 정상 동작`() {
        // given
        val `p(0,0)` = CellPosition.of(0, 0)
        val `p(1,0)` = CellPosition.of(1, 0)
        val `p(0,1)` = CellPosition.of(0, 1)
        val `p2(0,0)` = CellPosition.of(0, 0)

        // when, then
        assertAll(
            "compare cell position test",
            { assertThat(`p(0,0)`.compareTo(`p(1,0)`)).isEqualTo(-1) },
            { assertThat(`p(1,0)`.compareTo(`p(0,0)`)).isEqualTo(1) },
            { assertThat(`p(0,0)`.compareTo(`p(0,1)`)).isEqualTo(-1) },
            { assertThat(`p(0,1)`.compareTo(`p(0,0)`)).isEqualTo(1) },
            { assertThat(`p(1,0)`.compareTo(`p(0,1)`)).isEqualTo(-1) },
            { assertThat(`p(0,1)`.compareTo(`p(1,0)`)).isEqualTo(1) },
            { assertThat(`p(0,1)`.compareTo(`p(1,0)`)).isEqualTo(1) },
            { assertThat(`p(0,0)`.compareTo(`p2(0,0)`)).isEqualTo(0) }
        )
    }
}
