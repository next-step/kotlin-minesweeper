package domains

class NormalBlock(override val position: Position) : Block(position) {
    override val marker: String = "C"
}
