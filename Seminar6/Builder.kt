import java.time.LocalDate

data class Source(val name: String, val url: String)

data class StudentWorkReport(
    val title: String,
    val task: String,
    val annotation: String,
    val tableOfContents: List<String>,
    val report: String,
    val sources: List<Source>,
    val applications: List<String>
)

class StudentWorkReportBuilder {
    private var title: String = ""
    private var task: String = ""
    private var annotation: String = ""
    private var tableOfContents: MutableList<String> = mutableListOf()
    private var report: String = ""
    private var sources: MutableList<Source> = mutableListOf()
    private var applications: MutableList<String> = mutableListOf()

    fun setTitle(title: String): StudentWorkReportBuilder {
        this.title = title
        return this
    }

    fun setTask(task: String): StudentWorkReportBuilder {
        this.task = task
        return this
    }

    fun setAnnotation(annotation: String): StudentWorkReportBuilder {
        this.annotation = annotation
        return this
    }

    fun addTableOfContents(tableOfContent: String): StudentWorkReportBuilder {
        this.tableOfContents.add(tableOfContent)
        return this
    }

    fun setReport(report: String): StudentWorkReportBuilder {
        this.report = report
        return this
    }

    fun addSource(name: String, url: String): StudentWorkReportBuilder {
        this.sources.add(Source(name, url))
        return this
    }

    fun addApplication(application: String): StudentWorkReportBuilder {
        this.applications.add(application)
        return this
    }

    fun build(): StudentWorkReport {
        return StudentWorkReport(title, task, annotation, tableOfContents, report, sources, applications)
    }
}

fun main() {
    val report = StudentWorkReportBuilder()
        .setTitle("Title of the report")
        .setTask("Task of the report")
        .setAnnotation("Annotation of the report")
        .addTableOfContents("1. Introduction")
        .addTableOfContents("2. Main part")
        .addTableOfContents("3. Conclusion")
        .setReport("Report text")
        .addSource("Source 1", "https://example.com/source1")
        .addSource("Source 2", "https://example.com/source2")
        .addApplication("Application 1")
        .addApplication("Application 2")
        .build()

    println(report)
}