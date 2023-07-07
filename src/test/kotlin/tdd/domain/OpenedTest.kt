package tdd.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OpenedTest {

    @Test
    fun `When Opened opens, return self`() {
        val opened = Opened.of(3)
        opened shouldBe opened
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 9])
    fun `When aroundMineCount is out of range, should throw exception`(aroundMineCount: Int) {
        shouldThrow<IllegalStateException> {
            Opened.of(aroundMineCount)
        }
    }

    @Test
    fun `Should return aroundMinceCount`() {
        Opened.of(3).aroundMineCount() shouldBe 3
    }
}
