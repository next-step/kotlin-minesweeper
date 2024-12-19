package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import minesweeper.infrastructrue.CustomSpotGenerator

class FieldTest : StringSpec({
    "필드를 생성할 수 있다." {
        val height = 3
        val width = 4
        val minePositions = setOf(Pair(0, 0), Pair(1, 2), Pair(3, 2))
        val mineCount = MineCount(minePositions.size)
        val fieldInfo = FieldInfo(FieldHeight(height), FieldWidth(width))
        val spotGenerator = CustomSpotGenerator(minePositions)

        val field = Field(fieldInfo, mineCount, spotGenerator)

        field.lines.size shouldBe height

        field.lines.forEach { line ->
            line.spots.size shouldBe width
        }

        (0 until height).forEach { x ->
            (0 until width).forEach { y ->
                if (minePositions.contains(Pair(x, y))) {
                    field.lines[x].spots[y] shouldBeEqualToComparingFields MineSpot(x, y)
                } else {
                    field.lines[x].spots[y] shouldBeEqualToComparingFields SafeSpot(x, y)
                }
            }
        }
    }
})
