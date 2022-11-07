<h1>
   Billboard Birthday Project 
</h1>

<p>
  The purpose of this project is to show the user what album and artist(s) were at the top of the Billboard 200 chart on the day they were born and for how long. You must have Apache Maven and Jsoup installed in order for the program to compile and run. 
</p>

<h2>
  What did I learn? 🤔
</h2>

<p>
  At the beginning of this project, I actually had no idea how to access data from websites and implement it into a Java program. But the further I looked into the topic, the easier it became to grasp the fundamentals of basic web scraping. I learned how to use Java libraries and packages, something that had puzzled me before, and switched to using Visual Studio Code (still testing how I like it).

  On top of this, I also learned a bit of HTML that cemented what I already knew, and helped create this README file! 
</p>

<h2>
  Implementation 🔍
</h2>

<p>
  Using a combination of Apache Maven and Jsoup, I was able to connect to the Billboard website and extract data from their HTML page. It took quite a bit of work and time finding where specific elements were, but once those were located, the rest was basic Java programming that I already knew from previous classes.

  At the start of the program, the user is asked to input a year, a month, and a day, each needing their own exception handling (No digits larger than 9999 or smaller than 1000 for the year for example). With these inputs, I was able to generate the link to a specific day on the Billboard 200 chart simply by creating a string with the initial link and inputs combined. 

```java
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
```

   In order to obtain the information I was looking for, I needed to create a method that would go onto the website, make a connection, and allow me to get any HTML that I needed. The process of doing this was a bit challenging as I had no clue where to begin using classes like URLConnection or BufferedReader, but I was ultimately able to get it to work. Here is the method I used:

```java
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
```
   
  Once the connection to the website was made using Jsoup, I was able to look for specific HTML elements that corresponded to the album name, artist(s), and number of weeks they had been charting at #1. This was done by opening up inspect element and finding which HTML element tags were present for each of the 3 bits of information I was trying to find. Below are the specific tags I utilized to find each variable:
   
```java
        Document doc = Jsoup.parse(chartData("https://www.billboard.com/charts/billboard-200/"+year+"-"+month+"-"+day));
        Elements h3 = doc.select("h3");
        Elements p = doc.select("p");
        Elements span = doc.select("span");
```

Once the tags were found, it was a matter of locating at which index they resided in. To be frank, it took quite a bit of trial and error in order to find all of them, however, I was able to come up with a neat solution to finding the index of the weeks charted at #1 which I'm proud of. Because of how span works in HTML, there was no possible way I could have found it using trial and error so I had to find something that made it unique. Luckily, I was able to use the class attribute for the element and locate it much faster. Here's what I did for each variable:
   
```java
            albumName = h3.first().text();
            artistName = p.get(1).text();
            for(Element e : span)
            {
                if(e.hasClass("c-label"))
                {
                    if(count == 1)
                    {
                        if(e.text().equals("New this week!"))
                        {
                            week = "+1";
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
```

The reasoning behind using both week and weekTemp variables was in order to be able to pass week, aka the number of weeks charted at #1, as an integer later in my code and tempWeek as the String value. This was needed as I wanted to implement a "st" vs "nd" vs "rd" vs "th" system in my output, something that could not be done solely using a String value that was given. I generated a switch case that iterates through and looks for specific cases or otherwise prints out "th" as a default. Here's the code: 
   
```java
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
```

   After all the information was stored, it was simply a matter of putting it all together in a println and sending it back to the user. I didn't show all my implementation on this page as it would be too much, but all of my code is present in the repository in which this README.file resides. Here's an example of how the program should run using my birthday:

```
Enter your birthday to see what album was #1 on the Billboard 200!
What is your birth year? (YYYY)
2003
What is your birth month? (MM)
08
What is your birth day? (DD)
07
The #1 album on the Billboard 200 chart for your birthday (2003/08/07) was "Bad Boys II" by Soundtrack
It was on its *2nd* week at #1.
```
</p>


<h2>
  Future plans? 📅
</h2>

<p>
  I had lots of fun working on this as it was my first solo project and I would most definitely want to do something similar again. I would love to make a website that showcases this or other similar future projects, hopefully implementing a GUI so that the user doesn't have to do much on their end. I also want to add album covers and more information on each album, even providing links on where you can listen to it, etc etc. 
</p>
