package minesweeper.domain.flag

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class FlagTest : StringSpec({

    "플래그에서 상태를 바꿀 수 있으며, 현재 상태를 알 수 있다." {
        val blockFlag = BlockFlag()
        blockFlag.getCurrentState() shouldBe "0"

        blockFlag.updateAroundMineCount(aroundMineCount = 8)
        blockFlag.getCurrentState() shouldBe "8"

        MineFlag().getCurrentState() shouldBe "*"
    }

    "플래그에서 상태를 바꿀 수 있는 값은 0부터 8까지다." {
        forAll(
            row(10),
            row(-1),
            row(100)
        ) { mineCount ->
            val blockFlag = BlockFlag()

            val exception = shouldThrow<IllegalStateException> {
                blockFlag.updateAroundMineCount(aroundMineCount = mineCount)
            }

            exception shouldHaveMessage "블록 주변 지뢰 개수는 ${0 until 9} 범위여야 합니다. 입력값 : $mineCount"
        }
    }
})
