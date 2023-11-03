package com.example.digikala.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.digikala.util.Constants.Datastore_Name
import kotlinx.coroutines.flow.first
import javax.inject.Inject


private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Datastore_Name)

class DataStoreRepositoryImpl @Inject constructor(private val context: Context) :
    DataStoreRepository {
    override suspend fun putString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.datastore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferenceKey = intPreferencesKey(key)
        context.datastore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preferences = context.datastore.data.first()
            preferences[preferenceKey]
        } catch (exp: Exception) {
            exp.printStackTrace()
            null
        }
    }

    override suspend fun getInt(key: String): Int? {
        return try {
            val preferenceKey = intPreferencesKey(key)
            val preference = context.datastore.data.first()
            preference[preferenceKey]
        } catch (exp: Exception) {
            exp.printStackTrace()
            null
        }
    }
}