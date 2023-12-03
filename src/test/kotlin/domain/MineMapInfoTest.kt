package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row

class MineMapInfoTest : StringSpec({

    "잘못된 입력이 들어오면 예외가 발생한다" {
        forAll(
            row(0, 10, 4),
            row(10, 0, 4),
            row(10, 10, -1),
            row(10, 10, 100)
        ) { height, width, mineCount ->
            shouldThrow<IllegalArgumentException> {
                MineMapInfo(Point(height, width), mineCount)
            }
        }
    }
})
