package com.example.muplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.CoreContract
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var realism: Realism

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            suspend {
                realism.delete(1)
            }
        }
    }

}


class Realism @Inject constructor(
    private val coreRepositoryImpl: CoreRepositoryImpl
): CoreContract{

    override suspend fun search(name: String): List<CoreEntityModel> {
        return coreRepositoryImpl.search(name)
    }

    override suspend fun delete(id: Long) {
        coreRepositoryImpl.delete(id)
    }

    override suspend fun insert(coreEntity: CoreEntityModel) {
        coreRepositoryImpl.insert(coreEntity)
    }
}