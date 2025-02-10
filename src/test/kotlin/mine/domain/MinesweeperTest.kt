package mine.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MinesweeperTest {
    @Test
    fun `지뢰 개수가 게임 크기 초과시 예외`() {
        val height = 3
        val width = 3
        val mineCount = 10

        val exception =
            assertThrows<IllegalArgumentException> {
                Minesweeper(height, width, mineCount)
            }
        exception.message shouldBe "지뢰 개수는 1개이상 이며 보드 크기를 초과할수 없습니다."
    }

    @Test
    fun `파라메터  0보다큰 양수 확인 `() {
        val exception1 = assertThrows<IllegalArgumentException> { Minesweeper(0, 5, 3) }
        exception1.message shouldBe "높이는 1 이상이어야 합니다."

        val exception2 = assertThrows<IllegalArgumentException> { Minesweeper(5, 0, 3) }
        exception2.message shouldBe "너비는 1 이상이어야 합니다."
    }
}
