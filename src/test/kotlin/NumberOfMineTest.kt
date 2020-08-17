import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NumberOfMineTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 101])
    fun `지뢰 개수가 유효하지 않은 경우`(input: Int) {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))

        assertThatIllegalArgumentException().isThrownBy {
            NumberOfMine(input, boardSize)
        }
    }
}
