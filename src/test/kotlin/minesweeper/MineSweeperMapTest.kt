package minesweeper

import domain.MineSweeperMap
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineSweeperMapTest {
    @Test
    fun `높이와 너비가 가지는 영역에 지뢰를 랜덤하게 위치시킨다`() {
        // [give]
        val height = 3
        val width = 7
        val mineCount = 10

        // [when]
        val mineSweeperMap = MineSweeperMap(height, width, mineCount)

        // [then]
        val actualMineCount = mineSweeperMap.value.map { row ->
            row.count { pos -> pos == '*' }
        }.reduce { acc, i -> acc + i }

        actualMineCount shouldBe mineCount
    }
}
