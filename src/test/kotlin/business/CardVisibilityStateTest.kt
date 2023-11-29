package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardVisibilityStateTest {

    @Test
    fun `open 되여 있으면 true`() {
        // given
        val cardVisibilityState = CardVisibilityState.VISIBLE

        // when
        val result = cardVisibilityState.isVisible()

        // then
        result shouldBe true
    }

    @Test
    fun `open 되여 있지 않으면 false`() {
        // given
        val cardVisibilityState = CardVisibilityState.HIDDEN

        // when
        val result = cardVisibilityState.isVisible()

        // then
        result shouldBe false
    }
}
