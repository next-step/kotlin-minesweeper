package minesweeper

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.random.nextInt

class MineSweeperTest {
    @Test
    fun `높이와 너비가 가지는 영역에 지뢰를 랜덤하게 위치시킨다`() {
        // [give]
        val height = 3
        val width = 7
        val mineCount = 10
        // map init
        val mineSweeperMap = Array(height) { CharArray(width) { 'C' } }

        // [when]
        // set mines at random pos
        val MINE_SWEEPER_GAME_RANGE = 0 until height * width
        val numberSet = mutableSetOf<Pair<Int, Int>>()
        while (numberSet.size < mineCount) {
            val randomNumber = Random.nextInt(MINE_SWEEPER_GAME_RANGE)
            val row = randomNumber / width
            val column = randomNumber % width
            numberSet.add(row to column)
        }
        numberSet.forEach { (row, height) -> mineSweeperMap[row][height] = '*' }

        // [then]
        val actualMineCount = mineSweeperMap.map { row ->
            row.count { pos -> pos == '*' }
        }.reduce { acc, i -> acc + i }

        actualMineCount shouldBe mineCount
    }
}
