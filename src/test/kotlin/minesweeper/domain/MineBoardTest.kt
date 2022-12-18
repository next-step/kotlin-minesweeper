package minesweeper.domain

import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineBoardTest {

    @DisplayName("지뢰 개수는 보드 크기보다 작거나 같아야 합니다.")
    @Test
    fun minimumMineCount() {
        AssertionsForClassTypes.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                MineBoard(RowCount(10), ColumnCount(10), MineCount(101))
            }
    }
}
