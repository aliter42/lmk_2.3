package com.android.letmeknow;

public class row_item {
	private String title, start_date, end_date, deadline, description;

	public row_item(String title, String start_date, String end_date, String description, String deadline) {
        this.title = title;
        this.start_date = start_date;
        this.description = description;
        this.end_date = end_date;
        this.deadline = deadline;
    }
	
	public String get_title()
	{
		return title;
	}
	
	public void set_title(String title)
	{
		this.title = title;
	}
	
	public String get_start_date()
	{
		return start_date;
	}
	
	public void set_start_date(String start_date)
	{
		this.start_date = start_date;
	}
	
	public String get_end_date()
	{
		return end_date;
	}
	
	public void set_end_date(String end_date)
	{
		this.end_date = end_date;
	}
	
	public String get_deadline()
	{
		return deadline;
	}
	
	public void set_deadline(String deadline)
	{
		this.deadline = deadline;
	}
	
	public String get_description()
	{
		return description;
	}
	
	public void set_description(String description)
	{
		this.description = description;
	}

	

}
