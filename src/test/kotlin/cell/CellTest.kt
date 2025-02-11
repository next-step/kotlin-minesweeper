package cell

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `주변에 지뢰가 없는 경우 true를 반환한다`() {
        val actual = BlankCell(MineCount.ZERO)

        val result = actual.shouldFloodFill()

        result shouldBe true
    }

    @Test
    fun `지뢰 셀은 주변에 지뢰가 없어도 false를 반환한다`() {
        val actual = MineCell

        val result = actual.shouldFloodFill()

        result shouldBe false
    }

    @Test
    fun `셀을 공개하면 revealed가 true가 된다`() {
        val actual = BlankCell(MineCount.ZERO)

        val result = actual.reveal()

        result.revealed shouldBe true
    }
}
