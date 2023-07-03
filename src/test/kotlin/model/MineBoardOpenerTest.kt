package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import model.minemark.Mine
import model.minemark.MineCount
import model.minemark.OpenStatus
import view.OutputView

@DisplayName("지뢰 보드 오프너")
class MineBoardOpenerTest : StringSpec({

    "세어진 지뢰보드로 생성" {
        shouldNotThrowAny {
            MineBoardOpener(NINE_ELEMENTS_TWO_MINE_COUNTED_BOARD)
        }
    }

    "세어진 지뢰보드를 열 수 있음" {
        // given
        val mineBoardOpener = MineBoardOpener(NINE_ELEMENTS_TWO_MINE_COUNTED_BOARD)
        // when
        val opened: CountedMineBoard = mineBoardOpener.opened(Position(0, 0))
        // then
        opened shouldBe CountedMineBoard(
            MineBoard(
                mapOf(
                    Position(0, 0) to MineCount(1, OpenStatus.OPENED),
                    Position(1, 0) to Mine(),
                    Position(2, 0) to Mine(),
                    Position(0, 1) to MineCount(1),
                    Position(1, 1) to MineCount(2),
                    Position(2, 1) to MineCount(2),
                    Position(0, 2) to MineCount(0),
                    Position(1, 2) to MineCount(0),
                    Position(2, 2) to MineCount(0),
                )
            )
        )
    }

    "0을 선택하면 주변의 숫자까지 같이 열림" {
        // given
        val mineBoardOpener = MineBoardOpener(NINE_ELEMENTS_TWO_MINE_COUNTED_BOARD)
        // when
        val opened: CountedMineBoard = mineBoardOpener.opened(Position(1, 2))
        OutputView.printBoard(opened)
        // then
        opened shouldBe CountedMineBoard(
            MineBoard(
                mapOf(
                    Position(0, 0) to MineCount(1),
                    Position(1, 0) to Mine(),
                    Position(2, 0) to Mine(),
                    Position(0, 1) to MineCount(1, OpenStatus.OPENED),
                    Position(1, 1) to MineCount(2, OpenStatus.OPENED),
                    Position(2, 1) to MineCount(2, OpenStatus.OPENED),
                    Position(0, 2) to MineCount(0, OpenStatus.OPENED),
                    Position(1, 2) to MineCount(0, OpenStatus.OPENED),
                    Position(2, 2) to MineCount(0, OpenStatus.OPENED),
                )
            )
        )
    }
})
