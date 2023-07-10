package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeInRange

fun Int.toNumber() = Number.of(this.toString())

class MineGeneratorTest : StringSpec({
    "생성되는 지뢰의 위치는 높이와 넓이보다 작아야한다" {
        val width = 10.toNumber()
        val height = 10.toNumber()
        val position = MineGenerator.generate(width, height)

        position.x.value shouldBeInRange 0.until(width.value)
        position.y.value shouldBeInRange 0.until(height.value)
    }
})
