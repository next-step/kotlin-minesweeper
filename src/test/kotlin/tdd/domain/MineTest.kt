package tdd.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.junit.jupiter.api.Test

class MineTest {

    @Test
    fun `Mine is Closed State`() {
        Mine shouldBe instanceOf<Closed>()
    }

    @Test
    fun `When Mine opens, should throw exception`() {
        shouldThrow<IllegalStateException> {
            Mine.open(3)
        }
    }
}
