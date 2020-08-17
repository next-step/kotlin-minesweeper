package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MinesTest {

    private lateinit var mines: Mines
    private lateinit var minesExpected: List<Mine>

    @BeforeEach
    fun setUp() {
        mines = Mines(listOf(Pair(0, 0), Pair(1, 1), Pair(0, 0)).map { Mine(it) })
        minesExpected = listOf(Pair(0, 0), Pair(1, 1)).map { Mine(it) }
    }

    @Test
    fun `중복을 검사한다`() {
        assertTrue(mines.hasDuplicate())
    }

    @Test
    fun `중복 없는 지뢰 목록을 반환한다`() {
        // when
        val nonDuplicateMines = mines.duplicateRemoved()

        // then
        assertThat(nonDuplicateMines).isEqualTo(minesExpected)
    }

    @Test
    fun `지도 안 지뢰 위치에 지뢰 심볼("*")을 놓는다`() {
        // given
        val mines = Mines(listOf(Pair(0, 0), Pair(1, 1)).map { Mine(it) })
        val map = MineMap(mapArea = Pair(2, 2), _mines = mines)

        // when
        val mineMap = mines.setIntoMap(map.stateOfMap())

        // then
        assertTrue(mineMap[0][0] == "*")
        assertTrue(mineMap[1][1] == "*")

        assertFalse(mineMap[1][0] == "*")
        assertFalse(mineMap[0][1] == "*")
    }
}
