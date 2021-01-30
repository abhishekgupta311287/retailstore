# Retail Store
=========================

This app is a kind of retail store app and displays products from pre-populated json data.

Introduction
-------------
The app consists of multiple fragment bind to single activity and viewmodel.

RetailStoreFragment displays the available products category wise.

ProductDetailsFragment displays the details of the product along with option to add the product to cart.

CartFragment displays the list of products added to the cart along with the total price of all the added products.

### Functionality
##### Display product from pre-populated json data
##### Display details of the product
##### Add product to the cart
##### Display all products added to the cart
##### Delete product from cart
##### Display total price of all the products added to the cart

### Architecture Used
MVVM with LiveData and Repository pattern

####Unit Test
Yet to be written. The architecture of the app has been such that all the components and unit testable using mockito library.
Room integration can be tested using intrumentation test and running against a device.
UI test can be written using espresso and mocking viewmodel

### Libraries
* Android Support Library
* Android Architecture Components
* Koin for dependency injection
* Kotlin coroutines