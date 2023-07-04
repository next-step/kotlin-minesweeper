package model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.minemark.Mine
import model.minemark.MineCount
import model.minemark.Safety

@DisplayName("지뢰 보드")
class MineBoardTest : StringSpec({

    "포지션의 마크들로 생성" {
        shouldNotThrowAny {
            MineBoard(mapOf(Position(0, 0) to Safety()))
        }
    }

    "모든 포지션은 연속적이고 존재해야 함" {
        shouldThrowExactly<IllegalArgumentException> {
            MineBoard(
                mapOf(
                    Position(0, 0) to Safety(),
                    Position(10, 10) to Safety(),
                )
            )
        }
    }

    "주어진 마크 정보로 조회 가능" {
        listOf(
            mapOf(Position(0, 0) to Safety()),
            mapOf(Position(0, 0) to Mine()),
        ).forAll {
            MineBoard(it).elements shouldBe it
        }
    }

    "포지션의 마크들의 총 개수 조회" {
        FOUR_ELEMENTS_CLEAN_MINE_BOARD.size shouldBe 4
    }

    "가장 큰 x, y 좌표 조회 가능" {
        assertSoftly(FOUR_ELEMENTS_CLEAN_MINE_BOARD) {
            maxXPosition shouldBe 1
            maxYPosition shouldBe 1
        }
    }

    "포지션 마크 일치 여부 조회 가능" {
        listOf(
            Safety() to true,
            Mine() to false,
        ).forAll {
            FOUR_ELEMENTS_CLEAN_MINE_BOARD.isEqualMarkInPosition(Position(0, 0), it.first) shouldBe it.second
        }
    }

    "원하는 위치 마크 교체 가능" {
        // given
        val mineBoard = MineBoard(mapOf(Position(0, 0) to Safety()))
        // when
        val replacedMark = mineBoard.replacedMark(Position(0, 0), Mine())
        // then
        replacedMark shouldBe MineBoard(mapOf(Position(0, 0) to Mine()))
    }

    "주어진 포지션들의 지뢰 개수 조회 가능" {
        // given
        val positions = listOf(Position(0, 0), Position(1, 1))
        // when
        val mineCount = FOUR_ELEMENTS_TWO_MINE_BOARD.mineCount(positions)
        // then
        mineCount shouldBe 1
    }

    "안전 지대의 모든 마크들을 포지션으로 지뢰 개수로 변경할 수 있음" {
        // given & when
        val replaced = FOUR_ELEMENTS_TWO_MINE_BOARD.replacedOnlySafetyMarks { _ -> 2 }
        // then
        replaced shouldBe MineBoard(
            mapOf(
                Position(0, 0) to MineCount(2),
                Position(1, 1) to Mine(),
                Position(0, 1) to MineCount(2),
                Position(1, 0) to Mine(),
            )
        )
    }

    "마크 포함 여부 확인 가능" {
        listOf(
            Mine() to true,
            Safety() to false,
        ).forAll {
            FOUR_ELEMENTS_CLEAN_MINE_BOARD.doesNotContainsMark(it.first) shouldBe it.second
        }
    }
})

val FOUR_ELEMENTS_CLEAN_MINE_BOARD = MineBoard(
    mapOf(
        Position(0, 0) to Safety(),
        Position(1, 1) to Safety(),
        Position(0, 1) to Safety(),
        Position(1, 0) to Safety(),
    )
)

val FOUR_ELEMENTS_TWO_MINE_BOARD = MineBoard(
    mapOf(
        Position(0, 0) to Safety(),
        Position(1, 1) to Mine(),
        Position(0, 1) to Safety(),
        Position(1, 0) to Mine(),
    )
)
