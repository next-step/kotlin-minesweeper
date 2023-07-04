package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import model.minemark.Mine
import model.minemark.MineCount
import model.minemark.Safety

@DisplayName("세어진 지뢰 보드")
class CountedMineBoardTest : StringSpec({

    "지뢰 보드로 생성" {
        shouldNotThrowAny {
            CountedMineBoard(
                MineBoard(
                    mapOf(
                        Position(0, 0) to MineCount(2),
                        Position(1, 1) to Mine(),
                        Position(0, 1) to MineCount(2),
                        Position(1, 0) to Mine(),
                    )
                )
            )
        }
    }

    "세어진 지뢰 보드에는 안전지대가 존재할 수 없음" {
        shouldThrowExactly<IllegalArgumentException> {
            CountedMineBoard(FOUR_ELEMENTS_CLEAN_MINE_BOARD)
        }
    }
}) {
    companion object {
        private val FOUR_ELEMENTS_CLEAN_MINE_BOARD = MineBoard(
            mapOf(
                Position(0, 0) to Safety(),
                Position(1, 1) to Safety(),
                Position(0, 1) to Safety(),
                Position(1, 0) to Safety(),
            )
        )
    }
}
