import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MapGeneratorTest {
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    @ParameterizedTest
    fun `지뢰의 개수를 지정해서 맵을 생성할 수 있다`(mineCount: Int) {
        val width = 3
        val height = 3

        val map = MapGenerator.generate(width, height, mineCount)

        var count = 0
        map.field.forEach {
            it.forEach { tile ->
                if (tile is Mine) count++
            }
        }
        map.field.size shouldBe height
        map.field[0].size shouldBe width
        count shouldBe mineCount
    }

    @ValueSource(ints = [0, -1, -2, -10])
    @ParameterizedTest
    fun `높이가 0보다 작거나 같으면 IllegalArgumentException 발생`(height: Int) {
        shouldThrow<IllegalArgumentException> {
            MapGenerator.generate(1, height, 1)
        }
    }

    @ValueSource(ints = [0, -1, -2, -10])
    @ParameterizedTest
    fun `너비가 0보다 작거나 같으면 IllegalArgumentException 발생`(width: Int) {
        shouldThrow<IllegalArgumentException> {
            MapGenerator.generate(width, 1, 1)
        }
    }

    @ValueSource(ints = [-1, -2, -10, 10, 100])
    @ParameterizedTest
    fun `지뢰의 개수가 0 보다 작거나 width * height 보다 크면 IllegalArgumentException 발생`(mineCount: Int) {
        shouldThrow<IllegalArgumentException> {
            MapGenerator.generate(3, 3, mineCount)
        }
    }
}
