package minesweeper_tdd.domain.minemap

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

    @Test
    internal fun `빈칸 아이템은 주변 8분면에 있는 지뢰의 개수를 가진다`() {
        val sut = Empty(surroundingMineCount = 3)
        sut.surroundingMineCount shouldBe 3
    }
}
