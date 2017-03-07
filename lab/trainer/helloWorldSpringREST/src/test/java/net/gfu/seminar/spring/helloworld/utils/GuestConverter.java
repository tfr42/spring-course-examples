package net.gfu.seminar.spring.helloworld.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.gfu.seminar.spring.helloworld.Guest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to create and convert test data to Json and XML.
 */
public final class GuestConverter {

    public static String toJson(Guest guest) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(guest);
    }

    public static String toXml(Guest guest) {
        return convertToXml(guest);
    }

    public static String toXml(List<Guest> guestList) {
        return convertToXml(new GuestList(guestList));
    }

    public static <T> String convertToXml(T object) {
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(object.getClass());
        marshaller.setCheckForXmlRootElement(true);
        marshaller.marshal(object, result);
        return writer.toString();
    }

    public static String toJson(List<Guest> guestList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(guestList);
    }

    public static List<Guest> getTestData() throws IOException {
        return parseCSV("/names.csv");
    }

    public static List<Guest> parseCSV(String filename) throws IOException {
        List<Guest> guestList = new ArrayList<Guest>();
        InputStream asStream = GuestConverter.class.getResourceAsStream(filename);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(asStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                Guest guest = new Guest();
                guest.setName(line);
                guestList.add(guest);
            }
        } catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        return guestList;
    }

    private GuestConverter() {}
}
