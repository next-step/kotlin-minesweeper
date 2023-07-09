package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.point.Point
import minesweeper.fixture.CellFixture.clearCell
import minesweeper.fixture.CellFixture.mineCell

class CellsTest : ShouldSpec({
    lateinit var cells: Cells

    beforeEach {
        cells = Cells()
    }

    should("이미 존재하는 좌표의 Cell을 추가하면 해당 Cell이 새로운 Cell로 교체 된다.") {
        val clearCell = clearCell(0, 0)
        val mineCell = mineCell(0, 0)
        cells.add(clearCell)
        cells.add(mineCell)
        cells.at(Point(0, 0)) shouldBe mineCell
    }

    should("Point에 해당 하는 Cell을 가져 온다.") {
        val cell = clearCell(0, 0)
        cells.add(cell)
        cells.at(Point(0, 0)) shouldBe cell
    }

    should("Point에 해당 하는 Cell이 없으면 에러가 발생 한다.") {
        val cell = clearCell(0, 0)
        cells.add(cell)
        shouldThrow<RuntimeException> { cells.at(Point(1, 0)) }
    }

    should("특정 Point의 Cell을 다른 Cell로 변경 한다.") {
        cells.add(clearCell(0, 0))
        cells.add(clearCell(1, 0))
        cells.add(clearCell(0, 1))
        cells.add(clearCell(1, 1))

        val mineCell = mineCell(1, 1)
        cells.add(mineCell)

        cells.at(Point(1, 1)) shouldBe mineCell
    }
})
