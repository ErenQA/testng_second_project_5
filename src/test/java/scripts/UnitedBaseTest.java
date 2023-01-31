package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

import java.util.stream.IntStream;

public class UnitedBaseTest extends UnitedBase {
    /*
        Test Case 1: Validate "Main menu" navigation items
        Given user is on "https://www.united.com/en/us"
        Then user should see “Main menu” navigation items
        BOOK
        MY TRIPS
        TRAVEL INFO
        MILEAGEPLUS® PROGRAM|
        DEALS
        HELP
     */
    @Test(priority = 1, description = "Validate 'Main menu' navigation items")
    public void validateMainMenuNavigationItems() {
        String[] expectedData = {"BOOK", "MY TRIPS", "TRAVEL INFO", "MILEAGEPLUS® PROGRAM", "DEALS", "HELP"};

        IntStream.range(0, expectedData.length)
                .forEach(i -> unitedBasePage.headersList.get(i).isDisplayed());
        IntStream.range(0, expectedData.length)
                .forEach(i -> Assert.assertEquals(unitedBasePage.headersList.get(i).getText(), expectedData[i]));
    }

    /*
        Test Case 2: Validate "Book travel menu" navigation items
        Given user is on "https://www.united.com/en/us"
        Then user should see "Book travel menu" navigation items
        Book
        Flight Status|
        Check-in
        My trips
     */
    @Test(priority = 2, description = "Validate 'Book travel menu' navigation items")
    public void validateTravelMenuItems() {
        String[] expectedData = {"Book", "Flight status", "Check-in", "My trips"};

        for (int i = 0; i < expectedData.length; i++) {
            Assert.assertTrue(unitedBasePage.travelMenuItems.get(i).isDisplayed());
            Assert.assertEquals(unitedBasePage.travelMenuItems.get(i).getText(), expectedData[i]);
        }
    }

    /*
        Test Case 3: Validate "Round-trip" and "One-way" radio buttons
        Given user is on "https://www.united.com/en/us"
        Then validate "Roundtrip" radio button is displayed, is enabled and is selected
        And validate "One-way" radio button is displayed, is enabled but is not selected
        When user clicks on "One-way" radio button
        Then validate "One-way" radio button is selected while "Roundtrip" radio button is
        deselected
     */
    @Test(priority = 3, description = "Validate 'Round-trip' and 'One-way' radio buttons")
    public void validateRadioButtons() {

        for (int i = 0; i < unitedBasePage.radioButtonsInput.size(); i++) {
            Assert.assertTrue(unitedBasePage.radioButtonsLabel.get(i).isDisplayed());
            Assert.assertTrue(unitedBasePage.radioButtonsLabel.get(i).isEnabled());
        }

        Assert.assertTrue(unitedBasePage.radioButtonsInput.get(0).isSelected());
        Assert.assertFalse(unitedBasePage.radioButtonsInput.get(1).isSelected());

        unitedBasePage.radioButtonsInput.get(1).click();

        Assert.assertTrue(unitedBasePage.radioButtonsInput.get(1).isSelected());

        Assert.assertFalse(unitedBasePage.radioButtonsInput.get(0).isSelected());
    }

    /*
        Test Case 4: Validate "Book with miles" and "Flexible dates" checkboxes
        Given user is on "https://www.united.com/en/us"
        Then validate "Book with miles" checkbox is displayed, is enabled but is not selected
        And validate "Flexible dates" checkbox is displayed, is enabled but is not selected
        When user clicks both checkboxes
        Then validate both checkboxes are selected
        When user clicks on both selected checkboxes again
        Then validate both checkboxes are deselected
     */
    @Test(priority = 4, description = "Validate 'Book with miles' and 'Flexible dates' checkboxes")
    public void validateCheckboxesButtons() {

        for (int i = 0; i < unitedBasePage.radioButtonsInput.size(); i++) {
            Assert.assertTrue(unitedBasePage.checkboxButtonsLabel.get(i).isDisplayed());
            Assert.assertTrue(unitedBasePage.checkboxButtonsLabel.get(i).isEnabled());
            Assert.assertFalse(unitedBasePage.checkboxButtonsInput.get(i).isSelected());
        }


        for (int i = 0; i < unitedBasePage.checkboxButtonsInput.size(); i++) {
            unitedBasePage.checkboxButtonsLabel.get(i).click();
            Assert.assertTrue(unitedBasePage.checkboxButtonsInput.get(i).isSelected());
        }

        for (int i = 0; i < unitedBasePage.checkboxButtonsInput.size(); i++) {
            unitedBasePage.checkboxButtonsLabel.get(i).click();
            Assert.assertFalse(unitedBasePage.checkboxButtonsInput.get(i).isSelected());
        }
    }

    /*
        Test Case 5: Validate One-way ticket search results "from Chicago, IL, US (ORD) to
        Miami, FL, US (MIA)”
        Given user is on "https://www.united.com/en/us"
        When user selects "One-way" ticket radio button
        And User enters "Chicago, IL, US (ORD)" to from input box
        And User enters "Miami, FL, US (MIA)" to to input box
        And User selects "Feb 28" to the dates input box
        And User selects "2 Adults" from travelers selector
        And User selects "Business or First" from cabin dropdown
        And User clicks on "Find Flights" button
        Then validate departure equals to "DEPART ON: February 28"
     */
    @Test(priority = 5, description = "Validate One-way ticket search results")
    public void validateOneWayTicketSearch() {

        unitedBasePage.radioButtonsInput.get(1).click();

        unitedBasePage.originInputBox.clear();
        unitedBasePage.originInputBox.sendKeys("Chicago ORD");

        unitedBasePage.destinationInputBox.sendKeys("Miami MIA");

        unitedBasePage.departureDateInput.clear();
        unitedBasePage.departureDateInput.sendKeys("02/28/2023");

        unitedBasePage.travelersButton.click();
        unitedBasePage.adultTravelerInput.sendKeys("2");

        unitedBasePage.cabingTypeButton.click();
        unitedBasePage.selectCabinType("Business or first");

        unitedBasePage.findFlightsButton.click();

        Waiter.pause(10);
    }
}