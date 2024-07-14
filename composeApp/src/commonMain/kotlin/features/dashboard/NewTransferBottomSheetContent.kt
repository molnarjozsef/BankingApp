@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package features.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.CloseButton
import components.Header
import components.MainButton
import components.TextField
import kotlinx.coroutines.launch
import theme.AppTheme
import theme.dp16
import theme.dp2
import theme.dp24
import theme.dp4
import theme.dp40

@Composable
fun NewTransferBottomSheetContent() {
    val pagerState = rememberPagerState(pageCount = { IdentifierType.entries.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxHeight(0.9f)
    ) {
        Header(
            title = "új kedv",
            windowInsets = WindowInsets(0, 0, 0, 0),
            endButton = { CloseButton { } }
        )
        SecondaryScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            contentColor = AppTheme.colors.main,
            indicator = { tabPositions ->
                if (pagerState.currentPage < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        height = dp2,
                        color = AppTheme.colors.main
                    )
                }
            },
            edgePadding = 0.dp,
        ) {
            IdentifierType.entries.forEach { identifier ->
                Tab(
                    selected = pagerState.currentPage == identifier.ordinal,
                    onClick = { scope.launch { pagerState.animateScrollToPage(identifier.ordinal) } },
                    text = {
                        Text(
                            text = identifier.name,
                            maxLines = 1
                        )
                    },
                    selectedContentColor = AppTheme.colors.textDarker,
                    unselectedContentColor = AppTheme.colors.textDark,
                )
            }
        }
        HorizontalPager(
            state = pagerState,
        ) { index ->
            val type = IdentifierType.entries[index]

            Box(
                modifier = Modifier
                    .padding(top = dp40, bottom = dp24)
                    .padding(horizontal = dp16)
            ) {

                when (type) {
                    IdentifierType.AccountNumber -> AccountNumberInput()
                    IdentifierType.EmailAddress -> EmailAddressInput()
                    IdentifierType.PhoneNumber -> PhoneNumberInput()
                    IdentifierType.TaxId -> TaxIdInput()
                }
            }
        }

    }
}

@Composable
private fun AccountNumberInput() {
    Column {
        InputFieldTitle("kedv neve")
        Spacer(Modifier.height(dp4))
        TextField("", {}, "asd")
        Spacer(Modifier.height(dp24))
        InputFieldTitle("kedv számla")
        Spacer(Modifier.height(dp4))
        TextField("", {}, "asd")
        Spacer(Modifier.weight(1f))
        MainButton("tovább", {})
    }
}

@Composable
private fun EmailAddressInput() {
    Column {
        InputFieldTitle("kedv email")
        Spacer(Modifier.height(dp4))
        TextField("", {}, "asd")
        Spacer(Modifier.weight(1f))
        MainButton("tovább", {})
    }
}

@Composable
private fun PhoneNumberInput() {
    Column {
        InputFieldTitle("kedv teló")
        Spacer(Modifier.height(dp4))
        TextField("", {}, "asd")
        Spacer(Modifier.weight(1f))
        MainButton("tovább", {})
    }
}

@Composable
private fun TaxIdInput() {
    Column {
        InputFieldTitle("kedv adó")
        Spacer(Modifier.height(dp4))
        TextField("", {}, "asd")
        Spacer(Modifier.weight(1f))
        MainButton("tovább", {})
    }
}

@Composable
private fun InputFieldTitle(
    text: String,
) {
    Text(
        text = text,
        color = AppTheme.colors.textDark,
        fontSize = 14.sp,
    )
}

private enum class IdentifierType {
    AccountNumber,
    EmailAddress,
    PhoneNumber,
    TaxId
}
