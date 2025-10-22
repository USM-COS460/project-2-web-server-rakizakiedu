## COS 460/540 - Computer Networks
# Project 2: HTTP Server

# <<Rise Akizaki>>

This project is written in Java on Windows 11.

## How to compile
Assuming you are on Windows, and downloaded, and unzipped the zip folder from the Github:

1. Open Command Prompt (type "cmd" into the search bar)
2. cd into the 2nd "src" folder (ie, where the java files are kept)
3. Once you are in the folder, type "javac HTTPServerTest.java HTTPClient.java", and hit enter. 
The files should now compile.

## How to run

Assuming you are on Windows, have telnet enabled, and compiled the java files:

To get the standard response:

1. Open Command Prompt (type "cmd" into the search bar)
2. cd into the 1st "src" folder (ie, the folder that contains another "src" folder)
3. Once you are in the 1st "src" folder, type "java src.HTTPServerTest <<CHOICE OF PORT NUMBER>> <<DIRECTORY WHERE HTML/CSS FILE IS LOCATED (In our case "www")>>", and hit enter.
A message should pop up saying: "Server Port: <<CHOICE OF PORT NUMBER>>"

4. Open another Command Prompt tab.
5. Type "telnet localhost <<PORT NUMBER>>", and hit enter. You should now see a blank screen. 
6. Type "GET /<<NAME OF FILE (In our case, index.html>> HTTP/1.0". You won't be able to see the letters you type, so be careful typing. Once you
are finished, hit enter.
7. You should now see the HTML code of the index.html file. If you were able to scroll up (or commented out line 68 in HTTPClient.java)
you would also be able to see the something along these lines:

HTTP/1.0 200 OK
Date: MONDAY, 20 10 2025 21:35:32.393734500 EST
Server: HTTPServerTest/1.0
Content-Type: text/html
Content-Length: 838

To get to the actual webpage:

1. Open Command Prompt (type "cmd" into the search bar)
2. cd into the 1st "src" folder (ie, the folder that contains another "src" folder)
3. Once you are in the 1st "src" folder, type "java src.HTTPServerTest <<CHOICE OF PORT NUMBER>> <<DIRECTORY WHERE HTML/CSS FILE IS LOCATED (In our case "www")>>", and hit enter.
A message should pop up saying: "Server Port: <<CHOICE OF PORT NUMBER>>"

4. Open a browser of your choice.
5. Go to the link: "http://localhost:<<PORT NUMBER>>/<<FILE NAME>>" to load the given file. In our case, the file would be index.html
6. You should now see the index.html webpage, complete with css, and an image of a cat.:
Note that only the following MIME types are supported: HTML, CSS, JPEG, PNG, GIF, WEBP

## My experience with this project

The initial steps of the project weren't too difficult (aside from some initial trouble being able to open this project in Eclipse), since I had already coded a java socket server in the previous assignment. I mostly just copied the code from the previous assignment, and made a few changes; Namely removing any trace of the number guessing game, and stripping it to just a plain java socket server. Creating the threads took longer, since I haven't worked with them before. I took a look at the "All About Sockets" java tutorial linked on brightspace, which had pseudo code on how to handle multiple clients with threads. So, using that, and by consulting some online resources, I was able to figure out how to create a new thread for every new client. 

The rest of the assignment didn't go so well for me, to say the very least. After I coded the initial server, I was at a complete loss on what to do. I knew I had to use BufferedReaders + FileReaders to read the the files in "www", but I didn't know what to do beyond that. This stage was really just a matter of a bunch of trial-and-error, and looking up a bunch of resources online. So, this stage was pretty unorganized. But, a very basic summary of my work-flow was:

1. Implement a HTTP webserver
2. Get the webserver to display the HTML
3. Get the webserver to display the CSS
4. Get the webserver to display images

This is obviously a very basic summary, when in reality, there were many more steps involved in-between these steps. For example, one of the bigger challenges was a step between 3-4, which was figuring out how to get my code to support multiple MIME types; As in, how I get my code, which currently only reads strings (HTML, CSS) to also read image files (jpegs). 
