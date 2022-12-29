package minesweeper.domain.position

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PositionsTest : FunSpec({

    context("Positions객체가 정상적으로 생성된다.") {
        val givenHeight = 10
        val givenWidth = 10
        val givenMaxTotalCount = givenHeight * givenWidth

        val givenRowIndexRange = Position.indexRange(givenHeight)
        val givenColIndexRange = Position.indexRange(givenWidth)

        val givenAllPositions = givenRowIndexRange comma givenColIndexRange

        withData(
            nameFn = { "count: ${it.size}" },
            (0..givenMaxTotalCount).map { givenAllPositions.shuffled().take(it) }
        ) { givenPositionList ->

            val positions = Positions(givenPositionList)
            positions shouldNotBe null
            positions shouldContainAll givenPositionList
            positions shouldHaveSize  givenPositionList.size

            givenPositionList.forEach { position ->
                positions.havePosition(position) shouldBe true
                positions.havePosition(position.row, position.col) shouldBe true
            }
        }
    }
})
