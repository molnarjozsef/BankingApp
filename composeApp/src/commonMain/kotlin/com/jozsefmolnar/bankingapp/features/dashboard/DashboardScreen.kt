@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.bankingapp.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_administrative_transactions
import bankingapp.composeapp.generated.resources.dashboard_financial_transactions
import bankingapp.composeapp.generated.resources.dashboard_more_transactions
import bankingapp.composeapp.generated.resources.dashboard_todos
import com.jozsefmolnar.bankingapp.Routes
import com.jozsefmolnar.bankingapp.components.HorizontalCardButton
import com.jozsefmolnar.bankingapp.components.VerticalCardButton
import com.jozsefmolnar.bankingapp.model.domain.BankConfig
import com.jozsefmolnar.bankingapp.model.domain.DefaultBank
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp16
import com.jozsefmolnar.bankingapp.theme.dp24
import com.jozsefmolnar.bankingapp.theme.dp32
import com.jozsefmolnar.bankingapp.theme.dp56
import com.jozsefmolnar.bankingapp.theme.dp8
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun DashboardScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<DashboardViewModel>()
    val currentBank by viewModel.currentBank.collectAsState(DefaultBank)
    val amount by viewModel.amount.collectAsState()

    val scope = rememberCoroutineScope()
    var showMenu by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (showMenu) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showMenu = false },
            containerColor = AppTheme.colors.backgroundNeutral,
            dragHandle = null,
            shape = RoundedCornerShape(
                topStart = dp8,
                topEnd = dp8,
            ),
            contentWindowInsets = { BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom) },
        ) {
            NewTransferBottomSheetContent(
                closeSheet = {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showMenu = false
                            }
                        }
                },
                startTransferToEmail = { email ->
                    viewModel.setRecipientEmail(email)
                    navController.navigate(Routes.RouteNewTransfer)
                }
            )
        }
    }


    DashboardScreenContent(
        currentBank = currentBank,
        money = amount,
        showNewTransferSheet = { showMenu = true },
    )
}

@Composable
fun DashboardScreenContent(
    currentBank: BankConfig,
    money: Int?,
    showNewTransferSheet: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(AppTheme.colors.backgroundColored)
        ) {
            Spacer(Modifier.height(dp16))

            Accounts(
                currentBank = currentBank,
                money = money
            )

            Spacer(Modifier.height(dp24))

            Transactions()

            Spacer(Modifier.height(dp8))
        }

        Column(
            Modifier.padding(horizontal = dp16)
        ) {
            Spacer(Modifier.height(dp24))

            QuickFeatures(
                showNewTransferSheet = showNewTransferSheet,
            )

            Spacer(Modifier.height(dp24))

            VerticalCardButton(
                text = stringResource(Res.string.dashboard_more_transactions),
                icon = rememberVectorPainter(Icons.Filled.MoreHoriz),
                onClick = {},
            )

            Spacer(Modifier.height(dp32))

            Text(
                text = stringResource(Res.string.dashboard_todos),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.textDarker
            )

            Spacer(Modifier.height(dp16))

            HorizontalCardButton(
                text = stringResource(Res.string.dashboard_financial_transactions),
                icon = rememberVectorPainter(Icons.Filled.SyncAlt),
                onClick = {},
            )

            Spacer(Modifier.height(dp16))

            HorizontalCardButton(
                text = stringResource(Res.string.dashboard_administrative_transactions),
                icon = rememberVectorPainter(Icons.Filled.Description),
                onClick = {},
            )

            Spacer(Modifier.height(dp56))
        }
    }
}

