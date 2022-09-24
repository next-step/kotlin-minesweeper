package v2minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize

class PositionTest : FunSpec({
    test("인근의 유효한 값을 찾는다. 유효하지 않은 값은 결과에 포함되지 않는다.") {
        // given
        val basePosition = Position(1 to 1)

        // when
        val result = basePosition.toNextPositions()

        // then
        result shouldHaveSize 3
        result shouldContainAll listOf(
            Position(2 to 1),
            Position(1 to 2),
            Position(2 to 2)
        )
    }

    test("인근의 유효한 위치 값을 찾는다.") {
        // given
        val basePosition = Position(2 to 2)

        // when
        val result = basePosition.toNextPositions()

        // then
        result shouldHaveSize 8
        result shouldContainAll listOf(
            Position(1 to 1),
            Position(2 to 1),
            Position(1 to 2),
            Position(3 to 2),
            Position(2 to 3),
            Position(1 to 1),
            Position(2 to 1),
            Position(3 to 3)
        )
    }

    test("Map의 key 값이 Position이면 Position 대신 올바른 Pair 값으로 조회해도 value를 조회할 수 있다.") {
        // given
        listOf(
            row(Position(1 to 1), "String"),
            row(Position(1 to 1), 'c'),
            row(Position(1 to 1), 100)
        ).forEach { (key, value) ->
            // given
            val map = mapOf(key to value)

            // when // then
            shouldNotThrowAny { map[key.value] }
        }
    }

    test("Map의 key 값이 Position이면 Position 대신 엉뚱한 Pair 값으로 조회하면 예외를 발생시킨다.") {
        // given
        val map = mapOf(Position(1 to 1) to "value")

        // when // then
        shouldThrowExactly<IllegalArgumentException> { map[1 to 2] }
    }
})
