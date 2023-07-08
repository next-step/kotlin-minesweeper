package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class LocationTest {
    @Test
    internal fun `x와 y값이 같으면 isSame이 true다`() {
        Location(1, 2).isSame(1, 2) shouldBe true
    }
}
