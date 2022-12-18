package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

internal class MineCntTest : StringSpec({
    "마인의 개수에 양수 값이 값이 주어지면, 정상적으로 생성된다." {
        checkAll(Arb.int(1..45)) { number ->
            shouldNotThrowAny {
                MineCnt(number)
            }
        }
    }

    "마인의 개수에 양수가 아닌 값이 주어지면, 예외를 던진다." {
        checkAll(Arb.int(-45..0)) { number ->
            shouldThrow<IllegalArgumentException> {
                MineCnt(number)
            }
        }
    }
})
