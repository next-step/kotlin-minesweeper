package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SpotTest : StringSpec({
    "지뢰인지 아닌지 여부를 반환할 수 있다." {
        val mineSpot = MineSpot(FieldHeight(1), FieldWidth(1))
        val safeSpot = SafeSpot(FieldHeight(2), FieldWidth(3))

        mineSpot.isMine() shouldBe true
        safeSpot.isMine() shouldBe false
    }
})
