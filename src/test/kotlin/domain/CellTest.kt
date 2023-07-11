package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CellTest {
    @Test
    internal fun `셀을 오픈하면 셀은 열린다`() {
        val basic = Basic()
        val mine = Mine

        basic.open()
        mine.open()

        basic.isOpen shouldBe true
        mine.isOpen shouldBe true
    }
}
