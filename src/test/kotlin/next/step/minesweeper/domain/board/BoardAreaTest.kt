package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.position.Position

class BoardAreaTest : DescribeSpec({

    describe("BoardArea") {
        val area = BoardArea.of(3, 3)

        context("position이 area를 벗어나면 false") {
            withData(
                listOf(Position(-1, -1), Position(3, 3))
            ) { position ->
                (position in area) shouldBe false
            }
        }
        context("position이 area안에 있으면 true") {
            withData(
                listOf(Position(0, 0), Position(2, 2))
            ) { position ->
                (position in area) shouldBe true
            }
        }
        context("y가 height를 벗어나면 false") {
            withData(
                listOf(-1, 3)
            ) { y ->
                area.inHeight(y) shouldBe false
            }
        }
        context("y가 height안에 있으면 true") {
            withData(
                listOf(0, 2)
            ) { y ->
                area.inHeight(y) shouldBe true
            }
        }
        context("x가 width를 벗어나면 false") {
            withData(
                listOf(-1, 3)
            ) { x ->
                area.inWidth(x) shouldBe false
            }
        }
        context("x가 width안에 있으면 true") {
            withData(
                listOf(0, 2)
            ) { x ->
                area.inWidth(x) shouldBe true
            }
        }
        context("canHave") {
            it("area에 특정 개수를 포함할 수 있으면 true") {
                area.canHave(9) shouldBe true
            }
            it("area에 특정 개수를 포함할 수 없으면 false") {
                area.canHave(10) shouldBe false
            }
        }
        context("method") {
            it("넓이 제공") {
                area.area() shouldBe 9
            }
            it("너비 제공") {
                area.width() shouldBe 3
            }
            it("높이 제공") {
                area.height() shouldBe 3
            }
        }
    }

})
