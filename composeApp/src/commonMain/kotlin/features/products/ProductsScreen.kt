package features.products

import BankConfig
import DefaultBank
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.BusinessCenter
import androidx.compose.material.icons.outlined.CarCrash
import androidx.compose.material.icons.outlined.CarRepair
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.RealEstateAgent
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.extras_cheque_payment
import bankingapp.composeapp.generated.resources.products_available_products
import bankingapp.composeapp.generated.resources.products_insurances
import bankingapp.composeapp.generated.resources.products_insurances_casco
import bankingapp.composeapp.generated.resources.products_insurances_home
import bankingapp.composeapp.generated.resources.products_insurances_life
import bankingapp.composeapp.generated.resources.products_insurances_motor_third_party_liability
import bankingapp.composeapp.generated.resources.products_insurances_sme
import bankingapp.composeapp.generated.resources.products_insurances_travel
import bankingapp.composeapp.generated.resources.products_investments
import bankingapp.composeapp.generated.resources.products_investments_regular
import bankingapp.composeapp.generated.resources.products_loans
import bankingapp.composeapp.generated.resources.products_loans_personal_loan
import bankingapp.composeapp.generated.resources.products_my_products
import bankingapp.composeapp.generated.resources.products_my_products_cards
import bankingapp.composeapp.generated.resources.products_my_products_cards_count
import bankingapp.composeapp.generated.resources.products_my_products_insurances
import bankingapp.composeapp.generated.resources.products_my_products_insurances_count
import bankingapp.composeapp.generated.resources.products_my_products_loans
import bankingapp.composeapp.generated.resources.products_my_products_loans_count
import components.MyProductCard
import components.Product
import components.ProductSection
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp40
import kotlin.random.Random

@Composable
fun ProductsScreen() {
    val viewModel = koinViewModel<ProductsViewModel>()
    val currentBank by viewModel.currentBank.collectAsState(DefaultBank)

    ProductsScreenContent(currentBank = currentBank)
}

@Composable
fun ProductsScreenContent(
    currentBank: BankConfig,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = dp40)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = dp16),
            text = stringResource(Res.string.products_my_products),
            color = AppTheme.colors.textDarker,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )

        Spacer(Modifier.height(dp16))

        MyProducts()

        Spacer(Modifier.height(dp24))

        Text(
            modifier = Modifier.padding(horizontal = dp16),
            text = stringResource(Res.string.products_available_products),
            color = AppTheme.colors.textDarker,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )

        Spacer(Modifier.height(dp24))

        Loans(currentBank = currentBank)

        Spacer(Modifier.height(dp24))

        Insurances(currentBank = currentBank)

        Spacer(Modifier.height(dp24))

        Investments()
    }

}

@Composable
private fun MyProducts() {
    val cardCount: Int = rememberSaveable { Random.nextInt(1, 3) }
    val loanCount: Int = rememberSaveable { Random.nextInt(1, 3) }
    val insuranceCount: Int = rememberSaveable { Random.nextInt(1, 3) }

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = dp16),
        horizontalArrangement = Arrangement.spacedBy(dp16),
    ) {
        MyProductCard(
            text = stringResource(Res.string.products_my_products_cards),
            subtitle = pluralStringResource(
                resource = Res.plurals.products_my_products_cards_count,
                quantity = cardCount,
                cardCount
            ),
            icon = Icons.Outlined.CreditCard,
        )
        MyProductCard(
            text = stringResource(Res.string.products_my_products_loans),
            subtitle = pluralStringResource(
                resource = Res.plurals.products_my_products_loans_count,
                quantity = loanCount,
                loanCount
            ),
            icon = Icons.Outlined.RealEstateAgent,
        )
        MyProductCard(
            text = stringResource(Res.string.products_my_products_insurances),
            subtitle = pluralStringResource(
                resource = Res.plurals.products_my_products_insurances_count,
                quantity = insuranceCount,
                insuranceCount
            ),
            icon = Icons.Outlined.Shield,
        )
    }
}

@Composable
private fun Loans(
    currentBank: BankConfig,
) {
    ProductSection(
        title = stringResource(Res.string.products_loans),
        products = listOf(
            Product(
                name = stringResource(Res.string.products_loans_personal_loan, currentBank),
                icon = Icons.Outlined.Payments,
            )
        ),
        color = AppTheme.colors.productOrange,
    )
}


@Composable
private fun Insurances(
    currentBank: BankConfig,
) {
    ProductSection(
        title = stringResource(Res.string.products_insurances),
        products = listOf(
            Product(
                name = stringResource(Res.string.products_insurances_travel, currentBank),
                icon = Icons.Outlined.Flight,
            ),
            Product(
                name = stringResource(Res.string.products_insurances_home, currentBank),
                icon = Icons.Outlined.Home,
            ),
            Product(
                name = stringResource(
                    Res.string.products_insurances_motor_third_party_liability,
                    currentBank
                ),
                icon = Icons.Outlined.CarCrash,
            ),
            Product(
                name = stringResource(Res.string.products_insurances_casco, currentBank),
                icon = Icons.Outlined.CarRepair,
            ),
            Product(
                name = stringResource(Res.string.products_insurances_life, currentBank),
                icon = Icons.Outlined.FavoriteBorder,
            ),
            Product(
                name = stringResource(Res.string.products_insurances_sme, currentBank),
                icon = Icons.Outlined.BusinessCenter,
            ),
        ),
        color = AppTheme.colors.productYellow,
    )
}

@Composable
private fun Investments() {
    ProductSection(
        title = stringResource(Res.string.products_investments),
        products = listOf(
            Product(
                name = stringResource(Res.string.products_investments_regular),
                icon = Icons.Outlined.CurrencyExchange,
            ),
            Product(
                name = stringResource(Res.string.extras_cheque_payment),
                icon = Icons.Outlined.AttachMoney,
            ),
        ),
        color = AppTheme.colors.productPurple,
    )
}
