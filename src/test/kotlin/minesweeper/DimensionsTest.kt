package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class DimensionsTest {
    @Test
    fun `모든 위치를 반환한다`() {
        val dimensions = Dimensions(2, 2, 1)

        assertThat(dimensions.allPositions()).containsExactly(
            Position(0, 0),
            Position(1, 0),
            Position(0, 1),
            Position(1, 1),
        )
    }

    @Test
    fun `폭은 0보다 커야 한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Dimensions(0, 1, 1)
        }.withMessage("폭은 0보다 커야 합니다.")
    }

    @Test
    fun `높이는 0보다 커야 한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Dimensions(1, 0, 1)
        }.withMessage("높이는 0보다 커야 합니다.")
    }

    @Test
    fun `지뢰 갯수는 0보다 커야 한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Dimensions(1, 1, 0)
        }.withMessage("지뢰 갯수는 0보다 커야 합니다.")
    }

    @Test
    fun `지뢰 갯수는 최대치 보다 작아야 한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Dimensions(1, 1, 2)
        }.withMessage("지뢰 갯수는 최대치 보다 작아야 합니다")
    }
}