package com.backend.huffPage;

public class Delete {

  private String note_uuid;
  private String notesport;

  public Delete(String note_uuid, String notesport) {
	    this.note_uuid = note_uuid;
	    this.notesport = notesport;
	  }

  public String getNoteUUID() {
    return note_uuid;
  }

  public String getNoteSport() {
    return notesport;
  }
  
}
