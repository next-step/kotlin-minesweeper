package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LandTest : StringSpec({
    "Land 생성 성공" {
        val count = 10
        val land = Land(10, 10, count)
        land.mines.size shouldBe count
    }
})
