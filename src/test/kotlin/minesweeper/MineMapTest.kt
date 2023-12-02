package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMapTest {
    @Test
    fun `지뢰는 맵 안에서 개수대로 배치된다`() {
        val tenMines = MineMapInfo(LineCount(10), LineCount(10), MineCount(10))
        assertThat(MineMap.create(tenMines).mineMap.count { it.value is MapTile.Mine }).isEqualTo(10)

        val twentyMines = MineMapInfo(LineCount(10), LineCount(10), MineCount(20))
        assertThat(MineMap.create(twentyMines).mineMap.count { it.value is MapTile.Mine }).isEqualTo(20)
    }

    /**
     * 테스트 내에서만 쓰는, 특정 형태의 string을 입력 시 MineMap을 뽑아주는 메소드
     *
     * ex)
     *      ...*
     *      ....
     *      .*..
     *      ....
     *
     * -> 4x4 맵에 (1열 4행), (3열 2행) 지뢰가 있음
     */
    private fun createMap(mapString: String): MineMap {
        val rows = mapString.split("\n")

        val mines = mutableListOf<Point>()
        var mineCount = 0
        rows.forEachIndexed { row, str ->
            val columnList = str.withIndex().filter { it.value == '*' }.map { it.index }
            for (col in columnList) {
                mines.add(Point(row + 1, col + 1))
                mineCount++
            }
        }

        val mineMapInfo = MineMapInfo(MapSize(LineCount(rows.size), LineCount(rows[0].length)), MineCount(mineCount))
        val mineStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                return mines
            }
        }

        return MineMap.create(mineMapInfo, mineStrategy)
    }

    @Test
    fun `지뢰는 전략에 맞게 배치된다`() {
        val firstLineMine =
            """
                **********
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
            """.trimIndent()

        val isMinesInFirstRow: (Map.Entry<Point, MapTile>) -> Boolean = { it.key.row == 1 && it.value == MapTile.Mine }
        assertThat(createMap(firstLineMine).mineMap.count(isMinesInFirstRow)).isEqualTo(10)

        val lastLineMine =
            """
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                **********
            """.trimIndent()

        val isMinesInLastRow: (Map.Entry<Point, MapTile>) -> Boolean = { it.key.row == 10 && it.value == MapTile.Mine }
        assertThat(createMap(lastLineMine).mineMap.count(isMinesInLastRow)).isEqualTo(10)
    }

    @Test
    fun `지뢰에 안 둘러쌓여 있다면 둘러쌓인 지뢰의 수는 0이다`() {
        val mapCenterBlank =
            """
                ...
                ...
                ...
            """.trimIndent()

        val center = Point(2, 2)
        assertThat(createMap(mapCenterBlank).mineMap[center]).isEqualTo(MapTile.Blank(0))
    }

    @Test
    fun `지뢰에 모두 둘러쌓여 있다면 둘러쌓인 지뢰의 수는 8이다`() {

        val mapCenterBlank =
            """
                ***
                *.*
                ***
            """.trimIndent()

        val center = Point(2, 2)
        assertThat(createMap(mapCenterBlank).mineMap[center]).isEqualTo(MapTile.Blank(8))
    }

    @Test
    fun `지뢰가 아닌 칸은 인접한 칸의 지뢰의 수를 표시한다`() {
        val mapString =
            """
                ....
                .**.
                ....
            """.trimIndent()

        val map = createMap(mapString)

        assertThat(map.mineMap[Point(1, 1)]).isEqualTo(MapTile.Blank(1))
        assertThat(map.mineMap[Point(1, 2)]).isEqualTo(MapTile.Blank(2))
    }
}
