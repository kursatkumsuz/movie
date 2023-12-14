package com.kursatkumsuz.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kursatkumsuz.domain.repository.DataStoreRepository
import com.kursatkumsuz.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import androidx.datastore.preferences.core.Preferences

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.DATASTORE_PREFERENCES_NAME)

class DataStoreRepositoryImpl(private val context: Context) : DataStoreRepository {

    private val dataStore = context.dataStore
    override suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.edit {
            it[Constants.DATASTORE_PREFERENCES_KEY] = isCompleted
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { value ->
                val state = value[Constants.DATASTORE_PREFERENCES_KEY] ?: false
                state
            }
    }

}