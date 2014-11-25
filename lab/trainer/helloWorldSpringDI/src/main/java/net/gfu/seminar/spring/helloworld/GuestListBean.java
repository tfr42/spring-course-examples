package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class GuestListBean implements InitializingBean {

	private List<String> guests;

	public GuestListBean() {
	}

	public GuestListBean(List<String> guests) {
		this.guests = guests;
	}

	public List<String> getGuests() {
		return guests;
	}

	public void setGuests(List<String> guests) {
		this.guests = guests;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this.guests);
	}

}
