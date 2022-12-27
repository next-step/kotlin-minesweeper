package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RandomCoordinateGeneratorTest : StringSpec({

    val randomCoordinateGenerator = RandomCoordinateGenerator()

    "랜덤 좌표 생성기 개수 테스트" {
        // given
        val size = 5
        // when
        val actual = randomCoordinateGenerator.generate(7, 12, size)
        // then
        actual.size shouldBe size
    }
})
