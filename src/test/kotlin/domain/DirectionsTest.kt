package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class DirectionsTest : StringSpec({
    "동서남북의 대한 방향만 가져올 수 있다." {
        val fourDirection = Directions.getFourDirection()
        fourDirection shouldBe listOf(Directions.UP, Directions.RIGHT, Directions.DOWN, Directions.LEFT)
    }
})
