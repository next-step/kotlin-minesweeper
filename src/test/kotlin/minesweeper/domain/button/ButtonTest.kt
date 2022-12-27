package minesweeper.domain.button

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.position.data.PositionDataSet

class ButtonTest : FunSpec({
    test("PushableButton 객체가 정상적으로 생성된다.") {
        // Given
        val givenPosition = PositionDataSet.testData()

        // When
        val pushableButton = PushableButton(givenPosition)
        val otherPushableButton = PushableButton(givenPosition)

        // Then
        pushableButton shouldNotBe null
        pushableButton.position shouldBe givenPosition
        pushableButton shouldBe otherPushableButton
        pushableButton.hashCode() shouldBe otherPushableButton.hashCode()
    }

    test("Mine 객체가 정상적으로 생성된다.") {
        // Given
        val givenPosition = PositionDataSet.testData()

        // When
        val mine = Mine(givenPosition)
        val otherMine = Mine(givenPosition)

        // Then
        mine shouldNotBe null
        mine.position shouldBe givenPosition
        mine shouldBe otherMine
        mine.hashCode() shouldBe otherMine.hashCode()
    }

    context("Button 객체가 정상적으로 생성된다.") {
        val givenPosition = PositionDataSet.testData()

        withData(
            nameFn = { "${it.javaClass}" },
            listOf(
                PushableButton(givenPosition),
                Mine(givenPosition)
            )
        ) { button: Button ->
            when (button) {
                is Mine -> button shouldBe Mine.of(givenPosition.row, givenPosition.col)
                is PushableButton -> button shouldBe PushableButton.of(givenPosition.row, givenPosition.col)
            }
        }
    }
})
