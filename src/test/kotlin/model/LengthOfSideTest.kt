package model

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LengthOfSideTest {

    @ParameterizedTest
    @ValueSource(ints = [9, 31])
    fun `높이 혹은 너비 값이 허용되는 범위에 없는 경우`(input: Int) {
        assertThatIllegalArgumentException().isThrownBy {
            LengthOfSide(input)
        }
    }
}
