package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PositionTest : StringSpec({
    "Position.getNearPositions() 함수는 현재 위치의 주변 8개 위치가 포함됨을 확인한다." {
        //given
        val position = Position(1, 1)
        //when
        val nearPositions = position.getNearPositions()
        //then
        nearPositions.size shouldBe 8
        nearPositions shouldContainExactly listOf(
            Position(0, 0), Position(0, 1), Position(0, 2),
            Position(1, 0), Position(1, 2),
            Position(2, 0), Position(2, 1), Position(2, 2),
        )
    }
})
