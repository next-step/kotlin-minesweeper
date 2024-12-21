package cell

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellBoardTest {
    @Test
    fun `입력받은 높이, 너비, 지뢰 개수에 따라 지뢰 보드를 생성한다`() {
        val height = Length(3)
        val width = Length(3)
        val mineCount = Count(1)

        val actual = CellBoard.of(height, width, mineCount)

        actual.cellCount shouldBe 9
        actual.mineCount shouldBe 1
    }
}
