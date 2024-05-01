package org.sopt.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.rules.TemporaryFolder
import org.sopt.datastore.UserData
import org.sopt.datastore.UserDataSerializer
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataStoreModule::class]
)
object DataStoreTestModule {
    @Provides
    @Singleton
    fun providesTestUserDataStore(
        userDataSerializer: UserDataSerializer,
        tmpFolder: TemporaryFolder,
        scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    ): DataStore<UserData> =
        tmpFolder.testUserDataStore(userDataSerializer)
    fun TemporaryFolder.testUserDataStore(
        userDataSerializer: UserDataSerializer,
    ) =
        DataStoreFactory.create(
            serializer = userDataSerializer,
        ) {
            newFile("userdata_test.json")
        }
}