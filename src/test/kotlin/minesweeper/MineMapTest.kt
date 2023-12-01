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

    @Test
    fun `지뢰는 전략대로 배치된다`() {
        val tenMines = MineMapInfo(LineCount(10), LineCount(10), MineCount(10))
        val firstRowStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                val mineNum = mineMapInfo.mineCnt
                return buildList {
                    for (i in 0 until mineNum) {
                        add(i.toPoint(mineMapInfo.rowCnt))
                    }
                }
            }
        }

        val isMinesInFirstRow: (Map.Entry<Point, MapTile>) -> Boolean = { it.key.row == 0 && it.value == MapTile.Mine }
        assertThat(
            MineMap.create(tenMines, firstRowStrategy).mineMap
                .count(isMinesInFirstRow)
        ).isEqualTo(10)

        val tenMines2 = MineMapInfo(LineCount(10), LineCount(10), MineCount(10))
        val lastRowStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                val mineNum = mineMapInfo.mineCnt
                return buildList {
                    for (i in mineMapInfo.total - mineNum until mineMapInfo.total) {
                        add(i.toPoint(mineMapInfo.rowCnt))
                    }
                }
            }
        }

        assertThat(
            MineMap.create(tenMines2, lastRowStrategy).mineMap
                .count { it.key.row == 9 && it.value == MapTile.Mine }
        ).isEqualTo(10)
    }

    @Test
    fun `지뢰에 다 둘러쌓여 있으면 Blank의 nearCount는 8이다`() {
        val mineEight = MineMapInfo(LineCount(3), LineCount(3), MineCount(8))
        val edgeStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                return buildList {
                    for (i in 0..2) {
                        for (j in 0..2) {
                            if (i == 1 && j == 1) continue
                            add(Point(i, j))
                        }
                    }
                }
            }
        }

        assertThat(MineMap.create(mineEight, edgeStrategy).mineMap[Point(1, 1)]).isEqualTo(MapTile.Blank(8))
    }

    @Test
    fun `지뢰에 안 둘러쌓여 있다면 MineMap에 정보가 없다`() {
        val mineEight = MineMapInfo(LineCount(3), LineCount(3), MineCount(1))
        val edgeStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                return listOf(Point(0, 0))
            }
        }

        assertThat(MineMap.create(mineEight, edgeStrategy).mineMap[Point(2, 2)]).isEqualTo(MapTile.Blank(0))
    }

    @Test
    fun `근처 지뢰가 있냐에 따라 카운트가 달라진다`() {
        val mineEight = MineMapInfo(LineCount(3), LineCount(4), MineCount(2))
        val edgeStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                return listOf(Point(1, 1), Point(1, 2))
            }
        }

        assertThat(MineMap.create(mineEight, edgeStrategy).mineMap[Point(0, 0)]).isEqualTo(MapTile.Blank(1))
        assertThat(MineMap.create(mineEight, edgeStrategy).mineMap[Point(0, 1)]).isEqualTo(MapTile.Blank(2))
    }
}
