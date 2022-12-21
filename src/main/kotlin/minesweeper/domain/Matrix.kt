package minesweeper.domain

class Matrix(val rows: List<Row>) {
    operator fun set(x: Int, y: Int, dot: Field) {
        require(y in (1..rows.size))

        this.rows[y - 1][x] = dot
    }

    operator fun set(coordinate: Coordinate, dot: Field) {
        require(coordinate.y in (1..rows.size))

        this.rows[coordinate.y - 1][coordinate.x] = dot
    }

    operator fun get(x: Int, y: Int): Field {
        require(y in (1..rows.size))

        return rows[y - 1][x]
    }

    operator fun get(coordinate: Coordinate): Field {
        require(coordinate.y in (1..rows.size))

        return rows[coordinate.y - 1][coordinate.x]
    }

    fun coordinates(): List<Coordinate> {
        return (1..rows.size).flatMap { y: Int ->
            (1..rows[0].fields.size).map { x: Int ->
                Coordinate(x, y)
            }
        }
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
