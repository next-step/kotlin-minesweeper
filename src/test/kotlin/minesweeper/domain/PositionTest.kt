package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row

class PositionTest : StringSpec({
    "Position의 x, y의 값이 1보다 작은 경우 예외를 발생시킨다." {
        listOf(
            row(1, 0),
            row(0, 1),
        ).forEach { (x, y) ->
            // when // then
            shouldThrowExactly<IllegalArgumentException> { Position(x, y) }
        }
    }
})
