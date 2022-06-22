package domain

import domain.geometric.Dimension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MatrixTest : DescribeSpec({

    describe("행렬은") {
        it("차원의 넓이만큼 칸을 가진다") {
            val dimension = Dimension(5, 5)
            val matrix = Matrix(dimension)
            matrix.cells.size shouldBe 25
        }
    }
})
