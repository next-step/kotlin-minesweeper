package minesweeper.domain

class Field(val lines: List<FieldLine>) {
    companion object {
        fun createField(
            fieldInfo: FieldInfo,
            mineCount: MineCount,
            spotGenerator: SpotGenerator,
        ): Field {
            val spots = spotGenerator.generate(fieldInfo, mineCount)
            return Field(
                spots.chunked(fieldInfo.getWidth()).map { spotList ->
                    FieldLine(spotList)
                },
            )
        }
    }
}
