package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ColumnTest {
    @Test
    fun `Cell 생성 테스트`() {
        Column(Width(10)).size shouldBe 10
    }
}
