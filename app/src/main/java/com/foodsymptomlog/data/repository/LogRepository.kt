package com.foodsymptomlog.data.repository

import com.foodsymptomlog.data.dao.BowelMovementDao
import com.foodsymptomlog.data.dao.MealDao
import com.foodsymptomlog.data.dao.MedicationDao
import com.foodsymptomlog.data.dao.OtherEntryDao
import com.foodsymptomlog.data.dao.SymptomEntryDao
import com.foodsymptomlog.data.entity.BowelMovementEntry
import com.foodsymptomlog.data.entity.MealEntry
import com.foodsymptomlog.data.entity.MealType
import com.foodsymptomlog.data.entity.MealWithDetails
import com.foodsymptomlog.data.entity.MedicationEntry
import com.foodsymptomlog.data.entity.OtherEntry
import com.foodsymptomlog.data.entity.SymptomEntry
import com.foodsymptomlog.data.entity.Tag
import kotlinx.coroutines.flow.Flow

class LogRepository(
    private val mealDao: MealDao,
    private val symptomEntryDao: SymptomEntryDao,
    private val bowelMovementDao: BowelMovementDao,
    private val medicationDao: MedicationDao,
    private val otherEntryDao: OtherEntryDao
) {
    val allMeals: Flow<List<MealWithDetails>> = mealDao.getAllMealsWithDetails()
    val allSymptomEntries: Flow<List<SymptomEntry>> = symptomEntryDao.getAllSymptomEntries()
    val allBowelMovements: Flow<List<BowelMovementEntry>> = bowelMovementDao.getAllBowelMovements()
    val ongoingSymptoms: Flow<List<SymptomEntry>> = symptomEntryDao.getOngoingSymptoms()
    val allTags: Flow<List<Tag>> = mealDao.getAllTags()
    val allMedications: Flow<List<MedicationEntry>> = medicationDao.getAllMedications()
    val allMedicationNames: Flow<List<String>> = medicationDao.getAllMedicationNames()
    val allOtherEntries: Flow<List<OtherEntry>> = otherEntryDao.getAllOtherEntries()

    fun getRecentMeals(limit: Int = 5): Flow<List<MealWithDetails>> {
        return mealDao.getRecentMealsWithDetails(limit)
    }

    fun getRecentSymptomEntries(limit: Int = 5): Flow<List<SymptomEntry>> {
        return symptomEntryDao.getRecentSymptomEntries(limit)
    }

    fun getRecentBowelMovements(limit: Int = 5): Flow<List<BowelMovementEntry>> {
        return bowelMovementDao.getRecentBowelMovements(limit)
    }

    fun getRecentMedications(limit: Int = 5): Flow<List<MedicationEntry>> {
        return medicationDao.getRecentMedications(limit)
    }

    fun getRecentOtherEntries(limit: Int = 5): Flow<List<OtherEntry>> {
        return otherEntryDao.getRecentOtherEntries(limit)
    }

    suspend fun insertMeal(
        mealType: MealType,
        foods: List<String>,
        tags: List<String>,
        notes: String = "",
        timestamp: Long = System.currentTimeMillis()
    ): Long {
        val meal = MealEntry(mealType = mealType, notes = notes, timestamp = timestamp)
        return mealDao.insertMealWithDetails(meal, foods, tags)
    }

    suspend fun insertSymptom(symptomEntry: SymptomEntry): Long {
        return symptomEntryDao.insert(symptomEntry)
    }

    suspend fun insertBowelMovement(entry: BowelMovementEntry): Long {
        return bowelMovementDao.insert(entry)
    }

    suspend fun deleteMeal(meal: MealEntry) {
        mealDao.deleteMeal(meal)
    }

    suspend fun deleteSymptom(symptomEntry: SymptomEntry) {
        symptomEntryDao.delete(symptomEntry)
    }

    suspend fun endSymptom(id: Long, endTime: Long = System.currentTimeMillis()) {
        symptomEntryDao.updateEndTime(id, endTime)
    }

    suspend fun deleteBowelMovement(entry: BowelMovementEntry) {
        bowelMovementDao.delete(entry)
    }

    suspend fun deleteMealById(id: Long) {
        mealDao.deleteMealById(id)
    }

    suspend fun deleteSymptomById(id: Long) {
        symptomEntryDao.deleteById(id)
    }

    suspend fun deleteBowelMovementById(id: Long) {
        bowelMovementDao.deleteById(id)
    }

    // Update methods
    suspend fun updateSymptom(symptomEntry: SymptomEntry) {
        symptomEntryDao.update(symptomEntry)
    }

    suspend fun updateBowelMovement(entry: BowelMovementEntry) {
        bowelMovementDao.update(entry)
    }

    suspend fun updateMeal(
        meal: MealEntry,
        foods: List<String>,
        tags: List<String>
    ) {
        mealDao.updateMealWithDetails(meal, foods, tags)
    }

    // Get by ID methods
    suspend fun getSymptomById(id: Long): SymptomEntry? {
        return symptomEntryDao.getById(id)
    }

    suspend fun getBowelMovementById(id: Long): BowelMovementEntry? {
        return bowelMovementDao.getById(id)
    }

    suspend fun getMealWithDetailsById(id: Long): MealWithDetails? {
        return mealDao.getMealWithDetailsById(id)
    }

    // Medication methods
    suspend fun insertMedication(entry: MedicationEntry): Long {
        return medicationDao.insert(entry)
    }

    suspend fun updateMedication(entry: MedicationEntry) {
        medicationDao.update(entry)
    }

    suspend fun deleteMedication(entry: MedicationEntry) {
        medicationDao.delete(entry)
    }

    suspend fun getMedicationById(id: Long): MedicationEntry? {
        return medicationDao.getById(id)
    }

    // Other entry methods
    suspend fun insertOtherEntry(entry: OtherEntry): Long {
        return otherEntryDao.insert(entry)
    }

    suspend fun updateOtherEntry(entry: OtherEntry) {
        otherEntryDao.update(entry)
    }

    suspend fun deleteOtherEntry(entry: OtherEntry) {
        otherEntryDao.delete(entry)
    }

    suspend fun getOtherEntryById(id: Long): OtherEntry? {
        return otherEntryDao.getById(id)
    }
}
