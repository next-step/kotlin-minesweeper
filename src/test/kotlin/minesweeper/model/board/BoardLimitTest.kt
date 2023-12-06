package minesweeper.model.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BoardLimitTest : StringSpec({

    "입력된 Limit 정보를 바탕으로 area (영역의 크기) 를 반환해야 한다" {
        val limit = (7 to 4).toBoardLimit()

        limit.area shouldBe 28
    }
})
