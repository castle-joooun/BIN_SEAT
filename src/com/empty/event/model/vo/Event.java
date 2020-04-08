package com.empty.event.model.vo;

import java.sql.Date;

public class Event {
	
	private int eventNo;
	private String eventTitle;
	private String eventWriter;
	private String eventContent;
	private Date eventDate;
	private int eventCount;
	
	public Event() {}

	public Event(int eventNo, String eventTitle, String eventWriter, String eventContent, Date eventDate,
			int eventCount) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.eventWriter = eventWriter;
		this.eventContent = eventContent;
		this.eventDate = eventDate;
		this.eventCount = eventCount;
	}
	
	


	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventWriter() {
		return eventWriter;
	}

	public void setEventWriter(String eventWriter) {
		this.eventWriter = eventWriter;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	@Override
	public String toString() {
		return "Event [eventNo=" + eventNo + ", eventTitle=" + eventTitle + ", eventWriter=" + eventWriter
				+ ", eventContent=" + eventContent + ", eventDate=" + eventDate + ", eventCount=" + eventCount + "]";
	}
	
	

}
