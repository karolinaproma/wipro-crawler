# wipro-crawler
Web crawler for Wipro

How to run:

Please build project with "mvn clean package". Then you can change directory to target/ and run application by command "java -jar wipro-crawler-1.0.jar".
After running the application it will generate output at the console and also to the file "page_contents.txt" which will be created in the project folder.

Trade offs:

My solution prints a simple list of links and images. It does not show a gradual structure of the page.

What can I do with more time:

- improve printing to file: dynamically generate file name to avoid overwriting files generated on previous runs
- searching for other types of information (e.g. headers, texts, meta data)
- configure base URL by user
- configure output file name by user
- GUI (desktop or web)
