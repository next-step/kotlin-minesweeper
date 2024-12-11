package minesweeper.domain

import kotlin.random.Random

class Field(val lines: List<FieldLine>) {
    companion object {
        fun createField(
            fieldInfo: FieldInfo,
            mineCount: MineCount,
        ): Field {
            val spots = makeSpots(fieldInfo, mineCount)
            return Field(
                spots.chunked(fieldInfo.getWidth()).map { spotList ->
                    FieldLine(spotList)
                },
            )
        }

        private fun makeSpots(
            fieldInfo: FieldInfo,
            mineCount: MineCount,
        ): List<Spot> {
            val totalSpots = fieldInfo.getWidth() * fieldInfo.getHeight()
            require(mineCount.count <= totalSpots) { "지뢰 개수는 필드의 총 스팟보다 많을 수 없습니다." }
            val minePositions = selectMinePosition(mineCount, totalSpots)

            return (0 until totalSpots).map { spotCount ->
                val height = FieldHeight((spotCount / fieldInfo.getWidth()) + 1)
                val width = FieldWidth((spotCount % fieldInfo.getWidth()) + 1)
                (if (spotCount in minePositions) ::MineSpot else ::SafeSpot)(height, width)
            }
        }

        private fun selectMinePosition(
            mineCount: MineCount,
            totalSpots: Int,
        ): Set<Int> {
            return generateSequence { Random.nextInt(totalSpots) }
                .distinct()
                .take(mineCount.count)
                .toSet()
        }
    }
}
