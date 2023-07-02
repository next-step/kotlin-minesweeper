import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GameMapTest {
    @ValueSource(ints = [0, -1, -2, -10])
    @ParameterizedTest
    fun `높이가 0보다 작거나 같으면 IllegalArgumentException 발생`(height: Int) {
        shouldThrow<IllegalArgumentException> {
            GameMap.create(1, height, 0)
        }
    }

    @ValueSource(ints = [0, -1, -2, -10])
    @ParameterizedTest
    fun `너비가 0보다 작거나 같으면 IllegalArgumentException 발생`(width: Int) {
        shouldThrow<IllegalArgumentException> {
            GameMap.create(width, 1, 0)
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
        val field = listOf(listOf(NumberTile(point)))
        val map = GameMap(field)

        map.isMine(point) shouldBe false
    }

    @Test
    fun `특정 위치를 둘러싼 지뢰의 갯수를 확인할 수 있다`() {
        val point = Point.from(0, 1)

        val field = listOf(
            listOf(
                NumberTile(Point.from(0, 0)),
                NumberTile(Point.from(1, 0)),
                Mine(Point.from(2, 0)),
            ),
            listOf(
                NumberTile(point),
                Mine(Point.from(1, 1)),
                NumberTile(Point.from(2, 1)),
            ),
            listOf(
                Mine(Point.from(0, 2)),
                Mine(Point.from(1, 2)),
                NumberTile(Point.from(2, 2)),
            ),
        )
        val map = GameMap(field)
        map.mineCountInSquare(point) shouldBe 3
    }

    @Test
    fun `숫자 타일의 값을 갱신할 수 있다`() {
        val point = Point.from(0, 1)

        val field = listOf(
            listOf(
                NumberTile(Point.from(0, 0)),
                NumberTile(Point.from(1, 0)),
                Mine(Point.from(2, 0)),
            ),
            listOf(
                NumberTile(point),
                Mine(Point.from(1, 1)),
                NumberTile(Point.from(2, 1)),
            ),
            listOf(
                Mine(Point.from(0, 2)),
                Mine(Point.from(1, 2)),
                NumberTile(Point.from(2, 2)),
            ),
        )
        val expected = listOf(
            listOf(1, 2, -1),
            listOf(3, -1, 3),
            listOf(-1, -1, 2),
        )
        val map = GameMap(field).apply {
            updateField()
        }
        map.field.forEachIndexed { i, tiles ->
            tiles.forEachIndexed { j, tile ->
                if (tile is NumberTile) {
                    tile.value shouldBe expected[i][j]
                }
            }
        }
    }
}
