package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeInRange

class RandomPositionTest : StringSpec({

    "랜덤 값은 범위안에 무조건 들어와야 한다" {
        val randomPosition = RandomPosition.of(10, 10)
        randomPosition.first shouldBeInRange IntRange(0, 10)
        randomPosition.second shouldBeInRange IntRange(0, 10)
    }
})
