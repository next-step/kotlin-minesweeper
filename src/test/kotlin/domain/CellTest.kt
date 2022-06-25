package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellTest : DescribeSpec({

    describe("칸은") {
        context("지뢰를 심을 위치가 주어졌을 때") {
            val miningLocations = locations(
                0 to 0, 1 to 1
            )
            it("생성될 위치가 지뢰를 심을 위치에 해당하면 지뢰 칸을 생성한다") {
                val loc = location(0, 1)
                Cell.of(loc, miningLocations) shouldBe Cell.safe(loc)
            }
            it("생성될 위치가 지뢰를 심을 위치가 아니라면 안전 칸을 생성한다") {
                val loc = location(1, 1)
                Cell.of(loc, miningLocations) shouldBe Cell.mine(loc)
            }
        }
    }
})
