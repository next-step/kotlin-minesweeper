package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class CellTest : StringSpec({
    "Blank 셀은 오픈 상태로 변경하다." {
        val blank = Cell.Blank.init()
        val openedCell = blank.open()

        openedCell.isOpen.shouldBeTrue()
    }

    "Mine 셀 여부를 확인하다." {
        val mine = Cell.Mine.init()

        mine.isMine().shouldBeTrue()
    }

    "셀 좌표 포함 여부를 확인하다." {
        val xPosition = Position(0)
        val yPosition = Position(0)
        val mineCell = Cell.Mine(CellPosition(xPosition, yPosition))

        forAll(
            row(CellPosition(Position(1), Position(2)), false),
            row(CellPosition(Position(0), Position(0)), true),
        ) { cellPosition, isContain ->
            val isIn = mineCell.isIn(listOf(cellPosition))

            isIn shouldBe isContain
        }
    }
})
