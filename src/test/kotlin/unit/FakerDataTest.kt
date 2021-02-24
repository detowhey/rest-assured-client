package unit

import br.qa.henriquealmeida.util.FakeData
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FakerDataTest {

    @Test
    fun `Returns a random username without a dot`() = assertFalse(FakeData.userName().contains("."))

    @Test
    fun `Returns a random username with a dot`() = assertTrue(FakeData.userName(true).contains("."))

    @Test
    fun `Must contain a valid email`() = assertTrue(FakeData.email().contains("@email.com"))

    @Test
    fun `Must contain a invalid email`() = assertFalse(FakeData.email(false).contains("@email.com"))

    @Test
    fun `Returns a random firstname`() = assertTrue(FakeData.firstName().matches(lettersAccentRegex()))

    @Test
    fun `Returns a random lastname`() = assertTrue(FakeData.lastName().matches(lettersAccentRegex()))

    @Test
    fun `Returns a random full name`() = assertTrue(FakeData.fullName().matches(lettersAccentRegex()))

    @Test
    fun `Returns a invalid CPF with special characters`() {
        val invalidCPF = FakeData.invalidCpf(true)

        assertTrue {
            invalidCPF.contains("[.-]".toRegex())
                .and(invalidCPF.contains(numbersOnlyRegex()).and(invalidCPF.length == 14))
        }
    }

    @Test
    fun `Returns a invalid CPF without special characters`() {
        val invalidCPF = FakeData.invalidCpf()
        assertTrue(invalidCPF.matches(numbersOnlyRegex()).and(invalidCPF.length == 11))
    }

    @Test
    fun `Returns a String random cellphone number with special characters`() {
        val randomCellPhoneNumber = FakeData.cellNumber(true)

        assertTrue {
            randomCellPhoneNumber.contains("[().-]".toRegex())
                .and(randomCellPhoneNumber.contains(numbersOnlyRegex()).and(randomCellPhoneNumber.length == 15))
        }
    }

    @Test
    fun `Returns a String random cellphone number without special characters`() {
        val randomCellPhoneNumber = FakeData.cellNumber()
        assertTrue(randomCellPhoneNumber.matches(numbersOnlyRegex()).and(randomCellPhoneNumber.length == 11))
    }

    @Test
    fun `Returns random address String`() {
        assertTrue(FakeData.fullAdress().isNotEmpty())
    }

    @Test
    fun `Returns random street name String`() = assertTrue(FakeData.streetName().matches(lettersAccentRegex()))

    @Test
    fun `Returns random number address String`() = assertTrue(FakeData.numberAdressString().matches(numbersOnlyRegex()))

    @Test
    fun `Returns a full state name`() {
        val stateName = FakeData.stateFullName()
        assertTrue(stateName.matches(lettersAccentRegex()).and(stateName.length > 2))
    }

    @Test
    fun `Returns the initials of a state`() {
        val stateInitials = FakeData.stateInitials()
        assertTrue(stateInitials.matches("[A-Z]+".toRegex()).and(stateInitials.length == 2))
    }

    @Test
    fun `Return a random city name String`() = assertTrue(FakeData.cityName().matches(lettersAccentRegex()))

    @Test
    fun `Return a random zipcode String with special characters`() {
        val zipCode = FakeData.zipCode(true)
        assertTrue(zipCode.matches("[0-9-]+".toRegex()).and(zipCode.length == 9))
    }

    @Test
    fun `Return a random zipcode String without special characters`() {
        val zipCode = FakeData.zipCode()
        assertTrue(FakeData.zipCode().matches(numbersOnlyRegex()).and(zipCode.length == 8))
    }


    private fun numbersOnlyRegex(): Regex = "[0-9]+".toRegex()

    private fun lettersAccentRegex(): Regex = "[A-Za-záàâãéêíóôõöúçñÁÀÂÃÉÍÇ ]+".toRegex()
}
