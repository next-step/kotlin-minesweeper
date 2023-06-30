package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@DisplayName("설치된 지뢰 보드")
class InstalledMineBoardTest : StringSpec({

    "지뢰 보드로 생성 가능" {
        shouldNotThrowAny {
            InstalledMineBoard(FOUR_ELEMENTS_TWO_MINE_BOARD)
        }
    }

    "주어진 지뢰 보드 그대로 반환" {
        // given
        val installedMineBoard = InstalledMineBoard(FOUR_ELEMENTS_TWO_MINE_BOARD)
        // when & then
        installedMineBoard.mineBoard shouldBe FOUR_ELEMENTS_TWO_MINE_BOARD
    }
})
