package minesweeper.domain.position

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.view.toContentString
import org.junit.jupiter.api.assertThrows

class PositionTest : FunSpec({

    context("Position 객체가 정상적으로 생성된다") {
        val givenHeight = 20
        val givenWidth = 20

        withData(
            nameFn = { "$it" },
            (givenHeight.indexRange() comma givenWidth.indexRange())
                .map { it.row to it.col }
        ) { (row, col) ->
            row shouldBeGreaterThanOrEqual 0
            col shouldBeGreaterThanOrEqual 0

            val position = Position(row, col)

            position shouldNotBe null
            position.row shouldBe row
            position.col shouldBe col
        }
    }

    context("음수 row 혹은 음수 col일 경우, Position 생성시 IllegalArgumentException 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                -1 to 0,
                2 to -3,
                -4 to -2
            )
        ) { (row, col) ->
            assertThrows<IllegalArgumentException> {
                Position(row, col)
            }
        }
    }

    context("Position::getAroundPositions 메서드가 특정 position 기준으로 주변 8방향 position들 중 유효한 것들만 리턴한다.") {
        withData(
            nameFn = { "position: ${it.first}, expectedAroundPositions: ${it.second.toPositions().toContentString()}" },
            listOf(
                0 comma 0 to listOf(0 comma 1, 1 comma 0, 1 comma 1),
                1 comma 0 to listOf(0 comma 0, 0 comma 1, 1 comma 1, 2 comma 0, 2 comma 1),
                0 comma 1 to listOf(0 comma 0, 0 comma 2, 1 comma 0, 1 comma 1, 1 comma 2),
                1 comma 1 to listOf(0 comma 0, 0 comma 1, 0 comma 2, 1 comma 0, 1 comma 2, 2 comma 0, 2 comma 1, 2 comma 2),
                3 comma 4 to listOf(2 comma 3, 2 comma 4, 2 comma 5, 3 comma 3, 3 comma 5, 4 comma 3, 4 comma 4, 4 comma 5)
            )
        ) { (position, expected) ->

            val aroundPositions: List<Position> = position.getAroundPositions()

            aroundPositions shouldContainAll expected
            aroundPositions.toSet() shouldHaveSize expected.toSet().size

            println(aroundPositions.toPositions().toContentString())
        }
    }
})
