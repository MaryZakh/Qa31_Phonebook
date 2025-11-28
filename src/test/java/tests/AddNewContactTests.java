package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("margo@gmail.com")
                    .withPassword("Mmar123456$"));
        }
    }

    @Test
    public void addContactSuccessAllFields() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("Tony"+i)
                .lastName("Molly")
                .email("tony" + i + "@gmail.com")
                .phone("34343434" + i)
                .address("Haifa")
                .description("all fields")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }

    @Test
    public void addContactSuccessReqFields() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("TonyReq")
                .lastName("Molly")
                .email("tony" + i + "@gmail.com")
                .phone("34343434" + i)
                .address("Haifa")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Molly")
                .email("tony@gmail.com")
                .phone("343434343434")
                .address("Haifa")
                .description("wrong name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
    }
    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .email("tony@gmail.com")
                .phone("343434343434")
                .address("Haifa")
                .description("wrong last name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());

    }
    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .email("tonygmail.com")
                .phone("343434343434")
                .address("Haifa")
                .description("wrong email")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid:"));


    }

    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .email("tony@gmail.com")
                .phone("")
                .address("Haifa")
                .description("wrong phone")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .email("tony@gmail.com")
                .phone("343434343434")
                .address("")
                .description("wrong address")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());

    }

}
