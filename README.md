<h1>
   Billboard Birthday Project 
</h1>

<p>
  The purpose of this project is to show the user what album and artist(s) were at the top of the Billboard 200 chart on the day they were born and for how long.
</p>

<h2>
  What did I learn? 🤔
</h2>

<p>
  At the beginning of this project, I actually had no idea how to access data from websites and implement it into a Java program. But the further I looked into the topic, the easier it became to grasp the fundamentals of basic web scraping. I learned how to use Java libraries and packages, something that had puzzled me before, and switched to using Visual Studio Code.

  On top of this, I also learned a bit of HTML that cemented what I already knew, and helped create this readme file! 
</p>

<h2>
  Implementation 🔍
</h2>

<p>
  Using a combination of Apache Maven and Jsoup, I was able to connect to the Billboard website and extract data from their HTML page. It took quite a bit of work and time finding where specific elements were, but once those were located, the rest was basic Java programming that I already knew from previous classes.

  At the start of the program, the user is asked to input a year, a month, and a day, each needing their own exception handling (No digits larger than 9999 or smaller than 1000 for the year for example). With these inputs, I was able to generate the link to a specific day on the Billboard 200 chart simply by creating a string with the initial link and inputs combined. 

  Once the connection to the website was made using Jsoup, I was able to look for specific HTML elements that corresponded to the album name, artist(s), and number of weeks they had been charting at #1. This was done by opening up inspect element and finding which HTML element tags were present for each of the 3 bits of information I was trying to find. Below are the specific tags I used for each variable:


</p>


<h2>
  Future plans? 📅
</h2>

<p>
  I would love to make a website that showcases this project, hopefully implementing a GUI so that the user doesn't have to do much on their end. I also want to add album covers and more information on each album, even providing links on where you can listen to it. 
</p>
