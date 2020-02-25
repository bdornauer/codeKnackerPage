package at.qe.sepm.skeleton.ui.beans;

import javax.annotation.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope("request")
public class EmailBean {
	private String to;
	private String message;
	private String name; 

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
