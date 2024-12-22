package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.infrastructrue.CustomMinePositionSelector

class FieldTest : StringSpec({
    "필드를 생성할 수 있다." {
        val height = 3
        val width = 4
        val minePositions = setOf(Position(0, 0), Position(1, 2), Position(3, 2))
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
            row(0, 1, 2),
            row(3, 0, 0),
            row(2, 2, 2),
        ) { x, y, expected ->
            val height = 3
            val width = 4
            val minePositions = setOf(Position(0, 0), Position(1, 2), Position(3, 2))
            val mineCount = MineCount(minePositions.size)
            val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
            val spotGenerator = CustomMinePositionSelector(minePositions)

            val field = Field(fieldInfo, spotGenerator.generate(fieldInfo, mineCount))

            val spot = field.getSpot(Position(x, y)) as SafeSpot
            spot.nearbyMineCount shouldBe expected
        }
    }
})
