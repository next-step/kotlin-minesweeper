package domain.geometric

import domain.location
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class LocationTest : DescribeSpec({

    describe("위치는") {
        val location = location(3, 3)
        context("다른 위치보다 가로 값와 세로 값이 같거나 작으면") {
            val others = listOf(location(5, 5), location(3, 3))
            it("안쪽 위치로 판단한다") {
                others.forAll {
                    location.isLocatedInside(it) shouldBe true
                }
            }
        }
        context("다른 위치보다 가로 값와 세로 값이 같거나 크면") {
            val others = listOf(location(0, 0), location(3, 3))
            it("바깥쪽 위치로 판단한다") {
                others.forAll {
                    location.isLocatedOutside(it) shouldBe true
                }
            }
        }
    }
})
