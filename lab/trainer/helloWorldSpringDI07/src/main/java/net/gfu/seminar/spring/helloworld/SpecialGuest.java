package net.gfu.seminar.spring.helloworld;

import java.util.Properties;

public class SpecialGuest extends Guest {

	public SpecialGuest(Properties props) {
		super(props.getProperty("firstName"), props.getProperty("lastName"));
	}
}
