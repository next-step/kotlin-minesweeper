package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import minesweeper.infrastructrue.CustomMinePositionSelector

class FieldTest : StringSpec({
    "필드를 생성할 수 있다." {
        val height = 3
        val width = 4
        val minePositions = setOf(Position(1, 1), Position(2, 3), Position(4, 3))
        val mineCount = MineCount(minePositions.size)
        val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
        val spotGenerator = CustomMinePositionSelector(minePositions)

        val field = Field(fieldInfo, spotGenerator.generate(fieldInfo, mineCount))

        (0 until width).forEach { x ->
            (0 until height).forEach { y ->
                if (minePositions.contains(Position(x, y))) {
                    val mineSpot = field.getSpot(Position(x, y))
                    mineSpot.shouldBeInstanceOf<MineSpot>()
                    mineSpot.position shouldBe Position(x, y)
                } else {
                    val safeSpot = field.getSpot(Position(x, y))
                    safeSpot.shouldBeInstanceOf<SafeSpot>()
                    safeSpot.position shouldBe Position(x, y)
                }
            }
        }
    }

    /*
     * 1 0 0
     2 2 2 1
     1 * 2 *
     */
    "필드의 각 SafeSpot에 대해 주변에 있는 지뢰의 개수를 계산한다." {
        forAll(
            row(1, 2, 2),
            row(4, 1, 0),
            row(3, 3, 2),
        ) { x, y, expected ->
            val height = 3
            val width = 4
            val minePositions = setOf(Position(1, 1), Position(2, 3), Position(4, 3))
            val mineCount = MineCount(minePositions.size)
            val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
            val spotGenerator = CustomMinePositionSelector(minePositions)

            val field = Field(fieldInfo, spotGenerator.generate(fieldInfo, mineCount))

            val spot = field.getSpot(Position(x, y)) as SafeSpot
            spot.calculateNearbyMineCount(minePositions)
            spot.nearbyMineCount shouldBe expected
        }
    }

    /*
                        1 2 3 4  x
     * 1 0 0         1  C 1 0 0
     2 2 2 1     ->  2  C 2 2 1
     1 * 2 *         3  C C C C
                     y
     */
    "해당 필드에 인접한 지뢰 수가 0이면 인접한 모든 필드를 오픈한다." {
        val height = 3
        val width = 4
        val minePositions = setOf(Position(1, 1), Position(2, 3), Position(4, 3))
        val mineCount = MineCount(minePositions.size)
        val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
        val spotGenerator = CustomMinePositionSelector(minePositions)

        val field = Field(fieldInfo, spotGenerator.generate(fieldInfo, mineCount))

        field.openSpot(Position(3, 1)) shouldBeSameInstanceAs OpenResult.Success

        Position(1, 1).isClosedSpot(field)
        Position(2, 1).isOpenSpot(field)
        Position(3, 1).isOpenSpot(field)
        Position(4, 1).isOpenSpot(field)

        Position(1, 2).isClosedSpot(field)
        Position(2, 2).isOpenSpot(field)
        Position(3, 2).isOpenSpot(field)
        Position(4, 2).isOpenSpot(field)

        Position(1, 3).isClosedSpot(field)
        Position(2, 3).isClosedSpot(field)
        Position(3, 3).isClosedSpot(field)
        Position(4, 3).isClosedSpot(field)
    }

    "지뢰에 해당하는 스팟을 열면 GameOver를 반환한다." {
        val height = 3
        val width = 4
        val minePositions = setOf(Position(1, 1))
        val mineCount = MineCount(minePositions.size)
        val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
        val spotGenerator = CustomMinePositionSelector(minePositions)

        val field = Field(fieldInfo, spotGenerator.generate(fieldInfo, mineCount))

        field.openSpot(Position(1, 1)) shouldBeSameInstanceAs OpenResult.GameOver
    }
})

private fun Position.isOpenSpot(field: Field) {
    field.getSpot(this).isOpened() shouldBe true
}

private fun Position.isClosedSpot(field: Field) {
    field.getSpot(this).isClosed() shouldBe true
}
