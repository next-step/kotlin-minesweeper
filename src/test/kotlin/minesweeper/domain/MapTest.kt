package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize

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
})
