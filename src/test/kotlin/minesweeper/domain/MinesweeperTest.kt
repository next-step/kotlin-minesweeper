package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.PositiveNumber.Companion.asPositiveNumber

class MinesweeperTest : FreeSpec({

    "높이, 넓이, 지뢰 개수를 입력 받는다." {
        shouldNotThrowAny {
            Minesweeper(Dimension(10.asPositiveNumber(), 10.asPositiveNumber()), 5.asPositiveNumber())
        }
    }

    "높이, 넓이 사이즈 보다 지뢰 갯수가 많을 수 없다." {
        val throws = shouldThrow<IllegalArgumentException> {
            Minesweeper(Dimension(5.asPositiveNumber(), 5.asPositiveNumber()), 30.asPositiveNumber())
        }

        throws.message shouldBe "지뢰 갯수는 전체 블럭 넓이 보다 많을 수 없습니다."
    }
})
