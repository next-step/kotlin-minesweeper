package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec

class MineGameMetricTest : DescribeSpec({
    describe("지뢰찾기 입력값을 검증하고 메타데이터를 생성한다.") {
        context("보드의 높이가 1보다 작은 경우") {
            it("throw an exception") {
                shouldThrow<IllegalArgumentException> {
                    MineGameMetric(0, 1, 1)
                }
            }
        }

        context("보드의 너비가 1보다 작은 경우") {
            it("throw an exception") {
                shouldThrow<IllegalArgumentException> {
                    MineGameMetric(1, 0, 1)
                }
            }
        }

        context("지뢰 개수가 전체 셀 개수보다 많은 경우") {
            it("throw an exception") {
                shouldThrow<IllegalArgumentException> {
                    MineGameMetric(2, 2, 5)
                }
            }
        }
    }
})
