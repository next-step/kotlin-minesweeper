package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BasicTest {
    @Test
    internal fun `주변의 지뢰가 있으면 카운트가 올라간다`() {
        val basic = Basic()
        basic.addCount(1)

        basic.count shouldBe 1
    }
}
