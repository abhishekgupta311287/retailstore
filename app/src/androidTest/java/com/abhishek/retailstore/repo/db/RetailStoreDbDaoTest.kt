package com.abhishek.retailstore.repo.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abhishek.retailstore.model.Cart
import com.abhishek.retailstore.model.Product
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RetailStoreDbDaoTest {

    private val product = Product(
        1,
        "Television",
        100.0,
        1
    )

    private val cart = Cart(product)

    private lateinit var db: RetailStoreDb
    private lateinit var dao: IRetailStoreDbDao

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(appContext, RetailStoreDb::class.java)
            .fallbackToDestructiveMigration()
            .build()
        dao = db.retailStoreDao()

    }

    @After
    fun close() = runBlocking {
        dao.deleteFromCart(cart)
        db.close()
    }

    @Test
    fun verifyAddToCart() = runBlocking {
        dao.addToCart(cart)

        val productsFromCart = dao.getProductsFromCart()

        Assert.assertEquals(1, productsFromCart.size)

        val cart1 = productsFromCart[0]

        Assert.assertEquals(1, cart1.quantity)
        Assert.assertEquals(100.0, cart1.totalPrice, 0.0)
        Assert.assertEquals(product, cart1.product)

    }

    @Test
    fun verifyInsertUpdateToCart() = runBlocking {
        dao.insertOrUpdate(cart)

        var productsFromCart = dao.getProductsFromCart()

        Assert.assertEquals(1, productsFromCart.size)

        var cart1 = productsFromCart[0]

        Assert.assertEquals(1, cart1.quantity)
        Assert.assertEquals(100.0, cart1.totalPrice, 0.0)

        dao.insertOrUpdate(cart)

        productsFromCart = dao.getProductsFromCart()

        Assert.assertEquals(1, productsFromCart.size)

        cart1 = productsFromCart[0]

        Assert.assertEquals(2, cart1.quantity)
        Assert.assertEquals(200.0, cart1.totalPrice, 0.0)

        dao.insertOrUpdate(cart.copy(product = product.copy(2, "Vaccum", 200.0, 2)))

        productsFromCart = dao.getProductsFromCart()

        Assert.assertEquals(2, productsFromCart.size)

        cart1 = productsFromCart[1]

        Assert.assertEquals(cart1.product.name, "Vaccum")
        Assert.assertEquals(cart1.product.id, 2)
        Assert.assertEquals(cart1.product.price, 200.0, 0.0)
        Assert.assertEquals(cart1.product.image, 2)

    }

    @Test
    fun verifyDeleteCart() = runBlocking {
        dao.insertOrUpdate(cart)

        var productsFromCart = dao.getProductsFromCart()
        Assert.assertEquals(1, productsFromCart.size)

        dao.deleteFromCart(cart)
        productsFromCart = dao.getProductsFromCart()

        Assert.assertEquals(0, productsFromCart.size)

    }

    @Test
    fun verifyExistingProductInCart() = runBlocking {
        dao.insertOrUpdate(cart)

        val cart1 = dao.isProductExistsInCart(1)

        Assert.assertEquals(cart, cart1)
        Assert.assertEquals(cart.product, cart1?.product)
        Assert.assertEquals(cart.product.name, cart1?.product?.name)

    }
}