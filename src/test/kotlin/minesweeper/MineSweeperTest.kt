package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperTest {
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
    fun `이미 클릭한 구역을 클릭하면 이미 클릭했다고 알려준다`() {
        val mapCenterBlank =
            """
                ...
                ..*
                ...
            """.trimIndent()
        val mineSweeper = MineSweeper(createMap(mapCenterBlank), setOf(Point(2, 2)))

        mineSweeper.click(Point(1, 1))

        assertThat(mineSweeper.click(Point(1, 1))).isEqualTo(MineSweeper.ClickResult.ALREADY_CLICKED)
        assertThat(mineSweeper.click(Point(2, 2))).isEqualTo(MineSweeper.ClickResult.ALREADY_CLICKED)
    }

    @Test
    fun `지뢰 외 모든 구역을 다 찾았다면, 승리로 끝난다`() {
        val mapCenterBlank =
            """
                ***
                *.*
                ***
            """.trimIndent()
        val mineSweeper = MineSweeper(createMap(mapCenterBlank), setOf(Point(2, 2)))
        assertThat(mineSweeper.isDone).isTrue()
    }

    @Test
    fun `클릭 시 지뢰라면, 그 즉시 패배로 게임이 끝난다`() {
        val mapCenterBlank =
            """
                ***
                *.*
                ***
            """.trimIndent()
        val mineSweeper = MineSweeper(createMap(mapCenterBlank))
        assertThat(mineSweeper.click(Point(1, 1))).isEqualTo(MineSweeper.ClickResult.GAME_OVER)
    }

    @Test
    fun `클릭 시 주변 지뢰 수가 1 이상이라면, 클릭한 부분만 보여준다`() {
        val map1 =
            """
                .*
                *.
            """.trimIndent()
        val mineSweeper1 = MineSweeper(createMap(map1))
        val clickResult = mineSweeper1.click(Point(1, 1))
        assertThat(clickResult).isEqualTo(MineSweeper.ClickResult.CONTINUE)
        assertThat(mineSweeper1.clickedSet.size).isEqualTo(1)

        val map2 =
            """
                ..*.
                ....
                ....
            """.trimIndent()
        val mineSweeper2 = MineSweeper(createMap(map2))
        val clickResult2 = mineSweeper2.click(Point(1, 2))
        assertThat(clickResult2).isEqualTo(MineSweeper.ClickResult.CONTINUE)
        assertThat(mineSweeper2.clickedSet.size).isEqualTo(1)
    }

    @Test
    fun `클릭 시 주변 지뢰 수가 0개라면 인접 구역을 모두 밝힌다`() {
        val map1 =
            """
                ..*..
                .....
                .....
            """.trimIndent()
        val mineSweeper1 = MineSweeper(createMap(map1))
        val clickResult1 = mineSweeper1.click(Point(1, 1))
        assertThat(clickResult1).isEqualTo(MineSweeper.ClickResult.CONTINUE)
        assertThat(mineSweeper1.clickedSet.size).isEqualTo(14)

        val map2 =
            """
                ..*
                ...
                ..*
            """.trimIndent()
        val mineSweeper2 = MineSweeper(createMap(map2))
        val clickResult2 = mineSweeper2.click(Point(1, 1))
        assertThat(clickResult2).isEqualTo(MineSweeper.ClickResult.CONTINUE)
        assertThat(mineSweeper2.clickedSet.size).isEqualTo(6)
    }
}
