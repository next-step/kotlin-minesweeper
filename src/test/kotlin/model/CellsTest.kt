package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CellsTest : StringSpec({
    "가로 세로 지뢰수를 받는 cells 객체를 생성한다" {
        // when
        val cell = Cells(rows = 10, cols = 10, mineCount = 10)

        // then
        cell.rows shouldBe 10
        cell.cols shouldBe 10
        cell.mineCount shouldBe 10
        cell.cells.size shouldBe 10
        cell.cells[1].size shouldBe 10
    }
})
