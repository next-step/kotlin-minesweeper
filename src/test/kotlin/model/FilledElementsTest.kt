package model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.minemark.Mine
import model.minemark.MineMark
import model.minemark.Safety

@DisplayName("가득 찬 요소들")
class FilledElementsTest : StringSpec({

    "포지션의 마크들로 생성" {
        shouldNotThrowAny {
            FilledElements(mapOf(Position(0, 0) to Safety()))
        }
    }

    "모든 포지션은 연속적이고 존재해야 함" {
        shouldThrowExactly<IllegalArgumentException> {
            FilledElements(
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
            FilledElements(it).elements shouldBe it
        }
    }

    "원하는 대상을 변경할 수 있음" {
        // given
        val mineMapper: (Position, MineMark) -> MineMark = { _, _ -> Mine() }
        // when
        val replacedMarkElements = FOUR_ELEMENTS_CLEAN_MINE_BOARD.replacedMarkElements(mineMapper)
        // then
        replacedMarkElements shouldBe FilledElements(
            mapOf(
                Position(0, 0) to Mine(),
                Position(1, 1) to Mine(),
                Position(0, 1) to Mine(),
                Position(1, 0) to Mine(),
            )
        )
    }

    "원하는 대상의 개수를 셀 수 있음" {
        // given
        val safetyPredicate: (Position, MineMark) -> Boolean = { _, mineMark -> mineMark.isSafety }
        // when then
        FOUR_ELEMENTS_CLEAN_MINE_BOARD.count(safetyPredicate) shouldBe 4
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

    "마크 포함 여부 확인 가능" {
        listOf(
            Mine() to true,
            Safety() to false,
        ).forAll {
            FOUR_ELEMENTS_CLEAN_MINE_BOARD.doesNotContainsMark(it.first) shouldBe it.second
        }
    }
}) {
    companion object {
        private val FOUR_ELEMENTS_CLEAN_MINE_BOARD = FilledElements(
            mapOf(
                Position(0, 0) to Safety(),
                Position(1, 1) to Safety(),
                Position(0, 1) to Safety(),
                Position(1, 0) to Safety(),
            )
        )
    }
}
