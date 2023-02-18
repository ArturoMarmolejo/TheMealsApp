package com.example.themealsapp.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.themealsapp.data.local.MealDao
import com.example.themealsapp.data.local.MealDatabase
import com.example.themealsapp.data.remote.MealsAPI
import com.example.themealsapp.data.repository.MealRepositoryImpl
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.domain.use_case.GetFilteredMealsByArea
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.utils.NetworkState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providesMealInfoUseCase(
        repository: MealRepository,
        networkState: NetworkState
    ): GetMealsByName {
        return GetMealsByName(repository, networkState)
    }

    @Provides
    @Singleton
    fun providesFilteredNamesByAreaUseCase(
        repository: MealRepository,
        networkState: NetworkState
    ): GetFilteredMealsByArea {
        return GetFilteredMealsByArea(repository, networkState)
    }

    @Provides
    @Singleton
    fun providesMealRepository(
        db: MealDatabase,
        mealsAPI: MealsAPI
    ): MealRepository {
        return MealRepositoryImpl(mealDao = db.dao, mealsAPI = mealsAPI)
    }

    @Provides
    @Singleton
    fun providesMealDatabase(
        @ApplicationContext context: Context
    ): MealDatabase {
        return Room.databaseBuilder(
            context,
            MealDatabase::class.java,
            "themealsapp_db"
        ).build()
    }

    @Provides
    fun providesConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}