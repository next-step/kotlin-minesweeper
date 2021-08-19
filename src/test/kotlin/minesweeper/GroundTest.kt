package minesweeper

import minesweeper.domain.Ground
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GroundTest {

    @ParameterizedTest
    @CsvSource(value = ["-10, 2", "0, 5", "2, 0", "5, -10"])
    fun `높이 및 너비가 자연수가 아닌경우 예외처리된다`(height : Int, vertical: Int) {
        assertThatIllegalArgumentException().isThrownBy {
            Ground(height, vertical)
        }
    }
}