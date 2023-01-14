package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

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
        val nextMap = map.open(cellPosition)

        nextMap.status shouldBe Status.WINNING
    }

    "Blank 셀 인접한 칸이 Open 된다." {
        val openCellPosition = CellPosition(Position(0), Position(0))

        val cells = listOf<Cell>(
            Cell.Blank(CellPosition(Position(-1), Position(0))),
            Cell.Blank(openCellPosition),
            Cell.Blank(CellPosition(Position(1), Position(0))),
        )
        val map = Map(cells)
        val nextMap = map.open(openCellPosition)
        val isAllOpen = nextMap.cells
            .filterIsInstance<Cell.Blank>()
            .all { cell -> cell.isOpen }

        isAllOpen.shouldBeTrue()
    }

    "Mine 셀을 오픈 할 경우 게임에서 진다." {
        val cellPosition = CellPosition(Position(0), Position(0))
        val cells = listOf<Cell>(Cell.Mine(cellPosition))
        val map = Map(cells)
        val nextMap = map.open(cellPosition)

        nextMap.status shouldBe Status.LOSING
    }

    "맵에 존재 하지 않는 Position 에 벗어난 경우 예외 발생한다." {
        val cellPosition = CellPosition(Position(0), Position(0))
        val cells = listOf<Cell>(Cell.Mine(cellPosition))
        val map = Map(cells)

        val openPosition = CellPosition(Position(-1), Position(-1))

        val message = shouldThrow<IllegalArgumentException> {
            map.open(openPosition)
        }

        message shouldHaveMessage "좌표에 벗어난 값입니다."
    }
})
