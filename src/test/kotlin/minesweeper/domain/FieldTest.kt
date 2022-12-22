package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class FieldTest {
    @Test
    fun `안전지대는 주변에 존재하는 마인 개수를 가진다`() {
        val safe = Safe(7)

        safe.aroundMineCount shouldBe 7
    }

    @Test
    fun `지뢰는 *로 나타낸다`() {
        val mine = Mine()

        mine.toString() shouldBe "*"
    }
}
