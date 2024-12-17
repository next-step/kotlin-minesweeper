package mine.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineTest {
    @Test
    fun `지뢰 필드 생성 테스트`() {
        val mine = Mine(5, 5, 3)
        assertEquals(5, mine.minesweeper.size)
        assertEquals(5, mine.minesweeper[0].size)
    }

    @Test
    fun `지뢰 생성 개수 테스트`() {
        val height = 5
        val width = 5
        val mineCount = 10
        val mine = Mine(height, width, mineCount)

        val placedMineCount = mine.minesweeper.sumOf { row -> row.count { it == Mine.MINE_SYMBOL } }
        assertEquals(mineCount, placedMineCount)
    }

    @Test
    fun `지뢰 개수가 게임 크기 초과시 예외`() {
        val height = 3
        val width = 3
        val mineCount = 10

        val exception =
            assertThrows<IllegalArgumentException> {
                Mine(height, width, mineCount)
            }
        assertEquals("지뢰 개수는 전체 칸의 수보다 많을 수 없습니다.", exception.message)
    }

    @Test
    fun `파라메터  0보다큰 양수 확인 `() {
        val exception1 = assertThrows<IllegalArgumentException> { Mine(-1, 5, 3) }
        assertEquals("높이는 0보다 커야합니다.", exception1.message)

        val exception2 = assertThrows<IllegalArgumentException> { Mine(5, -1, 3) }
        assertEquals("너비는 0보다 커야합니다.", exception2.message)

        val exception3 = assertThrows<IllegalArgumentException> { Mine(5, 5, -1) }
        assertEquals("지뢰 개수는 0보다 커야합니다.", exception3.message)
    }
}
