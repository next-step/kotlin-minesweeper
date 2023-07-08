package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import model.minemark.Mine
import model.minemark.Safety

@DisplayName("설치된 지뢰 보드")
class InstalledMineBoardTest : StringSpec({

    "지뢰 보드로 생성 가능" {
        shouldNotThrowAny {
            InstalledMineBoard(FOUR_ELEMENTS_TWO_MINE_BOARD)
        }
    }
}) {
    companion object {
        private val FOUR_ELEMENTS_TWO_MINE_BOARD = FilledElements(
            mapOf(
                Position(0, 0) to Safety(),
                Position(1, 1) to Mine(),
                Position(0, 1) to Safety(),
                Position(1, 0) to Mine(),
            )
        )
    }
}
