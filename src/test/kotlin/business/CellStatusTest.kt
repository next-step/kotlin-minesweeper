package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellStatusTest {

    @Test
    fun `지뢰가 맞는지 확인한다`() {
        // given
        val cellStatus = CellStatus.MINE

        // when
        val result = cellStatus.isMine()

        // then
        result shouldBe true
    }

    @Test
    fun `지뢰가 아닌지 확인한다`() {
        // given
        val cellStatus = CellStatus.EMPTY

        // when
        val result = cellStatus.isMine()

        // then
        result shouldBe false
    }
}
