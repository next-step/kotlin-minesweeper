package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMapTest {
    @Test
    fun `지뢰는 맵 안에서 개수대로 배치된다`() {
        val tenMines = MineMapInfo(LineCount(10), LineCount(10), MineCount(10))
        assertThat(MineMap(tenMines).mineMap.flatten().count { it == Mine.MINE }).isEqualTo(10)

        val twentyMines = MineMapInfo(LineCount(10), LineCount(10), MineCount(20))
        assertThat(MineMap(twentyMines).mineMap.flatten().count { it == Mine.MINE }).isEqualTo(20)
    }

    @Test
    fun `지뢰는 전략대로 배치된다`() {
        val tenMines = MineMapInfo(LineCount(10), LineCount(10), MineCount(10))
        val firstRowStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                val mineNum = mineMapInfo.mineCnt
                return buildList {
                    for (i in 0 until mineNum) {
                        add(i.getPoint(mineMapInfo.rowCnt))
                    }
                }
            }
        }

        assertThat(MineMap(tenMines, firstRowStrategy).mineMap[0].count { it == Mine.MINE }).isEqualTo(10)

        val tenMines2 = MineMapInfo(LineCount(10), LineCount(10), MineCount(10))
        val lastRowStrategy = object : MinePointCreateStrategy {
            override fun createMinePoints(mineMapInfo: MineMapInfo): List<Point> {
                val mineNum = mineMapInfo.mineCnt
                return buildList {
                    for (i in mineMapInfo.total - mineNum until mineMapInfo.total) {
                        add(i.getPoint(mineMapInfo.rowCnt))
                    }
                }
            }
        }

        assertThat(MineMap(tenMines2, lastRowStrategy).mineMap[9].count { it == Mine.MINE }).isEqualTo(10)
    }
}
