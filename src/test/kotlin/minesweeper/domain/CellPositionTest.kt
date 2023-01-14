package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CellPositionTest : StringSpec({
    "x,y 에 대해 오름차순 정렬을 해준다." {

        val cellPositions = listOf(
            CellPosition(Position(1), Position(1)),
            CellPosition(Position(1), Position(0)),
            CellPosition(Position(0), Position(0))
        )
        
        val expect = listOf(
            CellPosition(Position(0), Position(0)),
            CellPosition(Position(1), Position(0)),
            CellPosition(Position(1), Position(1))
        )

        cellPositions.sorted() shouldBe expect
    }
})
