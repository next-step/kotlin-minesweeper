package minesweeper.domain

import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineCountTest {

    @DisplayName("지뢰는 1개 이상이어야 합니다")
    @Test
    fun minimumSize() {
        AssertionsForClassTypes.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                MineCount(0)
            }
    }
}
