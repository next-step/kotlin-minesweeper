import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RandomGeneratorTest {
    @ValueSource(ints = [0, 1, 2, 3, 10])
    @ParameterizedTest
    fun `지정한 갯수의 좌표를 생성할 수 있다`(count: Int) {
        val points = RandomGenerator.points(4, 4, count)
        points.size shouldBe count
    }

    @ValueSource(ints = [0, -1, -2, -3, -10])
    @ParameterizedTest
    fun `너비 값이 0보다 작거나 같으면 IllegalArgumentException 발생`(width: Int) {
        shouldThrow<IllegalArgumentException> {
            RandomGenerator.points(width, 1, 1)
        }
    }

    @ValueSource(ints = [0, -1, -2, -3, -10])
    @ParameterizedTest
    fun `높이 값이 0보다 작거나 같으면 IllegalArgumentException 발생`(height: Int) {
        shouldThrow<IllegalArgumentException> {
            RandomGenerator.points(1, height, 1)
        }
    }

    @ValueSource(ints = [-1, -2, -3, -10])
    @ParameterizedTest
    fun `좌표의 개수가 0보다 작거나 width * height 보다 크면 IllegalArgumentException 발생`(count: Int) {
        shouldThrow<IllegalArgumentException> {
            RandomGenerator.points(3, 3, count)
        }
    }
}
