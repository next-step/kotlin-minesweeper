package minesweeper

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GroundTest {

    @ParameterizedTest
    @ValueSource(ints = [-10, -5, 0])
    fun `높이가 자연수가 아닌경우 예외처리된다`(input : Int) {
        assertThatIllegalArgumentException().isThrownBy {
            Ground(input)
        }
    }
}