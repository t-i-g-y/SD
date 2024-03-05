import java.util.*

class TextEditor {
    private val history = Stack<String>()
    private var currentText = ""

    fun add(text: String) {
        history.push(currentText)
        currentText = text
    }

    fun edit(text: String) {
        history.push(currentText)
        currentText += "\n" + text
    }

    fun watch(): String {
        return currentText
    }

    fun cancel(): String {
        if (history.isNotEmpty()) {
            currentText = history.pop()
        }
        return currentText
    }

    fun runCommand(command: String) {
        val args = command.split(" ")
        when (args[0]) {
            "add" -> add(args.subList(1, args.size).joinToString(" "))
            "edit" -> edit(args.subList(1, args.size).joinToString(" "))
            "watch" -> println(watch())
            "cancel" -> println(cancel())
            else -> println("Unknown command: $command")
        }
    }
}

fun main() {
    val editor = TextEditor()
    val scanner = Scanner(System.`in`)

    while (true) {
        print("> ")
        val command = scanner.nextLine()
        if (command.lowercase(Locale.getDefault()) == "exit") {
            break
        }
        editor.runCommand(command)
    }
}