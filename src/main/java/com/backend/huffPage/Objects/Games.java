package com.backend.huffPage.Objects;
import java.util.Date;
import java.util.List;

public class Games{
    public String id;
    public String sport_key;
    public String sport_title;
    public Date commence_time;
    public String home_team;
    public String away_team;
    public List<Bookmaker> bookmakers;
}