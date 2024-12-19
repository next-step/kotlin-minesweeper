package minesweeper.domain

class Field(
    private val fieldInfo: FieldInfo,
    private val mineCount: MineCount,
    private val spotGenerator: SpotGenerator,
) {
    val lines: List<FieldLine> = createField()
    private val width = fieldInfo.getWidth()

    init {
        validateMineCount()
    }

    private fun createField(): List<FieldLine> {
        val spots = spotGenerator.generate(fieldInfo, mineCount)
        return spots.chunked(fieldInfo.getWidth()).map { lineSpots ->
            FieldLine(lineSpots)
        }
    }

    private fun validateMineCount() {
        val height = fieldInfo.getHeight()
        val totalSpots = height * width
        require(mineCount.count <= totalSpots) { "지뢰 개수는 필드의 총 스팟보다 많을 수 없습니다." }
    }
}
