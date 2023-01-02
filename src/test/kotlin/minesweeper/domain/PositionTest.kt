package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import minesweeper.common.value.CoordinateValue

class PositionTest : FreeSpec({
    "x 좌표 값과 y 좌표 값을 가지고 있다" {

        val x = CoordinateValue(value = 1)
        val y = CoordinateValue(value = 1)

        shouldNotThrowAny { Position(x = x, y = y) }
    }

    "같은 x 좌표와 y 좌표의 위치값은 동등성을 보장받는다" {
        val positionA = Position(x = CoordinateValue(10), y = CoordinateValue(10))
        val positionB = Position(x = CoordinateValue(10), y = CoordinateValue(10))
        (positionA == positionB).shouldBeTrue()
    }
})
