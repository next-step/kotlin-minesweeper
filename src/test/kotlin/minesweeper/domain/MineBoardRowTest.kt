package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldBeInstanceOf

class MineBoardRowTest : StringSpec({
    "특정 셀에 지뢰를 심으면 해당 셀은 지뢰가 나타남을 확인한다." {
        //given
        val mineBoardRow = MineBoardRow(5)
        //when
        mineBoardRow.plantMine(3)
        //then
        mineBoardRow.mineCells[3] shouldBe MineCell()
    }

    "특정 셀에 주변 지뢰 개수를 증가시키는 함수를 두번 호출하면 그 값이 2임을 확인한다." {
        //given
        val mineBoardRow = MineBoardRow(Array(5) { CleanCell() })
        val cleanCell = mineBoardRow.mineCells[3]
        //when
        mineBoardRow.incrementNearMineCount(3)
        mineBoardRow.incrementNearMineCount(3)
        //then
        assertSoftly {
            cleanCell.shouldBeInstanceOf<CleanCell>()
            cleanCell.nearMineCount shouldBe 2
        }

    }

})
