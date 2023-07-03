package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import model.minemark.Mine
import model.minemark.MineCount

@DisplayName("세어진 지뢰 보드")
class CountedMineBoardTest : StringSpec({

    "지뢰 보드로 생성" {
        shouldNotThrowAny {
            CountedMineBoard(
                MineBoard(
                    BoardElements(
                        listOf(
                            BoardElement(Position(0, 0), MineCount(2)),
                            BoardElement(Position(1, 1), Mine),
                            BoardElement(Position(0, 1), MineCount(2)),
                            BoardElement(Position(1, 0), Mine),
                        )
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
})
