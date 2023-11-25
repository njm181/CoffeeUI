package com.njm.coffeeui.domain.di

import android.content.Context
import androidx.room.Room
import com.njm.coffeeui.data.database.CoffeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    private val COFFEE_DATABASE_NAME = "coffee_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        CoffeeDatabase::class.java,
        name = COFFEE_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideOrderHistoryDao(db: CoffeeDatabase) = db.getOrderHistoryDao()
}