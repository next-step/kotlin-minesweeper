package cell

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellBoardTest {
    @Test
    fun `입력받은 높이, 너비, 지뢰 개수에 따라 지뢰 보드를 생성한다`() {
        val height = 3
        val width = 3
        val mineCount = 1

        val actual = CellBoard.of(height, width, mineCount)

        actual.cellCount shouldBe 9
        actual.mineCount shouldBe 1
    }

    @Test
    fun `높이가 0 이하일 경우 예외가 발생한다`() {
        val height = 0
        val width = 3
        val mineCount = 1

        shouldThrow<IllegalArgumentException> {
            CellBoard.of(height, width, mineCount)
        }
    }

    @Test
    fun `너비가 0 이하일 경우 예외가 발생한다`() {
        val height = 3
        val width = 0
        val mineCount = 1

        shouldThrow<IllegalArgumentException> {
            CellBoard.of(height, width, mineCount)
        }
    }
}
