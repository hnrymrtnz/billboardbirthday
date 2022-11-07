package com.hnrymrtnz.projects;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;  
import org.jsoup.nodes.Element;

import java.net.*;  
import java.io.*;
import java.util.Scanner;


public class BillboardBirthday
{
   public static void main(String[] args)
   {  
    System.out.println("Enter your birthday to see what album was #1 on the Billboard 200!");

    String year = "";
    String month = "";
    String day = "";
    String temp = "";

    Scanner in = new Scanner(System.in);
      
    try{
        System.out.println("What is your birth year? (YYYY)");
        temp = in.next();
        if(temp.length() != 4)
        {
            throw new FileNotFoundException("Unable to find year. Please try again using the 'YYYY' format. Exiting program.");
        }
        else
        {
            year = temp;
        }

        System.out.println("What is your birth month? (MM)");
        temp = in.next();
        if(temp.length() != 2)
        {
            throw new FileNotFoundException("Unable to find month. Please try again using the 'MM' format. Exiting program.");
        }
        else
        {
            month = temp;
        }

        System.out.println("What is your birth day? (DD)");
        temp = in.next();
        if(temp.length() != 2)
        {
            throw new FileNotFoundException("Unable to find day. Please try again using the 'DD' format. Exiting program.");
        }
        else
        {
            day = temp;
        }
      
        String albumName = "";
        String artistName = "";
        String week = "";
        String weekTemp = "";
        String iden = "";
        int count = 0;
        Document doc = Jsoup.parse(chartData("https://www.billboard.com/charts/billboard-200/"+year+"-"+month+"-"+day));
        Elements h3 = doc.select("h3");
        Elements p = doc.select("p");
        Elements span = doc.select("span");

        if(h3.isEmpty())
        {
            System.out.println("It looks like there's no recorded data for that date. Try again with another.");
        }
        else
        {
            albumName = h3.first().text();
            artistName = p.get(1).text();
            for(Element e : span)
            {
                if(e.hasClass("c-label"))
                {
                    if(count == 1)
                    {
                        if(e.text().equals("New this week!") || e.text().equals("NEW"))
                        {
                            week = "+1";
                            weekTemp = "1";
                        }
                        else
                        {
                            week = "+" + e.text();
                            weekTemp = e.text();
                        }
                    }

                    count++;
                }
            }

            switch(Integer.parseInt(week))
            {
                case 1:
                case 21:
                case 31:
                case 41:
                case 51:
                case 61:
                    week = weekTemp;
                    iden = "st";
                    break;
                case 2:
                case 22:
                case 32:
                case 42:
                case 52:
                case 62:
                    week = weekTemp;
                    iden = "nd";
                    break;
                case 3:
                case 23:
                case 33:
                case 43:
                case 53:
                case 63:
                    week = weekTemp;
                    iden = "rd";
                    break;
                default:
                    week = weekTemp;
                    iden = "th";
                    break;
            }

            System.out.println("The #1 album on the Billboard 200 chart for your birthday (" + year + "/" + month + "/" + day + ") was " + "\"" + albumName + "\"" + " by " + artistName);
            System.out.println("It was on its *" + week + iden + "* week at #1.");
        }

        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());  
        }

        finally{
            in.close();
        }
   }
   
   public static String chartData(String link)
   {
      StringBuilder data = new StringBuilder();
      
      try
      {
         URL url = new URL(link);
         URLConnection urlConnection = url.openConnection();
         
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); 
         String line;
         
         while((line = bufferedReader.readLine()) != null)
         {
            data.append(line + "\n");
         }
         bufferedReader.close();
      }
      
      catch(Exception e)
      {
         e.printStackTrace();
      }
      
      return data.toString();
   }
}
