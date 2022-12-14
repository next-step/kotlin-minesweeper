package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlockTest : StringSpec({

    "블록은 'c' 로 표시한다." {
        Block.Normal().blockText shouldBe "c"
    }

    "지뢰는 '*' 로 표시한다." {
        Block.LandMine().blockText shouldBe "*"
    }
})
