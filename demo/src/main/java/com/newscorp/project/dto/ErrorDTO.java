package com.newscorp.project.dto;

import java.util.Date;

public class ErrorDTO {

	// Denotes application level error code.
	private int code;

	// Meaning text describing the cause of error.
	private String text;

	// Hints on how the error can be fixed.
	private String hints;

	// Error occured date
	private Date date;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHints() {
		return hints;
	}

	public void setHints(String hints) {
		this.hints = hints;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ErrorDTO [code=" + code + ", text=" + text + ", hints=" + hints + ", date=" + date + "]";
	}
}
