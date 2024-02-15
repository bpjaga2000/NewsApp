package `in`.bpj4.newsapp.domain.usecases.appentry

import `in`.bpj4.newsapp.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke() = localUserManager.readAppEntry()

}