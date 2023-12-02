package minesweeper.view

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical

class CoordinateParserTest : StringSpec({

    "문자열 [1,2] 가 입력되면 Coordinate(Vertical(1), Horizontal(2)) 가 반환되어야 한다" {
        val parse = CoordinateParser.parse("1,2")
        parse shouldBe Coordinate(Vertical(1), Horizontal(2))
    }

})
