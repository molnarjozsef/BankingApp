@file:OptIn(ExperimentalMaterial3Api::class)

package features.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.new_beneficiary_account_number_tab
import bankingapp.composeapp.generated.resources.new_beneficiary_beneficiary_account_number
import bankingapp.composeapp.generated.resources.new_beneficiary_beneficiary_account_number_hint
import bankingapp.composeapp.generated.resources.new_beneficiary_beneficiary_email_address
import bankingapp.composeapp.generated.resources.new_beneficiary_beneficiary_email_address_hint
import bankingapp.composeapp.generated.resources.new_beneficiary_beneficiary_name_hint
import bankingapp.composeapp.generated.resources.new_beneficiary_beneficiary_phone_number
import bankingapp.composeapp.generated.resources.new_beneficiary_beneficiary_tax_number
import bankingapp.composeapp.generated.resources.new_beneficiary_email_address_tab
import bankingapp.composeapp.generated.resources.new_beneficiary_next_button
import bankingapp.composeapp.generated.resources.new_beneficiary_phone_number_tab
import bankingapp.composeapp.generated.resources.new_beneficiary_tax_number_tab
import bankingapp.composeapp.generated.resources.new_beneficiary_title
import components.CloseButton
import components.Header
import components.MainButton
import components.TextField
import components.TextFieldTitle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp16
import theme.dp2
import theme.dp24
import theme.dp4
import theme.dp40

@Composable
fun NewTransferBottomSheetContent(
    closeSheet: () -> Unit,
    startTransferToEmail: (String) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { IdentifierType.entries.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxHeight(0.9f)
    ) {
        Header(
            title = stringResource(Res.string.new_beneficiary_title),
            windowInsets = WindowInsets(0, 0, 0, 0),
            endButton = { CloseButton(onClick = closeSheet) }
        )

        SecondaryScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            contentColor = AppTheme.colors.main,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(pagerState.currentPage),
                    height = dp2,
                    color = AppTheme.colors.main
                )
            },
            edgePadding = 0.dp,
        ) {
            IdentifierType.entries.forEach { identifier ->
                Tab(
                    selected = pagerState.currentPage == identifier.ordinal,
                    onClick = { scope.launch { pagerState.animateScrollToPage(identifier.ordinal) } },
                    text = {
                        Text(
                            text = stringResource(identifier.getTabNameRes()),
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
                    IdentifierType.EmailAddress -> EmailAddressInput(
                        startTransferToEmail = startTransferToEmail
                    )

                    IdentifierType.PhoneNumber -> PhoneNumberInput()
                    IdentifierType.TaxNumber -> TaxNumberInput()
                }
            }
        }

    }
}

@Composable
private fun AccountNumberInput() {
    Column {
        TextFieldTitle(text = stringResource(Res.string.new_beneficiary_beneficiary_account_number))
        Spacer(Modifier.height(dp4))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = stringResource(Res.string.new_beneficiary_beneficiary_account_number_hint)
        )

        Spacer(modifier = Modifier.height(dp24))

        TextFieldTitle(text = stringResource(Res.string.new_beneficiary_beneficiary_account_number))
        Spacer(Modifier.height(dp4))
        TextField(
            value = "",
            onValueChange = {},
            placeholder = stringResource(Res.string.new_beneficiary_beneficiary_name_hint)
        )

        ContinueButtonSection(onClick = {})
    }
}

@Composable
private fun EmailAddressInput(
    startTransferToEmail: (String) -> Unit,
) {
    var email by rememberSaveable { mutableStateOf("") }
    val regex = Regex("^[^@]+@[^@]+\\.[^@]+\$")

    Column {
        TextFieldTitle(text = stringResource(Res.string.new_beneficiary_beneficiary_email_address))
        Spacer(Modifier.height(dp4))
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = stringResource(Res.string.new_beneficiary_beneficiary_email_address_hint)
        )

        ContinueButtonSection(
            onClick = { startTransferToEmail(email) },
            enabled = regex.matches(email)
        )
    }
}

@Composable
private fun PhoneNumberInput() {
    Column {
        TextFieldTitle(text = stringResource(Res.string.new_beneficiary_beneficiary_phone_number))
        Spacer(Modifier.height(dp4))
        TextField("", {})

        ContinueButtonSection(onClick = {})
    }
}

@Composable
private fun TaxNumberInput() {
    Column {
        TextFieldTitle(text = stringResource(Res.string.new_beneficiary_beneficiary_tax_number))
        Spacer(Modifier.height(dp4))
        TextField("", {})

        ContinueButtonSection(onClick = {})
    }
}


@Composable
private fun ColumnScope.ContinueButtonSection(
    onClick: () -> Unit,
    enabled: Boolean = false,
) {
    Spacer(Modifier.weight(1f))
    Spacer(Modifier.height(dp16))
    MainButton(
        text = stringResource(Res.string.new_beneficiary_next_button),
        onClick = onClick,
        enabled = enabled,
    )
}

private enum class IdentifierType {
    AccountNumber,
    EmailAddress,
    PhoneNumber,
    TaxNumber;

    fun getTabNameRes() = when (this) {
        AccountNumber -> Res.string.new_beneficiary_account_number_tab
        EmailAddress -> Res.string.new_beneficiary_email_address_tab
        PhoneNumber -> Res.string.new_beneficiary_phone_number_tab
        TaxNumber -> Res.string.new_beneficiary_tax_number_tab
    }
}
