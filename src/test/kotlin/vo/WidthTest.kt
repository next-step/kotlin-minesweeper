package vo

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

internal class WidthTest : FreeSpec({

    "에러" - {
        "너비는 음수가 될 수 없다" {
            shouldThrow<IllegalArgumentException> {
                Width(-1)
            }
        }

        "너비는 0 이 될 수 없다" {
            shouldThrow<IllegalArgumentException> {
                Width(-0)
            }
        }
    }

    "성공" - {
        "1 이상의 숫자를 입력하면 성공한다" {
            val value = (1..100).random()
            Width(value)
        }
    }
})
