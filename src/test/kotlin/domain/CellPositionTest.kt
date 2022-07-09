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

    @Test
    fun `isInBoundaryOf를 통해 해당 좌표가 지뢰찾기 범위 안에 있는지 확인할 수 있다`() {
        val minesweeperInfo = MinesweeperInfo(10, 15, 1)

        assertAll(
            { assertThat(CellPosition(0, 2).isInBoundaryOf(minesweeperInfo)).isTrue },
            { assertThat(CellPosition(11, 2).isInBoundaryOf(minesweeperInfo)).isFalse },
            { assertThat(CellPosition(0, 18).isInBoundaryOf(minesweeperInfo)).isFalse },
            { assertThat(CellPosition(-5, 3).isInBoundaryOf(minesweeperInfo)).isFalse },
            { assertThat(CellPosition(2, -10).isInBoundaryOf(minesweeperInfo)).isFalse }
        )
    }
}
