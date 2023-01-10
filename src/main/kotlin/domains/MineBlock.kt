package domains

class MineBlock(override val position: Position) : Block(position) {
    override val marker: String = "*"
}
