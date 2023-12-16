package minesweeper.model.vison.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import minesweeper.model.board.toBoardLimit

class VisionCoveredStrategyTest : StringSpec({

    "VisionTotalCoveringStrategy 는 모든 Point 를 덮어야 한다" {
        val limit = (4 to 4).toBoardLimit()
        val actual = VisionTotalCoveringStrategy.coordinates(limit)

        actual shouldHaveSize 16
    }
})
