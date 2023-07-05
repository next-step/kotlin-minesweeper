package map.position

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldNotContainExactly

internal class PositionTest : StringSpec({
    "자신의 인접한 Position 들을 반환한다" {
        val pos = Position(1, 1)
        val expected = listOf(Position(0, 0), Position(0, 1), Position(1, 0), Position(1, 1))
        val actual = pos.getAdjacentNeighbors()
        actual shouldNotContainExactly expected
    }
})
