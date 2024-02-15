package `in`.bpj4.newsapp.presentation.onboarding

sealed class OnBoardingEvent {

    data object SaveAppEntry: OnBoardingEvent()
}