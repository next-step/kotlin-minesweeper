package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.Cell

/**
 * @author 이상준
 */
class CellTest : StringSpec({
    "지뢰찾기 셀 생성 테스트" {
        val cell = Cell()
        cell.isMine() shouldBe false
    }
    "지뢰찾기 셀 지뢰 생성 테스트" {
        val cell = Cell()
        cell.addMine()
        cell.isMine() shouldBe true
    }
})
