package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import javax.inject.Inject

class SaveDarkModeUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(darkMode: Boolean) = dataStoreRepository.saveDarkMode(darkMode)
}