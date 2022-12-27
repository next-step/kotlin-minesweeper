package minesweeper.domain.position

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.position.data.PositionDataSet

class PositionsTest : FunSpec({
    context("Positions객체가 정상적으로 생성된다.") {
        withData(
            nameFn = { "count: $it" },
            (0..100)
        ) { count ->
            val positionList = PositionDataSet.testData(count)

            val positions = Positions(positionList)
            positions shouldNotBe null
            positions shouldContainAll positionList
            positions.size shouldBe count

            positionList.forEach { position ->
                positions.havePosition(position) shouldBe true
                positions.havePosition(position.row, position.col) shouldBe true
            }
        }
    }
})
