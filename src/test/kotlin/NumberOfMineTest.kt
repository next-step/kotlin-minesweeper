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

class NumberOfMine(mineCount: Int, boardSize: BoardSize) {

    init {
        require(mineCount in MIN_MINE_NUMBER..boardSize.get()) {
            "지뢰 개수는 전체 크기를 초과할 수 없습니다."
        }
    }

    companion object {
        private const val MIN_MINE_NUMBER = 1
    }
}
