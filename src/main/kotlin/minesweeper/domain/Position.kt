package minesweeper.domain

enum class Position {
    Top {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            val top = index - columns
            if (top < 0) return -1
            return top
        }
    },
    TopLeft {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            val top = index - columns
            if (top < 0) return -1
            if (top % columns <= 0) return -1
            return top - 1
        }
    },
    TopRight {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            val top = index - columns
            if (top < 0) return -1
            val topRight = top + 1
            if (topRight % columns <= 0) return -1
            return topRight
        }
    },
    Left {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            if (index == 0) return -1
            if (index % columns <= 0) return -1
            return index - 1
        }
    },
    Right {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            val right = index + 1
            if (right % columns <= 0) return -1
            return right
        }
    },
    Bottom {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            val bottom = index + columns
            if (bottom > (rows * columns - 1)) return -1
            return bottom
        }
    },
    BottomLeft {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            val bottom = index + columns
            if (bottom > (rows * columns - 1)) return -1
            if (bottom % columns <= 0) return -1
            return bottom - 1
        }
    },
    BottomRight {
        override fun calculate(rows: Int, columns: Int, index: Int): Int {
            val bottom = index + columns
            if (bottom > (rows * columns - 1)) return -1
            if (bottom < 0) return -1
            val bottomRight = bottom + 1
            if (bottomRight % columns <= 0) return -1
            return bottomRight
        }
    };

    abstract fun calculate(rows: Int, columns: Int, index: Int): Int

    companion object {
        private val tokenRegex = "[,:]".toRegex()

        fun toIndex(columnSize: Int, positionText: String): Int {
            val result: List<String> = positionText.split(tokenRegex)
            val columnIndex = result[0].toIntOrNull()?.minus(1)
            val rowIndex = result[1].toIntOrNull()?.minus(1)

            requireNotNull(columnIndex) { "입력된 행 좌표는 숫자 값이어야 합니다" }
            requireNotNull(rowIndex) { "입력된 열 좌표는 숫자 값이어야 합니다" }
            require(columnIndex >= 0) { "입력된 행 좌표는 양수 이어야 합니다." }
            require(rowIndex >= 0) { "입력된 열 좌표는 양수 이어야 합니다." }

            return rowIndex * columnSize + columnIndex
        }
    }
}
