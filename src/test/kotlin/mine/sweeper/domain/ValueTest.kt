package mine.sweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

class ValueTest : StringSpec({

    "맵 높이가 0보다 낮다면 IllegalArgument 에외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Height(0)
        }
    }

    "맵 너비가 0보다 낮다면 IllegalArgument 에외가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Width(0)
        }
    }

    "지뢰가 하나도 없다면 IllegalArgument 에외가 발생한다" {
        shouldThrow<IllegalArgumentException> {
            MineCount(0)
        }
    }
})
