package com.mashup.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.mashup.core.model.data.local.AppPreference
import com.mashup.core.model.data.local.UserPreference
import com.mashup.datastore.data.source.AppPreferenceSerializer
import com.mashup.datastore.data.source.UserPreferenceSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

    companion object {
        private const val PATH_PB = "mashup.preferences_pb"
    }

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<UserPreference> {
        return DataStoreFactory.create(
            serializer = UserPreferenceSerializer()
        ) {
            File("${context.cacheDir.path}/$PATH_PB")
        }
    }

    @Provides
    @Singleton
    fun providesAppPreferencesDataStore(
        @ApplicationContext context: Context,
    ): DataStore<AppPreference> {
        return DataStoreFactory.create(
            serializer = AppPreferenceSerializer()
        ) {
            File("${context.cacheDir.path}/$PATH_PB")
        }
    }
}