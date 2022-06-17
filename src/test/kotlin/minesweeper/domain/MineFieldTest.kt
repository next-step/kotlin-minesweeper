package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.field.Coordinate
import minesweeper.domain.field.CoordinateValue
import minesweeper.domain.field.Field
import minesweeper.domain.field.Mine
import minesweeper.domain.vo.Height
import minesweeper.domain.vo.NumberOfMine
import minesweeper.domain.vo.Width

class MineFieldTest : StringSpec({
    "지뢰 찾기를 위한 판을 생성할수 있다." {
        shouldNotThrow<Throwable> { MineField(listOf(Field(Coordinate(CoordinateValue(1), CoordinateValue(1)), Mine))) }
    }

    "필드가 비어있는 지뢰판을 생성할 경우 Exception을 던진다." {
        shouldThrow<IllegalArgumentException> { MineField(emptyList()) }
    }

    "높이, 너비, 지뢰 갯수를 입력 받아 지뢰 판을 생성할수 있다." {
        val height = Height(5)
        val width = Width(5)
        val numberOfMine = NumberOfMine(1)
        val mineCoordinates = listOf(
            Coordinate(
                CoordinateValue(0),
                CoordinateValue(0)
            )
        )

        val mineField = MineFieldFactory.create(height, width, numberOfMine) { _, _ -> mineCoordinates }

        mineField.fields.size shouldBe height.value * width.value
        mineField.fields.first().dot shouldBe Mine
        mineField.fields.count { it.dot == Mine } shouldBe numberOfMine.value
    }
})
