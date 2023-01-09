package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class MapTest : StringSpec({
    "메타 정보(높이,너비,지뢰 수)를 이용하여 지뢰게임 맵을 생성하다." {
        val mapMeta = MapMeta(
            height = 10,
            width = 10,
            mineCount = 10
        )

        val map = Map.create(mapMeta)

        map.cells shouldHaveSize 100
        map.cells.filterIsInstance<Cell.Mine>() shouldHaveSize 10
    }

    "모든 Blank 셀을 오픈 한 경우 게임에서 이긴다." {
        val cellPosition = CellPosition(Position(0), Position(0))
        val cells = listOf<Cell>(Cell.Blank(cellPosition))
        val map = Map(cells)
        val status = map.open(cellPosition)

        status shouldBe Status.WINNING
    }

    "Mine 셀을 오픈 할 경우 게임에서 진다." {
        val cellPosition = CellPosition(Position(0), Position(0))
        val cells = listOf<Cell>(Cell.Mine(cellPosition))
        val map = Map(cells)
        val status = map.open(cellPosition)

        status shouldBe Status.LOSING
    }
})
