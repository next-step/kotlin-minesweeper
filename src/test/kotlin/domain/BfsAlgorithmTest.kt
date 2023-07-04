package domain

import domain.model.GameMap
import domain.model.Mine
import domain.model.NumberTile
import domain.model.Point
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BfsAlgorithmTest {
    @Test
    fun `특정 위치의 타일을 열면 인접한 숫자 타일이 같이 열린다`() {
        val point = Point.from(1, 1)
        /*
        0, 0, 0
        0, 0, 0
        0, 0, 0
         */
        val field = listOf(
            listOf(
                NumberTile(Point.from(0, 0)),
                NumberTile(Point.from(1, 0)),
                NumberTile(Point.from(2, 0)),
            ),
            listOf(
                NumberTile(Point.from(0, 1)),
                NumberTile(Point.from(1, 1)),
                NumberTile(Point.from(2, 1)),
            ),
            listOf(
                NumberTile(Point.from(0, 2)),
                NumberTile(Point.from(1, 2)),
                NumberTile(Point.from(2, 2)),
            ),
        )
        val map = GameMap(field)
        val algorithm = BfsAlgorithm()

        map.isAllOpened() shouldBe false
        algorithm.openTiles(map, point)
        map.isAllOpened() shouldBe true
    }

    @Test
    fun `특정 위치의 타일을 열었을 때 지뢰 타일은 열리지 않는다`() {
        val point = Point.from(1, 1)
        /*
        0, 1, 1
        0, 1, *
        0, 1, 1
         */
        val field = listOf(
            listOf(
                NumberTile(Point.from(0, 0)),
                NumberTile(Point.from(1, 0)),
                NumberTile(Point.from(2, 0)),
            ),
            listOf(
                NumberTile(Point.from(0, 1)),
                NumberTile(Point.from(1, 1)),
                Mine(Point.from(2, 1)),
            ),
            listOf(
                NumberTile(Point.from(0, 2)),
                NumberTile(Point.from(1, 2)),
                NumberTile(Point.from(2, 2)),
            ),
        )
        val map = GameMap(field).apply {
            updateField()
        }

        BfsAlgorithm().openTiles(map, point)
        map.field[1][2].isOpened shouldBe false
    }

    @Test
    fun `특정 위치의 타일을 열었을 때 값이 0인 연결된 숫자 타일은 모두 열린다`() {
        /*
        0, 0, 1, *
        0, 0, 1, 1
        1, 1, 1, 0
        1, *, 1, 0
         */
        val field = listOf(
            listOf(
                NumberTile(Point.from(0, 0)),
                NumberTile(Point.from(1, 0)),
                NumberTile(Point.from(2, 0)),
                Mine(Point.from(3, 0)),
            ),
            listOf(
                NumberTile(Point.from(0, 1)),
                NumberTile(Point.from(1, 1)),
                NumberTile(Point.from(2, 1)),
                NumberTile(Point.from(3, 1)),
            ),
            listOf(
                NumberTile(Point.from(0, 2)),
                NumberTile(Point.from(1, 2)),
                NumberTile(Point.from(2, 2)),
                NumberTile(Point.from(3, 2)),
            ),
            listOf(
                NumberTile(Point.from(0, 3)),
                Mine(Point.from(1, 3)),
                NumberTile(Point.from(2, 3)),
                NumberTile(Point.from(3, 3)),
            ),
        )
        val map = GameMap(field).apply {
            updateField()
        }
        BfsAlgorithm().openTiles(map, Point.from(1, 1))
        map.field[0][0].isOpened shouldBe true
        map.field[3][0].isOpened shouldBe false
        map.field[2][3].isOpened shouldBe false
    }
}
