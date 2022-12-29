package minesweeper.domain.button

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.position.Position
import minesweeper.view.toContentString

class ButtonTest : FunSpec({
    context("PushableButton 객체가 정상적으로 생성된다.") {
        val givenHeight = 10
        val givenWidth = 10

        withData(
            nameFn = { "$it" },
            Position.getAllPositionList(givenHeight, givenWidth)
        ) { givenPosition ->
            val pushableButton = PushableButton(givenPosition)
            val otherPushableButton = PushableButton(givenPosition)

            pushableButton shouldNotBe null
            otherPushableButton shouldNotBe null

            pushableButton.position shouldBe givenPosition
            pushableButton shouldBe otherPushableButton
            pushableButton.hashCode() shouldBe otherPushableButton.hashCode()
        }
    }

    context("Mine 객체가 정상적으로 생성된다.") {
        val givenHeight = 10
        val givenWidth = 10

        withData(
            nameFn = { it.toContentString() },
            Position.getAllPositionList(givenHeight, givenWidth)
        ) { givenPosition ->
            val mine = Mine(givenPosition)
            val otherMine = Mine(givenPosition)

            mine shouldNotBe null
            otherMine shouldNotBe null

            mine.position shouldBe givenPosition
            mine shouldBe otherMine
            mine.hashCode() shouldBe otherMine.hashCode()
        }
    }
})
