package domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec

class MineSweeperMetricTest : DescribeSpec({
    describe("사용자 입력에 대한 유효성 검증") {
        context("높이가 1보다 작은 경우") {
            it("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 0, mineBoardWidth = 10, mineCount = 10)
                }
            }
        }

        context("높이가 1이상인 경우") {
            it("should not throw exception") {
                shouldNotThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 1, mineBoardWidth = 10, mineCount = 10)
                }
            }
        }

        context("너비가 1보다 작은 경우") {
            it("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 10, mineBoardWidth = 0, mineCount = 10)
                }
            }
        }

        context("너비가 1이상인 경우") {
            it("should not throw exception") {
                shouldNotThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 10, mineBoardWidth = 1, mineCount = 10)
                }
            }
        }

        context("지뢰 개수가 1보다 작은 경우") {
            it("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 10, mineBoardWidth = 10, mineCount = 0)
                }
            }
        }

        context("지뢰 개수가 1이상인 경우") {
            it("should not throw exception") {
                shouldNotThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 10, mineBoardWidth = 1, mineCount = 10)
                }
            }
        }

        context("지뢰 개수가 전체 칸보다 많은 경우") {
            it("should throw exception") {
                shouldThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 2, mineBoardWidth = 2, mineCount = 5)
                }
            }
        }

        context("지뢰 개수가 전체 칸보다 적은 경우") {
            it("should throw exception") {
                shouldNotThrow<IllegalArgumentException> {
                    MineSweeperMetric(mineBoardHeight = 2, mineBoardWidth = 2, mineCount = 1)
                }
            }
        }
    }
})
