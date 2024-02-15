package `in`.bpj4.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import `in`.bpj4.newsapp.presentation.Dimens
import `in`.bpj4.newsapp.presentation.common.NewsButton
import `in`.bpj4.newsapp.presentation.common.NewsTextButton
import `in`.bpj4.newsapp.presentation.common.PageIndicator
import `in`.bpj4.newsapp.presentation.onboarding.components.OnBoardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    pages.size - 1 -> listOf("Back", "Get Started")
                    else -> listOf("Back", "Next")
                }
            }
        }

        val coroutine = rememberCoroutineScope()

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MediumPadding1)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(Dimens.PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (buttonState.value[0].isNotBlank()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = { coroutine.launch { pagerState.animateScrollToPage(page = pagerState.currentPage - 1) } }
                    )
                    Spacer(modifier = Modifier.width(Dimens.MediumPadding1))
                }


                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        if (pagerState.currentPage == pagerState.pageCount - 1) {
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            coroutine.launch { pagerState.animateScrollToPage(page = (pagerState.currentPage + 1) % pagerState.pageCount) }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}