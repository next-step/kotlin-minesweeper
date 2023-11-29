package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `지뢰가 맞는지 확인한다`() {
        // given
        val cell = Cell(CellStatus.MINE)

        // when
        val result = cell.isMine()

        // then
        result shouldBe true
    }

    @Test
    fun `지뢰가 아닌지 확인한다`() {
        // given
        val cell = Cell(CellStatus.EMPTY)

        // when
        val result = cell.isMine()

        // then
        result shouldBe false
    }
}
