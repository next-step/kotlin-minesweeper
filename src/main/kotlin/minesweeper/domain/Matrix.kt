package minesweeper.domain

class Matrix(val rows: List<Row>) {
    fun set(coordinate: Coordinate, dot: Field) {
        require(coordinate.y in rows.indices)

        this.rows[coordinate.y].fields[coordinate.x] = dot
    }

    fun get(coordinate: Coordinate): Field {
        require(coordinate.y in rows.indices)

        return rows[coordinate.y].fields[coordinate.x]
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
