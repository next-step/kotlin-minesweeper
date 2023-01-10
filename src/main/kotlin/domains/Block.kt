package domains

abstract class Block(open val position: Position) {
    abstract val marker: String
}
