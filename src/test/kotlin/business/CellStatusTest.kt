package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellStatusTest {

    @Test
    fun `지뢰가 맞으면 true`() {
        // given
        val cellStatus = CellStatus.MINE

        // when
        val result = cellStatus.isMine()

        // then
        result shouldBe true
    }

    @Test
    fun `지뢰가 아니면 false`() {
        // given
        val cellStatus = CellStatus.EMPTY

        // when
        val result = cellStatus.isMine()

        // then
        result shouldBe false
    }
}
