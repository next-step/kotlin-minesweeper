package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class MapElementTest : BehaviorSpec({
    forAll(
        table(
            headers("value"),
            row(-1),
            row(9),
        ),
    ) { value ->
        Given("${value}가 주어졌다") {
            When("해당 숫자에 해당하는 map element를 구하면") {
                Then("예외가 던져진다") {
                    shouldThrow<IllegalArgumentException> { MapElement.of(value) }
                }
            }
        }
    }

    forAll(
        table(
            headers("value", "예상결과"),
            row(0, MapElement.ZERO),
            row(1, MapElement.ONE),
            row(2, MapElement.TWO),
            row(3, MapElement.THREE),
            row(4, MapElement.FOUR),
            row(5, MapElement.FIVE),
            row(6, MapElement.SIX),
            row(7, MapElement.SEVEN),
            row(8, MapElement.EIGHT),
        ),
    ) { value, expectedValue ->
        Given("${value}가 주어졌다") {
            When("해당 숫자에 해당하는 map element를 구하면") {
                Then("해당 map element가 반환된다") {
                    MapElement.of(value) shouldBe expectedValue
                }
            }
        }
    }
})
