package minesweeper.domain.minemap

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MapItemTest {
    @Test
    internal fun `아이템을 열 수 있다`() {
        val sut = Mine()
        sut.isOpened shouldBe false
        sut.open()
        sut.isOpened shouldBe true
    }
}
