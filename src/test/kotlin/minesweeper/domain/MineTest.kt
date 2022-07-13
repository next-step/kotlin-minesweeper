package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class MineTest : FreeSpec({

    "지뢰가 생성되면 기본적으로 숨겨져있다." {
        val mine = Mine()
        mine.status shouldBe DotStatus.HIDDEN
    }

    "지뢰가 숨겨져있는지 아닌지를 알 수 있다." - {
        listOf(
            row(Mine(status = DotStatus.HIDDEN), true),
            row(Mine(status = DotStatus.OPEN), false)
        ).forEach { (mine, result) ->
            "$mine 이 숨겨져있는지 결과는 $result 이다." {
                mine.isHidden shouldBe result
            }
        }
    }

    "지뢰가 안전지대인지 물으면 무조건 false를 반환한다." {
        val hiddenMine = Mine(status = DotStatus.HIDDEN)
        val openedMine = Mine(status = DotStatus.OPEN)

        hiddenMine.isZeroMineLand() shouldBe false
        openedMine.isZeroMineLand() shouldBe false
    }

    "지뢰를 열 수 있다." {
        val mine = Mine(status = DotStatus.HIDDEN)
        val openedMine = mine.open()

        openedMine.status shouldBe DotStatus.OPEN
    }
})
