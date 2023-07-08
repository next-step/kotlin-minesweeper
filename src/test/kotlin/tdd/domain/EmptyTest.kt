package tdd.domain

import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.junit.jupiter.api.Test

class EmptyTest {

    @Test
    fun `Empty is Closed State`() {
        Empty should instanceOf<Closed>()
    }

    @Test
    fun `When Empty opens, return Opened`() {
        Empty.open(3) shouldBe Opened.of(3)
    }
}
