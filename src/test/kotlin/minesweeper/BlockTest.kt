package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlockTest : StringSpec({

    "블록은 'C' 로 표시한다." {
        Block.Normal(1).blockText shouldBe "C"
    }

    "지뢰는 '*' 로 표시한다." {
        Block.LandMine(1).blockText shouldBe "*"
    }
})
