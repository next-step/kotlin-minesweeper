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
    fun `열려있는 안전지대는 주변에 존재하는 마인 개수를 나타낸다`() {
        val safe = Safe(7)

        safe.toString() shouldBe 7
    }

    @Test
    fun `열려있는 지뢰는 *로 나타낸다`() {
        val mine = Mine()
        mine.open()

        mine.toString() shouldBe "*"
    }

    @Test
    fun `열려있지 않은 Field는 C로 나타난다`() {
        val field = Field()
        val mine = Mine()
        val safe = Safe(3)

        field.toString() shouldBe "C"
        mine.toString() shouldBe "C"
        safe.toString() shouldBe "C"
    }
}
