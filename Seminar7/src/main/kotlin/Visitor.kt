import java.util.*

sealed class BracketNode {
    class RB(val char: Char) : BracketNode() {
        override fun accept(visitor: BracketVisitor) {
            visitor.visitRB(this)
        }
    }

    class SB(val children: MutableList<BracketNode>) : BracketNode() {
        override fun accept(visitor: BracketVisitor) {
            visitor.visitSB(this)
        }
    }

    class FB(var child: BracketNode) : BracketNode() {
        override fun accept(visitor: BracketVisitor) {
            visitor.visitFB(this)
        }
    }

    abstract fun accept(visitor: BracketVisitor)

    class BracketVisitor {
        private var output = StringBuilder()

        fun visitRB(node: RB) {
            output.append(node.char)
        }

        fun visitSB(node: SB) {
            output.append("{")
            node.children.forEach { it.accept(this) }
            output.append("}")
        }

        fun visitFB(node: FB) {
            node.child.accept(this)
            output.append("{")
        }

        fun getResult(): String {
            return output.toString()
        }
    }

    fun toBracketExpression(): String {
        val sb = StringBuilder()
        toBracketExpression(this, sb)
        return sb.toString()
    }

    private fun toBracketExpression(node: BracketNode, sb: StringBuilder) {
        when (node) {
            is RB -> sb.append(node.char)
            is SB -> {
                node.children.forEach { toBracketExpression(it, sb) }
            }
            is FB -> {
                toBracketExpression(node.child, sb)
                sb.append("{")
            }
        }
    }
}

fun parseBracketExpression(expression: String): BracketNode {
    val stack = Stack<BracketNode>()
    var i = 0
    while (i < expression.length) {
        when (expression[i]) {
            '(' -> stack.push(BracketNode.RB('('))
            '[' -> stack.push(BracketNode.RB('['))
            ')' -> {
                val top = stack.pop()
                if (top !is BracketNode.RB) {
                    throw IllegalStateException("Mismatched brackets")
                }
                if (!stack.isEmpty() && stack.peek() is BracketNode.SB) {
                    val parent = stack.pop() as BracketNode.SB
                    parent.children.add(top)
                    stack.push(parent)
                } else {
                    stack.push(top)
                }
            }
            ']' -> {
                val top = stack.pop()
                if (top !is BracketNode.RB) {
                    throw IllegalStateException("Mismatched brackets")
                }
                if (stack.isNotEmpty() && stack.peek() is BracketNode.FB) {
                    val parent = stack.pop() as BracketNode.FB
                    parent.child = top
                    stack.push(parent)
                } else {
                    stack.push(top)
                }
            }
            '{' -> {
                val parent = if (stack.isNotEmpty() && stack.peek() is BracketNode.FB) {
                    stack.pop() as BracketNode.FB
                } else {
                    BracketNode.SB(mutableListOf())
                }
                stack.push(parent)
            }
            '}' -> {
                val top = stack.pop()
                if (top is BracketNode.SB) {
                    top.children.forEach { it.accept(BracketNode.BracketVisitor()) }
                    println(top.toBracketExpression())
                }
            }
        }
        i++
    }
    return stack.pop()
}


fun main() {
    val expression = "({[]{[()]}[[{}]])"
    val rootNode = parseBracketExpression(expression)
    val visitor = BracketNode.BracketVisitor()
    rootNode.accept(visitor)
    println(visitor.getResult())
}