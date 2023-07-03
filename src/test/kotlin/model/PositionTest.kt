package model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

@DisplayName("위치")
class PositionTest : StringSpec({

    "x 와 y 좌표로 생성" {
        listOf(0, 1, Int.MAX_VALUE).forAll {
            shouldNotThrowAny { Position(it, it) }
        }
    }

    "좌표는 반드시 양수" {
        listOf(Int.MIN_VALUE, -1).forAll {
            shouldThrowExactly<IllegalArgumentException> {
                Position(it, it)
            }
        }
    }

    "주어진 x y 대로 반환" {
        listOf(0, 1, Int.MAX_VALUE).forAll {
            assertSoftly(Position(it, it)) { position ->
                position.x shouldBe it
                position.y shouldBe it
            }
        }
    }

    "근처 포지션들을 조회할 수 있음" {
        // given
        val position = Position(1, 1)
        // when
        val nearbyPositions = position.nearByPositions(10, 10)
        // then
        nearbyPositions shouldContainExactlyInAnyOrder listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )
    }
})
