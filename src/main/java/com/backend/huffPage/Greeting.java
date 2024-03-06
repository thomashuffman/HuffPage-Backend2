package com.backend.huffPage;

public class Greeting {

  private String noteid;
  private String notedata;
  private String notetype;
  private String notesport;

  public Greeting(String noteid, String notedata) {
    this.noteid = noteid;
    this.notedata = notedata;
    this.notetype = notetype;
  }

  public String getNoteId() {
    return noteid;
  }

  public String getNoteData() {
    return notedata;
  }
  
  public String getNoteType() {
    return notetype;
  }
  
  public String getNoteSport() {
	    return notesport;
	  }
}
