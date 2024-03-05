import java.time.LocalDate

interface ComputerComponent{
    val nomenclatureNumber: Int
    val name: String
    val price: Double
}

data class Motherboard(
    override val nomenclatureNumber: Int,
    override val name: String,
    override val price: Double,
    val socketType: String,
    val processorCount: Int,
    val ramType: String
) : ComputerComponent {
    override fun toString(): String {
        return "Nomenclature number: $nomenclatureNumber, Name: $name, Price: $price, Socket type: $socketType, Processor count: $processorCount, RAM type: $ramType"
    }
}

data class Processor(
    override val nomenclatureNumber: Int,
    override val name: String,
    override val price: Double,
    val socketType: String,
    val coreCount: Int,
    val clockSpeed: Double
) : ComputerComponent {
    override fun toString(): String {
        return "Nomenclature number: $nomenclatureNumber, Name: $name, Price: $price, Socket type: $socketType, Core count: $coreCount, Clock speed: $clockSpeed"
    }
}

data class HardDrive(
    override val nomenclatureNumber: Int,
    override val name: String,
    override val price: Double,
    val capacity: Int,
    val rotationSpeed: Int
) : ComputerComponent {
    override fun toString(): String {
        return "Nomenclature number: $nomenclatureNumber, Name: $name, Price: $price, Capacity: $capacity, Rotation speed: $rotationSpeed"
    }
}

class ComputerComponentFactory {
    fun createMotherboard(nomenclatureNumber: Int, name: String, price: Double, socketType: String, processorCount: Int, ramType: String): Motherboard {
        return Motherboard(nomenclatureNumber, name, price, socketType, processorCount, ramType)
    }

    fun createProcessor(nomenclatureNumber: Int, name: String, price: Double, socketType: String, coreCount: Int, clockSpeed: Double): Processor {
        return Processor(nomenclatureNumber, name, price, socketType, coreCount, clockSpeed)
    }

    fun createHardDrive(nomenclatureNumber: Int, name: String, price: Double, capacity: Int, rotationSpeed: Int): HardDrive {
        return HardDrive(nomenclatureNumber, name, price, capacity, rotationSpeed)
    }
}

fun displayCatalog(factory: ComputerComponentFactory) {
    println("Component nomenclature:")
    println("======================")
    factory.createMotherboard(1, "Terabyte", 20000.0, "1234", 4, "DDR16")
        .also { println(it) }
    factory.createProcessor(2, "Info Core x5", 30000.0, "2048", 16, 8.0)
        .also { println(it) }
    factory.createHardDrive(3, "WaterGate SSD 2TB", 1000.0, 2000, 5000)
        .also { println(it) }
}

fun displayProductDetails(factory: ComputerComponentFactory, nomenclatureNumber: Int) {
    println("Detailed product information:")
    println("==========================")
    when (nomenclatureNumber) {
        1 -> factory.createMotherboard(nomenclatureNumber, "", 0.0, "", 0, "").also { println(it) }
        2 -> factory.createProcessor(nomenclatureNumber, "", 0.0, "", 0, 0.0)
            .also { println(it) }
        3 -> factory.createHardDrive(nomenclatureNumber, "", 0.0, 0, 0)
            .also { println(it) }
        else -> println("Product number not found")
    }
}

fun main() {
    val factory = ComputerComponentFactory()
    displayCatalog(factory)
    println()
    displayProductDetails(factory, 2)
}