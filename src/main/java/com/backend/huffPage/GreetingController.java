package com.backend.huffPage;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@CrossOrigin(origins="*")
public class GreetingController {

  private static final String template = "Hello, %s!";
  private static final String templatePost = "Hello Post, %s!";
  private final AtomicLong counter = new AtomicLong();

  @CrossOrigin
  @GetMapping("/greeting/{notesport}")
  public String greeting(@PathVariable String notesport) throws Exception {
    String s = null;
    System.out.println(notesport);
    try
    {
      JSONArray j = HuffPageConnection.getAllNotes(notesport);
      s = j.toString();
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return s;
  }
  
  @CrossOrigin
  @GetMapping("/getSliders/{slidersport}")
  public String getSliders(@PathVariable String slidersport) throws Exception {
    String s = null;
    System.out.println(slidersport);
    try
    {
      JSONArray j = HuffPageConnection.getAllSliders(slidersport);
      s = j.toString();
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return s;
  }
  
  @CrossOrigin
  @GetMapping("/getInjuries")
  public String getInjuries() throws Exception {
	  //System.out.println("Getting Injuries");
    String s = null;
    try
    {
    	JSONArray j = HuffPageConnection.getInjuries();
        s = j.toString();
    }	
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Getting Injuries" + s);
    return s;
  }
  
  @CrossOrigin
  @GetMapping("/getNBAModel")
  public String getNBAModel() throws Exception {
	  //System.out.println("Getting Injuries");
    String s = null;
    try
    {
    	JSONArray j = HuffPageConnection.getNBAModel();
        s = j.toString();
    }	
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Getting Model\n\n\n\n\n\n\n\n" + s);
    return s;
  }
  
  
  @CrossOrigin
  @GetMapping("/getVotes/{slidersport}/{index}")
  public int[] getVotes(@PathVariable String slidersport, @PathVariable String index) throws Exception {
    String s = null;
    int[] j = new int[2];
    System.out.println(slidersport);
    System.out.println(index);
    try
    {
      j = HuffPageConnection.getVotes(slidersport, index);
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return j;
  }
  
  @CrossOrigin
  @GetMapping("/getTest")
  public String getTest() throws Exception {
    String s = null;
//    System.out.println(slidersport);
//    try
//    {
//      JSONArray j = HuffPageConnection.getAllSliders(slidersport);
//      s = j.toString();
//    }
//    catch (SQLException e)
//    {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    return "Test";
  }
  
  @CrossOrigin
  @GetMapping("/getGameInfo/{gameSport}")
  public String getGameInfo(@PathVariable String gameSport) throws Exception {
    String s = null;
    System.out.println(gameSport);
    try
    {
      //JSONArray j = HuffPageConnection.getGameInfo();
    	s = HuffPageConnection.getGameInfo(gameSport);
      //s = j.toString();
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //System.out.println(s);
    return s;
  }
  
  @CrossOrigin
  @PostMapping("/greetingPost")
  public String greetingPost(@RequestBody String payload) throws Exception {
    System.out.println(payload);
    Gson gson = new Gson();
    Greeting value = gson.fromJson(payload, Greeting.class);
    String noteid = value.getNoteId();
    String notedata = value.getNoteData();
    String notetype = value.getNoteType();
    String notesport = value.getNoteSport();
    System.out.println(noteid);
    System.out.println(notedata);
    System.out.println(notesport);
    try
    {
      HuffPageConnection.addNote(noteid,notedata,notetype,notesport);
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
//    counter.incrementAndGet()
    JSONArray j = HuffPageConnection.getAllNotes(notesport);
    String s = j.toString();
    return s;
  }
  
  @CrossOrigin
  @PostMapping("/saveSlider")
  public String saveSlider(@RequestBody String payload) throws Exception {
    System.out.println(payload);
    Gson gson = new Gson();
    Greeting value = gson.fromJson(payload, Greeting.class);
    String noteid = value.getNoteId();
    String notedata = value.getNoteData();
    String notetype = value.getNoteType();
    String notesport = value.getNoteSport();
    System.out.println(noteid);
    System.out.println(notedata);
    System.out.println(notesport);
    try
    {
      HuffPageConnection.addSlider(noteid,notedata,notetype,notesport);
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
//    counter.incrementAndGet()
    JSONArray j = HuffPageConnection.getAllNotes(notesport);
    String s = j.toString();
    return s;
  }
  
  @CrossOrigin
  @PostMapping("/deleteNote")
  public String deleteNote(@RequestBody String payload) throws Exception {
    System.out.println(payload);
    Gson gson = new Gson();
    Delete value = gson.fromJson(payload, Delete.class);
    String note_uuid = value.getNoteUUID();
    String notesport = value.getNoteSport();
    String new_uuid = note_uuid;
    System.out.println(new_uuid);
    try
    {
      HuffPageConnection.deleteNote(new_uuid);
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
//    counter.incrementAndGet()
    JSONArray j = HuffPageConnection.getAllNotes(notesport);
    
    String s = j.toString();
    System.out.println(s);
    return s;
  }
}