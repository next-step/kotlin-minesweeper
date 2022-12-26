package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Block

class BlockTest : StringSpec({

    "블록은 'C' 로 표시한다." {
        Block.Normal().blockText shouldBe "C"
    }

    "지뢰는 '*' 로 표시한다." {
        Block.LandMine().blockText shouldBe "*"
    }
})
