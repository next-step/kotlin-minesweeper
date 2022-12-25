package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineBoardRowTest : StringSpec({
    "특정 셀에 지뢰를 심으면 해당 셀은 지뢰가 나타남을 확인한다."{
        //given
        val mineBoardRow = MineBoardRow(5)
        //when
        mineBoardRow.plantMine(3)
        //then
        mineBoardRow.mineCells[3] shouldBe MineCell()
    }

})
