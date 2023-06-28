package minesweeper.domain

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class PointBasedMineCellSelector(private val pointOfMines: Set<Point>): MineCellSelector {
    constructor(vararg point: Point): this(point.toSet())
    override fun select(): Set<Point> = pointOfMines
}
class CellGeneratorTest: ShouldSpec({
    context("셀 생성기에 Point(1, 1)이 지뢰로 등록 되었다.") {
        val mineSelector = PointBasedMineCellSelector(Point(1, 1))
        val generator = CellGenerator(mineSelector)
        should("Point(1, 1)로 생성 요청 시, 지뢰셀(MineCell)을 반환 한다.") {
            val actual = generator.generate(Point(1, 1))
            actual.shouldBeInstanceOf<MineCell>()
        }
        should("Point(1, 2)로 생성 요청 시, 빈셀(ClearCell)을 반환 한다.") {
            val actual = generator.generate(Point(1, 2))
            actual.shouldBeInstanceOf<ClearCell>()
        }
    }

})
