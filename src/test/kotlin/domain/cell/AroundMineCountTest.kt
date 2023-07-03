package domain.cell

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class AroundMineCountTest : StringSpec({

    "음수가 입력되면 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> { AroundMineCount(-1) }
    }
})
