package minesweeper.domain

class Matrix(val rows: List<Row>) {
    operator fun set(x: Int, y: Int, dot: Field) {
        require(y in rows.indices)

        this.rows[y][x] = dot
    }

    operator fun set(coordinate: Coordinate, dot: Field) {
        require(coordinate.y in rows.indices)

        this.rows[coordinate.y][coordinate.x] = dot
    }

    operator fun get(x: Int, y: Int): Field {
        require(y in rows.indices)

        return rows[y][x]
    }

    operator fun get(coordinate: Coordinate): Field {
        require(coordinate.y in rows.indices)

        return rows[coordinate.y][coordinate.x]
    }

    fun width(): Int {
        return rows[0].fields.size
    }

    fun height(): Int {
        return rows.size
    }

    companion object {
        fun of(width: Int, height: Int): Matrix {
            return Matrix(
                List(height) {
                    Row.init(width)
                }
            )
        }
    }
}
