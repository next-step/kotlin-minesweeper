package vo

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec

internal class HeightTest : FreeSpec({

    "에러" - {
        "높이는 음수가 될 수 없다" {
            shouldThrow<IllegalArgumentException> {
                Height(-1)
            }
        }

        "높이는 0 이 될 수 없다" {
            shouldThrow<IllegalArgumentException> {
                Height(-0)
            }
        }
    }

    "성공" - {
        "1 이상의 숫자를 입력하면 성공한다" {
            val value = (1..100).random()
            Height(value)
        }
    }
})
