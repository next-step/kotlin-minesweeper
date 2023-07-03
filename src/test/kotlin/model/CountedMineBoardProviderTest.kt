package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import model.minemark.Mine
import model.minemark.MineCount

@DisplayName("지뢰 찾기")
class CountedMineBoardProviderTest : StringSpec({

    "설치된 지뢰 보드로 생성 가능" {
        shouldNotThrowAny {
            CountedMineBoardProvider(InstalledMineBoard(FOUR_ELEMENTS_TWO_MINE_BOARD))
        }
    }

    "설치된 지뢰보드로 지뢰 개수 파악하기" {
        // given
        val countedMineBoardProvider = CountedMineBoardProvider(InstalledMineBoard(FOUR_ELEMENTS_TWO_MINE_BOARD))
        // when
        val countedMineBoard: CountedMineBoard = countedMineBoardProvider.countedMineBoard
        // then
        countedMineBoard shouldBe CountedMineBoard(
            MineBoard(
                mapOf(
                    Position(0, 0) to MineCount(2),
                    Position(1, 1) to Mine,
                    Position(0, 1) to MineCount(2),
                    Position(1, 0) to Mine,
                )
            )
        )
    }
})
