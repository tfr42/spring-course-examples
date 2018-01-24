package net.gfu.seminar.spring.helloworld.utils;

import net.gfu.seminar.spring.helloworld.Guest;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class GuestConverterTest {

    @Test
    public void testGuestToJsonAndXmlConverter() throws IOException {
        List<Guest> testData = GuestConverter.getTestData();
        assertFalse(testData.isEmpty());
        System.out.println("--- LIST AS STRING ---");
        System.out.println(testData);
        System.out.println("--- LIST AS JSON ---");
        System.out.println(GuestConverter.toJson(testData));
        System.out.println("--- OBJECT AS JSON ---");
        System.out.println(GuestConverter.toJson(testData.get(0)));
        System.out.println("--- LIST AS XML ---");
        System.out.println(GuestConverter.toXml(testData));
        System.out.println("--- OBJECT AS XML ---");
        System.out.println(GuestConverter.toXml(testData.get(0)));
    }
}
