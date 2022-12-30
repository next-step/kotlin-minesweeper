package minesweeper.domain.position.vendor

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertThrows

class PositionVendorTest : FunSpec({
    context("높이와 너비를 통해 PositionVendor 정상적으로 생성된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(10, 10, 100),
                Tuple3(10, 10, 0),
                Tuple3(25, 10, 50),
                Tuple3(20, 30, 400),
            )
        ) { (height, width, validCount) ->
            val positionVendor = PositionVendor(height, width)

            val positions = positionVendor.getPositions(validCount)

            val remainPositions = positionVendor.getPositionsExcluding(positions)

            positionVendor shouldNotBe null
            positions shouldHaveSize validCount
            positions.toSet() shouldHaveSize validCount
            positions.intersect(remainPositions) shouldHaveSize 0
            remainPositions shouldHaveSize positionVendor.size - validCount
        }
    }

    context("높이와 너비가 0이하 일때, IllegalArgumentException 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                Pair(-10, 10),
                Pair(10, -10),
                Pair(10, 0),
                Pair(0, 10),
            )
        ) { (height, width) ->
            assertThrows<IllegalArgumentException> {
                PositionVendor(height, width)
            }
        }
    }

    context("PositionVendor.getPositions 메서드를 음수 파라미터 전달시, IllegalArgument 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, -10),
                Tuple3(10, 10, -100),
                Tuple3(10, 10, -5),
                Tuple3(25, 10, -50),
                Tuple3(20, 30, -400),
            )
        ) { (height, width, validCount) ->
            val positionVendor = PositionVendor(height, width)

            assertThrows<IllegalArgumentException> {
                positionVendor.getPositions(validCount)
            }
        }
    }
})
