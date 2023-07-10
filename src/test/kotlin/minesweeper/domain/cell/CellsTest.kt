package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.point.Point
import minesweeper.fixture.CellFixture.clearCell

class CellsTest : ShouldSpec({

    should("Point에 해당 하는 Cell을 가져 온다.") {
        val clearCell = clearCell(0, 0)
        val cells = Cells.from(listOf(clearCell))
        cells.at(Point(0, 0)) shouldBe clearCell
    }

    should("Point에 해당 하는 Cell이 없으면 에러가 발생 한다.") {
        val clearCell = clearCell(0, 0)
        val cells = Cells.from(listOf(clearCell))
        shouldThrow<RuntimeException> { cells.at(Point(1, 0)) }
    }

    should("오픈 시, isOpened는 true이다.") {
        val clearCell = clearCell(0, 0)
        clearCell.open()
        clearCell.isOpened shouldBe true
    }

    should("값 증가 시, 새로운 Cell이 반환 된다.") {
        val clearCell = clearCell(0, 0)
        val actual = clearCell.increase()
        actual.shouldBeInstanceOf<HazardCell>()
        actual.count shouldBe 1
    }

    should("오픈 시, 해당 Cell이 ClearCell이면 주변 Cell도 함께 오픈 된다.") {
        val clearCells = Point.square(3, 3).map { ClearCell(it) }
        val cells = Cells.from(clearCells)

        val opendCell = cells.open(Point(1, 1))

        Point.square(3, 3)
            .forAll { cells.at(it).isOpened shouldBe true }
    }
})
