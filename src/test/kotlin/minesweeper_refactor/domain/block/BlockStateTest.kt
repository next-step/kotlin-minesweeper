package minesweeper_refactor.domain.block

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class BlockStateTest : StringSpec({

    "주변 지뢰 개수로 블록 상태로 변환할 수 있다." {
        forAll(
            row(0, BlockState.ZERO),
            row(1, BlockState.ONE),
            row(2, BlockState.TWO),
            row(3, BlockState.THREE),
            row(4, BlockState.FOUR),
            row(5, BlockState.FIVE),
            row(6, BlockState.SIX),
            row(7, BlockState.SEVEN),
            row(8, BlockState.EIGHT),
        ) { value, expect ->
            val blockState = BlockState.valueOf(aroundMineCount = value)
            blockState shouldBe expect
        }
    }

    "정해진 주변 지뢰 개수가 아닌 상태로 블록 상태로 변환을 하면 0 ~ 8 범위만 가능하다는 에러가 발생한다." {
        forAll(
            row(-1),
            row(10),
            row(20),
            row(9),
        ) { value ->
            val exception = shouldThrow<IllegalArgumentException> {
                BlockState.valueOf(aroundMineCount = value)
            }

            exception shouldHaveMessage "주변 지뢰는 0 ~ 8 범위의 개수만 가질 수 있습니다. 입력값 : $value"
        }
    }
})
