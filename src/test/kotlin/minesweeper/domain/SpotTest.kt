package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SpotTest : StringSpec({
    "지뢰인지 아닌지 여부를 반환할 수 있다." {
        val mineSpot = MineSpot(Position(1, 1))
        val safeSpot = SafeSpot(Position(2, 3), 0)

        mineSpot.isMine() shouldBe true
        safeSpot.isMine() shouldBe false
    }

    "스팟을 열 수 있다." {
        val mineSpot = MineSpot(Position(1, 1))
        val safeSpot = SafeSpot(Position(2, 3), 0)

        safeSpot.open()

        safeSpot.isOpened() shouldBe true
        mineSpot.isOpened() shouldBe false
    }

    "이미 열려 있는 칸은 다시 열 수 없다." {
        val safeSpot = SafeSpot(Position(2, 3), 0)

        safeSpot.open()

        val exception =
            shouldThrow<IllegalArgumentException> {
                safeSpot.open()
            }

        exception.message shouldBe "이미 열린 칸입니다."
    }
})
