package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RandomLocationGeneratorTest : StringSpec({
    "size만큼 랜덤값을 생선한다." {
        val locations = RandomLocationGenerator.location(5, 6, 7)
        locations.size shouldBe 5
    }
})
