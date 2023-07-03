package next.step.minesweeper.utils

import io.kotest.assertions.fail
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class RetryUtilsKtTest : DescribeSpec({

    describe("RetryUtils") {
        context("retryOnFailure") {
            it("예외 없으면 바로 리턴") {
                retryOnFailure({ 1 }) { fail("호출 안됨") } shouldBe 1
            }
            it("예외 있으면 실패 콜백 호출 후 재시도") {
                val numbers = mutableListOf(1, 2)
                retryOnFailure({ if (numbers.removeAt(0) == 1) throw IllegalArgumentException("test") else "success" }) {
                    it.message shouldBe "test"
                } shouldBe "success"
            }
        }
    }
})
