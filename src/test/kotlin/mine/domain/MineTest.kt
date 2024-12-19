package mine.domain

import io.kotest.matchers.shouldBe
import mine.enums.MineCell
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineTest {
    @Test
    fun `지뢰 필드 생성 테스트`() {
        val mine = Mine(5, 5, 3)
        mine.minesweeper.size shouldBe 5
        mine.minesweeper[0].mineCells.size shouldBe 5
    }

    @Test
    fun `지뢰 생성 개수 테스트`() {
        val height = 5
        val width = 5
        val mineCount = 10
        val mine = Mine(height, width, mineCount)

        val placedMineCount =
            mine.minesweeper.sumOf { row -> row.mineCells.count { it == MineCell.MINE } }
        mineCount shouldBe placedMineCount
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
        exception.message shouldBe "지뢰 개수는 전체 칸의 수보다 많을 수 없습니다."
    }

    @Test
    fun `파라메터  0보다큰 양수 확인 `() {
        val exception1 = assertThrows<IllegalArgumentException> { Mine(-1, 5, 3) }
        exception1.message shouldBe "높이는 0보다 커야합니다."

        val exception2 = assertThrows<IllegalArgumentException> { Mine(5, -1, 3) }
        exception2.message shouldBe "너비는 0보다 커야합니다."

        val exception3 = assertThrows<IllegalArgumentException> { Mine(5, 5, -1) }
        exception3.message shouldBe "지뢰 개수는 0보다 커야합니다."
    }
}
