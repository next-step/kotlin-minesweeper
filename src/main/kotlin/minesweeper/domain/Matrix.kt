package minesweeper.domain

class Matrix(val rows: List<Row>) {
    fun land(coordinate: Coordinate, field: Field) {
        require(coordinate.y in rows.indices)

        this.rows[coordinate.y].fields[coordinate.x] = field
    }

    fun field(coordinate: Coordinate): Field {
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
