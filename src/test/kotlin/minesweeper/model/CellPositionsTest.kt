package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("셀 위치 컬렉션 테스트")
class CellPositionsTest {

    @Test
    fun `셀 위치 컬렉션이 정상적으로 생성`() {
        // given, when
        val cellPositions = CellPositions.of(2, 2)

        // then
        val expected = listOf(
            CellPosition.of(0, 0),
            CellPosition.of(1, 0),
            CellPosition.of(0, 1),
            CellPosition.of(1, 1),
        )
        assertThat(cellPositions).isEqualTo(CellPositions(expected))
    }
}
