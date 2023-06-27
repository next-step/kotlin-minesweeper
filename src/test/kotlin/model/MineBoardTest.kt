package model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

@DisplayName("지뢰 보드")
class MineBoardTest : StringSpec({

    "포지션의 마크들로 생성" {
        shouldNotThrowAny {
            MineBoard(mapOf(Position(0, 0) to MineMark.SAFETY))
        }
    }

    "모든 포지션은 연속적이고 존재해야 함" {
        shouldThrowExactly<IllegalArgumentException> {
            MineBoard(
                mapOf(
                    Position(0, 0) to MineMark.SAFETY,
                    Position(10, 10) to MineMark.SAFETY,
                )
            )
        }
    }

    "주어진 마크 정보로 조회 가능" {
        listOf(
            mapOf(Position(0, 0) to MineMark.SAFETY),
            mapOf(Position(0, 0) to MineMark.MINE),
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
            MineMark.SAFETY to true,
            MineMark.MINE to false,
        ).forAll {
            FOUR_ELEMENTS_CLEAN_MINE_BOARD.isEqualMarkInPosition(Position(0, 0), it.first) shouldBe it.second
        }
    }

    "원하는 위치 마크 교체 가능" {
        // given
        val mineBoard = MineBoard(mapOf(Position(0, 0) to MineMark.SAFETY))
        // when
        val replacedMark = mineBoard.replacedMark(Position(0, 0), MineMark.MINE)
        // then
        replacedMark shouldBe MineBoard(mapOf(Position(0, 0) to MineMark.MINE))
    }
})

val FOUR_ELEMENTS_CLEAN_MINE_BOARD = MineBoard(
    mapOf(
        Position(0, 0) to MineMark.SAFETY,
        Position(1, 1) to MineMark.SAFETY,
        Position(0, 1) to MineMark.SAFETY,
        Position(1, 0) to MineMark.SAFETY,
    )
)
