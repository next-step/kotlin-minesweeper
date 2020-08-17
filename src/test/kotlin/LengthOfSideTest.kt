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

class LengthOfSide(length: Int) {

    init {
        require(length in NUMBER_RANGE) {
            "높이 혹은 너비는 10 - 30까지만 가능합니다."
        }
    }

    companion object {
        private val NUMBER_RANGE = 10..30
    }
}
