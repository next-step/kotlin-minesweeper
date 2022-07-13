package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import minesweeper.dto.MineCount

internal class LandTest : FreeSpec({

    "안전지대가 생성되면 기본적으로 숨겨져있다." {
        val land = Land(mineCount = MineCount(0))
        land.status shouldBe DotStatus.HIDDEN
    }

    "안전지대가 숨겨져있는지 아닌지를 알 수 있다." - {
        listOf(
            row(Land(mineCount = MineCount(0), status = DotStatus.HIDDEN), true),
            row(Land(mineCount = MineCount(0), status = DotStatus.OPEN), false)
        ).forEach { (land, result) ->
            "$land 이 숨겨져있는지 결과는 $result 이다." {
                land.isHidden shouldBe result
            }
        }
    }

    "안전지대가 주변에 지뢰가 없는 안전지대인지 알 수 있다." - {
        listOf(
            row(Land(mineCount = MineCount(0)), true),
            row(Land(mineCount = MineCount(1)), false),
            row(Land(mineCount = MineCount(8)), false),
        ).forEach { (land, result) ->
            "$land 가 주변에 지뢰가 없는 안전지대인지 결과는 $result 다." {
                land.isZeroMineLand() shouldBe result
            }
        }
    }

    "안전지대를 열 수 있다." {
        val land = Land(status = DotStatus.HIDDEN, mineCount = MineCount(0))
        val openedLand = land.open()

        openedLand.status shouldBe DotStatus.OPEN
    }
})
