package com.example.economoney.di

import com.example.economoney.data.datasource.EconoMoneyDataSource
import com.example.economoney.data.repository.EconoMoneyRepo
import com.example.economoney.data.services.ApiServicesDAO
import com.example.economoney.data.services.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideEconoMoneyRepo(econoMoneyDataSource: EconoMoneyDataSource): EconoMoneyRepo =
        EconoMoneyRepo(econoMoneyDataSource)

    @Provides
    @Singleton
    fun provideEconoMoneyDataSource(apiServicesDAO: ApiServicesDAO): EconoMoneyDataSource =
        EconoMoneyDataSource(apiServicesDAO)

    @Provides
    @Singleton
    fun provideApiServicesDAO(): ApiServicesDAO =
        ApiUtils.apiServicesDAO()

}