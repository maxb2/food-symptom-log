package com.privatehealthjournal.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.privatehealthjournal.data.entity.BloodGlucoseEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodGlucoseDao {
    @Query("SELECT * FROM blood_glucose_entries ORDER BY timestamp DESC")
    fun getAllBloodGlucoseEntries(): Flow<List<BloodGlucoseEntry>>

    @Query("SELECT * FROM blood_glucose_entries ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentBloodGlucoseEntries(limit: Int): Flow<List<BloodGlucoseEntry>>

    @Query("SELECT * FROM blood_glucose_entries WHERE id = :id")
    suspend fun getById(id: Long): BloodGlucoseEntry?

    @Insert
    suspend fun insert(entry: BloodGlucoseEntry): Long

    @Update
    suspend fun update(entry: BloodGlucoseEntry)

    @Delete
    suspend fun delete(entry: BloodGlucoseEntry)

    @Query("DELETE FROM blood_glucose_entries WHERE id = :id")
    suspend fun deleteById(id: Long)
}
