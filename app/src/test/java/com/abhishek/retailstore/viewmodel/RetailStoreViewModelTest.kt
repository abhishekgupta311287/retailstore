package com.abhishek.retailstore.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abhishek.retailstore.di.appModule
import com.abhishek.retailstore.di.dbModule
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Product
import com.abhishek.retailstore.repo.IRetailStoreRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
class RetailStoreViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val product = Product(
        1,
        "Television",
        100.0,
        1
    )

    private val cart = Cart(product)

    private val repo = mockk<IRetailStoreRepo>(relaxed = true)

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            listOf(dbModule, appModule)
        }
        coEvery { repo.getProductsFromCart() }.returns(listOf(cart))

    }

    @After
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun `verify add to cart`() = runBlockingTest {
        val viewModel = RetailStoreViewModel(repo)
        viewModel.addToCart(product)

        coVerify { repo.insertOrUpdate(cart) }

        assert(viewModel.cartListLiveData.value?.first?.contains(cart) == true)
    }

    @Test
    fun `verify fetch cart `() = runBlockingTest {
        val viewModel = RetailStoreViewModel(repo)
        viewModel.fetchCart()

        val (list, price) = viewModel.cartListLiveData.value!!

        assert(list.contains(cart))
        Assert.assertEquals(100.0, price, 0.0)

    }

    @Test
    fun `verify delete cart`() = runBlockingTest {
        val viewModel = RetailStoreViewModel(repo)
        viewModel.deleteCartProduct(cart)

        coVerify { repo.deleteFromCart(cart) }

    }

    @Test
    fun `verify categories populate`() = runBlocking {
        val viewModel = RetailStoreViewModel(repo)

        delay(1000)
        val categories = viewModel.categoriesLiveData.value

        Assert.assertEquals(2, categories?.size)
    }
}