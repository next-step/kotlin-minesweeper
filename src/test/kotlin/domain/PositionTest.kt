package domain

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @Test
    fun `position x, y 오름차순으로 정렬한다`() {
        val positions = listOf(
            Position.of(1, 1), Position.of(1, 0), Position.of(0, 1), Position.of(0, 0)
        )
        positions.sorted() shouldBe listOf(
            Position.of(0, 0), Position.of(0, 1), Position.of(1, 0), Position.of(1, 1)
        )
    }

    @CsvSource("0,0", "0,1", "1,1", "5,5")
    @ParameterizedTest
    fun `position은 주변 position 값들을 가져올 수 있다`(x: Int, y: Int) {
        val surroundingPositions = Position.of(x, y).surroundings()
        surroundingPositions.size shouldBe 8
        surroundingPositions shouldContainAll listOf(
            Position.of(x - 1, y - 1), Position.of(x + 1, y + 1),
            Position.of(x + 1, y - 1), Position.of(x + 1, y - 1)
        )
    }
}
