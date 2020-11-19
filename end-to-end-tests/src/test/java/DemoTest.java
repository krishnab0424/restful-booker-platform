import models.MessagePage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pageobjects.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class DemoTest extends TestSetup {

    @Before
    public void logIntoApplication(){
        navigateToApplication();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.populateUsername("admin");
        loginPage.populatePassword("password");
        loginPage.clickLogin();
    }

    @Test
    public void createRoomDemoTest() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        if(roomListingPage.roomFormExists()) {
            int initialRoomCount = roomListingPage.roomCount();
            roomListingPage.populateRoomNumber("111");
            roomListingPage.setRoomPrice("250");
            roomListingPage.checkWifi();
            roomListingPage.checkSafe();
            roomListingPage.checkRadio();
            roomListingPage.clickCreateRoom();
            int currentRoomCount = roomListingPage.roomCount();
            assertThat(currentRoomCount, is(initialRoomCount + 1));
        }

    }

    @Test
    public void multipleCreateRoomDemoTest() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        if(roomListingPage.roomFormExists()) {
            int j;
            for(j=1; j<5; j++){
                int roomNum = roomListingPage.generateRandomNumber(999);
                String roomNumStr = Integer.toString(roomNum);
                int roomPrice = roomListingPage.generateRandomNumber(500);
                String roomPriceStr = Integer.toString(roomPrice);
                roomListingPage.populateRoomNumber(roomNumStr);
                roomListingPage.setRoomPrice(roomPriceStr);
                roomListingPage.checkWifi();
                roomListingPage.checkSafe();
                roomListingPage.checkRadio();
                roomListingPage.clickCreateRoom();
            }
        }

    }

    @Test
    public void deleteRoomDemoTest() throws InterruptedException {
        RoomListingPage roomListingPage = new RoomListingPage(driver);
        roomListingPage.roomsToDelete();
    }

}
