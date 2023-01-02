package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import minesweeper.common.value.CoordinateValue

class MineTest : FreeSpec({
    "지뢰는 위치값을 가지고 있다." {

        val x = CoordinateValue(1)
        val y = CoordinateValue(1)

        val position = Position(x, y)
        shouldNotThrowAny { Mine(position = position) }
    }
})
