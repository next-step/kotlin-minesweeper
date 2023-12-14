package minesweeper.model.vison.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import minesweeper.model.board.toBoardLimit

class VisionCoveredStrategyTest : StringSpec({

    "확인이 필요하다 // 테스트한다" {
        val limit = (4 to 4).toBoardLimit()
        val actual = VisionCoveredStrategy.coordinates(limit)
//        actual shouldHaveSize 25
        actual shouldHaveSize 16
    }
})
