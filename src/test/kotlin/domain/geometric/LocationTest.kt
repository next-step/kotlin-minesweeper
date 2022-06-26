package domain.geometric

import domain.location
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LocationTest : DescribeSpec({

    describe("위치는") {
        context("0 보다 크거나 같은 가로, 세로 값이 주어지면") {
            val row = 10
            val column = 10
            it("정상 위치를 반환한다") {
                Location.ofOrNull(row, column) shouldBe location(10, 10)
            }
        }
        context("0보다 작은 가로, 세로 값이 주어지면") {
            val table = io.kotest.data.table(
                headers("row", "column"),
                row(-1, 0),
                row(0, -1),
                row(-1, -1)
            )
            it("null을 반환한다") {
                table.forAll { row, column ->
                    Location.ofOrNull(row, column) shouldBe null
                }
            }
        }
    }
})
