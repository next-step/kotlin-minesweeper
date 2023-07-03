package model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import model.minemark.Mine
import model.minemark.Safety

@DisplayName("지뢰판 제공자")
class MineBoardProviderTest : StringSpec({

    "가로와 세로, 생성 마크로 생성 가능" {
        shouldNotThrowAny {
            MineBoardProvider(1, 1, Mine)
        }
    }

    "가로 세로는 모두 0보다 커야함" {
        listOf(
            0 to 1,
            1 to 0,
            -1 to 1,
            1 to -1,
        ).forAll {
            shouldThrowExactly<IllegalArgumentException> {
                MineBoardProvider(it.first, it.second)
            }
        }
    }

    "지뢰 보드 제공 가능" {
        // given & when
        val mineBoard = MineBoardProvider(1, 1, Safety).mineBoard
        // then
        mineBoard shouldBe MineBoard(BoardElements(listOf(BoardElement(Position(0, 0), Safety))))
    }
})
