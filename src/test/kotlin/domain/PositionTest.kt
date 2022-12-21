package domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    fun `position x, y 오름차순으로 정렬한다`() {
        val positions = listOf(
            Position(1, 1), Position(1, 0), Position(0, 1), Position(0, 0)
        )
        positions.sorted() shouldBe listOf(
            Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1)
        )
    }
}
