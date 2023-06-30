import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GameMapTest {
    @ValueSource(ints = [0, -1, -2, -10])
    @ParameterizedTest
    fun `너비가 0보다 작거나 같으면 IllegalArgumentException 발생`(height: Int) {
        shouldThrow<IllegalArgumentException> {
            GameMap.createEmptyMap(1, height)
        }
    }

    @ValueSource(ints = [0, -1, -2, -10])
    @ParameterizedTest
    fun `높이가 0보다 작거나 같으면 IllegalArgumentException 발생`(width: Int) {
        shouldThrow<IllegalArgumentException> {
            GameMap.createEmptyMap(width, 1)
        }
    }

    @Test
    fun `특정 위치에 지뢰가 있는지 확인할 수 있다 - true`() {
        val point = Point.from(0, 0)
        val field = listOf(listOf(Mine(point)))
        val map = GameMap(field)

        map.isMine(point) shouldBe true
    }

    @Test
    fun `특정 위치에 지뢰가 있는지 확인할 수 있다 - false`() {
        val point = Point.from(0, 0)
        val field = listOf(listOf(Tile(point)))
        val map = GameMap(field)

        map.isMine(point) shouldBe false
    }

    @Test
    fun `특정 위치에 지뢰를 추가할 수 있다`() {
        val map = GameMap.createEmptyMap(10, 10)

        val point = Point.from(5, 5)

        map.isMine(point) shouldBe false

        map.setMine(point)

        map.isMine(point) shouldBe true
    }
}
