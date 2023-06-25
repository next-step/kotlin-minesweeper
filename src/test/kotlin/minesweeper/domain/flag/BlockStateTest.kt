package minesweeper.domain.flag

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class BlockStateTest : StringSpec({

    "주변 지뢰 개수로 블록 상태로 변환할 수 있다." {
        forAll(
            row(0),
            row(1),
            row(2),
            row(3),
            row(4),
            row(5),
            row(6),
            row(7),
            row(8),
        ) { value ->
            val blockState = BlockState.valueOf(aroundMineCount = value)
            blockState.value shouldBe value
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
