package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellPositionTest {
    @Test
    fun `CellPosition은 특정 칸의 행 위치와 열 위치를 보관한다`() {
        val cellPosition = CellPosition(10, 16)

        assertAll(
            { assertThat(cellPosition.row).isEqualTo(10) },
            { assertThat(cellPosition.column).isEqualTo(16) }
        )
    }

    @Test
    fun `전체 cell 중의 순번과 열의 개수를 받아 위치를 구할 수 있다`() {
        val cellPosition = CellPosition.from(138, 15)

        assertAll(
            { assertThat(cellPosition.row).isEqualTo(9) },
            { assertThat(cellPosition.column).isEqualTo(3) }
        )
    }
}
