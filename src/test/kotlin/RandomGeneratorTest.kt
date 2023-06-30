import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RandomGeneratorTest {
    @ValueSource(ints = [0, -1, -2, -3, -10])
    @ParameterizedTest
    fun `너비 값이 0보다 작거나 같으면 IllegalArgumentException 발생`(width: Int) {
        shouldThrow<IllegalArgumentException> {
            RandomGenerator.point(width, 1)
        }
    }

    @ValueSource(ints = [0, -1, -2, -3, -10])
    @ParameterizedTest
    fun `높이 값이 0보다 작거나 같으면 IllegalArgumentException 발생`(height: Int) {
        shouldThrow<IllegalArgumentException> {
            RandomGenerator.point(1, height)
        }
    }
}
