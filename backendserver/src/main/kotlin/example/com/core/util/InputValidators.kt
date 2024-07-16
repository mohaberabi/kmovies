package example.com.core.util


class InputValidators {
    fun validateEmail(email: String): Boolean {
        val emailRegex = Regex("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$")
        return emailRegex.matches(email)
    }


    fun valdiatePassword(
        password: String,
    ): Boolean = password.length >= 8
}