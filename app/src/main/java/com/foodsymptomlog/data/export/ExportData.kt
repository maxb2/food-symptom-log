package com.foodsymptomlog.data.export

data class ExportData(
    val version: Int = 1,
    val exportedAt: Long = System.currentTimeMillis(),
    val meals: List<ExportedMeal> = emptyList(),
    val symptoms: List<ExportedSymptom> = emptyList(),
    val medications: List<ExportedMedication> = emptyList(),
    val otherEntries: List<ExportedOtherEntry> = emptyList()
)

data class ExportedMeal(
    val mealType: String,
    val notes: String,
    val timestamp: Long,
    val foods: List<String>,
    val tags: List<String>
)

data class ExportedSymptom(
    val name: String,
    val severity: Int,
    val notes: String,
    val startTime: Long,
    val endTime: Long?
)

data class ExportedMedication(
    val name: String,
    val dosage: String,
    val notes: String,
    val timestamp: Long
)

data class ExportedOtherEntry(
    val entryType: String,
    val subType: String,
    val value: String,
    val notes: String,
    val timestamp: Long
)
