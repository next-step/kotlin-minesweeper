package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CleanCellTest : StringSpec({

    "주변 지뢰가 1개로 CleanCell 생성 시 주변 지뢰 개수가 1로 세팅되고 오픈 결과 MINE_NOT_FOUND 이다." {
        //given
        val cleanCell = CleanCell(Position(1, 1), listOf(Position(0, 0)))
        //when && then
        cleanCell.nearMineCount shouldBe 1
        cleanCell.openResult() shouldBe CellOpenResult.MINE_NOT_FOUND
    }

    "주변 지뢰가 0개로 CleanCell 생성 시 주변 지뢰 개수가 0으로 세팅되고 오픈 결과 SPREAD_NEEDED 이다." {
        //given
        val cleanCell = CleanCell(Position(1, 1), listOf())
        //when && then
        cleanCell.nearMineCount shouldBe 0
        cleanCell.openResult() shouldBe CellOpenResult.SPREAD_NEEDED
    }

    "copyWIthOpen함수 호출시 다른 값은 동일하고 CellState만 OPENED로 세팅된 Cell이 반환된다." {
        //given
        val cleanCell = CleanCell(Position(1, 1), listOf(Position(0, 0)))

        //when
        val copyWithOpen = cleanCell.copyWithOpen()

        //then
        copyWithOpen.position shouldBe Position(1, 1)
        copyWithOpen.nearMineCount shouldBe 1
        copyWithOpen.state shouldBe CellState.OPENED
    }
})
