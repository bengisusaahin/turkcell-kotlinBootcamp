// This class was created to manage all customer operations.
class CustomerManager {

    // This list includes all customers from all customer types.
    private val customerList = mutableListOf<Customer>()

    // To add customer to customer list
    fun addCustomer(customer: Customer){
        // Thanks to add function, the customer was easily added to the customer list.
        customerList.add(customer)
    }

    // To update customer infos in the customer list
    fun updateCustomer(customerId: Int, updatedName: String? = null, updatedEmail: String? = null, updatedPhoneNumber: Long? = null, updatedLoyaltyPoints: Int? = null, updatedVipLevel: Int? = null) {
        // The indexOfFirst function is used to find the customer directly in the list.
        val index = customerList.indexOfFirst { it.generateCustomerId() == customerId }

        if (index != -1) {
            val customer = customerList[index]
            when (customer) {
                is RegularCustomer -> {
                    // If updatedName is not null then calls let function and the updated name will assign to the customer
                    updatedName?.let { customer.name = it }
                    updatedEmail?.let { customer.email = it }
                    updatedPhoneNumber?.let { customer.phoneNumber = it }
                    updatedLoyaltyPoints?.let { customer.loyaltyPoints = it }
                }
                is VIPCustomer -> {
                    updatedName?.let { customer.name = it }
                    updatedEmail?.let { customer.email = it }
                    updatedPhoneNumber?.let { customer.phoneNumber = it }
                    updatedVipLevel?.let { customer.vipLevel = it }
                }
                else -> {
                    println("Unknown customer type.")
                    return
                }
            }
            println("\nCustomer infos are updated: $customerId")
        } else {
            println("\nCustomer was not found! : $customerId . Make sure you enter the correct customer ID.")
        }
    }


    // To show all customers as a customer list
    fun listCustomers(){
        // Thanks to the forEach function, the customer was easily showed to the customer list.
        customerList.forEach {
            println(it)
        }
    }

    // To remove customer from customer list
    fun removeCustomer(customerId: Int){
        // Thanks to the removeIf function controls the customer is in the list or not and then if it is, remove.
        val removedCustomer = customerList.removeIf { it.generateCustomerId() == customerId }

        // It was shown to the user whether the customer was removed or whether such a user did not exist.
        if (removedCustomer) {
            println("\nCustomer with ID $customerId removed successfully.")
        } else {
            println("\nCustomer with ID $customerId was not found.")
        }
    }
}