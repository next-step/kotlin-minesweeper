package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

class SpotTest : StringSpec({
    "지뢰인지 아닌지 여부를 반환할 수 있다." {
        val mineSpot = MineSpot(Position(1, 1))
        val safeSpot = SafeSpot(Position(2, 3))

        mineSpot.isMine() shouldBe true
        safeSpot.isMine() shouldBe false
    }

    "스팟을 열 수 있다." {
        val mineSpot = MineSpot(Position(1, 1))
        val safeSpot = SafeSpot(Position(2, 3))

        safeSpot.open()

        safeSpot.isOpened() shouldBe true
        mineSpot.isOpened() shouldBe false
    }

    "이미 열려 있는 칸을 열면 AlreadyOpened를 반환한다." {
        val safeSpot = SafeSpot(Position(2, 3))
        safeSpot.open()

        safeSpot.open() shouldBeSameInstanceAs OpenResult.AlreadyOpened
    }
})
