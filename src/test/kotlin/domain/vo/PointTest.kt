package domain.vo

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

internal class PointTest : FreeSpec({

    "에러" - {
        "위치는 음수가 될 수 없다" {
            shouldThrow<IllegalArgumentException> {
                Point(-1)
            }
        }

        "위치는 0 이 될 수 없다" {
            shouldThrow<IllegalArgumentException> {
                Point(-0)
            }
        }
    }

    "성공" - {
        "1 이상의 숫자를 입력하면 성공한다" {
            val point = (1..100).random()
            Point(point)
        }
    }
})
