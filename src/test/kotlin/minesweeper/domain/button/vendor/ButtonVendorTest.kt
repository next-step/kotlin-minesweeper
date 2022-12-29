package minesweeper.domain.button.vendor

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ButtonVendorTest : FunSpec({
    context("높이와 너비를 통해 PositionVendor과 ButtonVendor가 정상적으로 생성된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(10, 10, 100),
                Tuple3(10, 10, 0),
                Tuple3(25, 10, 50),
                Tuple3(20, 30, 400),
            )
        ) { (height, width, mineCount) ->
            val buttonVendor = ButtonVendor(height, width)

            val buttonGraph = buttonVendor.getButtonGraph(mineCount = mineCount)

            buttonVendor shouldNotBe null
            buttonGraph shouldNotBe null
            buttonGraph.isNotEmpty() shouldBe true
            buttonGraph.mines shouldHaveSize mineCount
        }
    }
})
