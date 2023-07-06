package domain.cell

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CellTest : StringSpec({

    "mine은 오픈할 수 있다" {
        val mine = Cell.hideMine().open()
        (mine.isOpen()) shouldBe true
    }

    "ground는 오픈할 수 있다" {
        val ground = Cell.hideGround(AroundMineCount(1)).open()
        (ground.isOpen()) shouldBe true
    }
})
