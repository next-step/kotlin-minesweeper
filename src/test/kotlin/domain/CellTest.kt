package domain

import domain.vo.Point
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

internal class CellTest : StringSpec({

    "지뢰를 가진 cell 을 생성한다" {
        val cell = Mine(Coordinate(Point(1), Point(2)))

        cell.shouldBeInstanceOf<Cell>()
    }

    "지뢰가 없는 cell 을 생성한다" {
        val cell = Empty(Coordinate(Point(1), Point(2)))

        cell.shouldBeInstanceOf<Cell>()
    }
})
