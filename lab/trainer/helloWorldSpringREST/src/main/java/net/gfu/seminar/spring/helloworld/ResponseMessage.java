package net.gfu.seminar.spring.helloworld;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage {
	private String text;

	public ResponseMessage() {
		// TODO Auto-generated constructor stub
	}

	public ResponseMessage(String string) {
		this.text = string;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
