# wipro-crawler
Web crawler for Wipro

The task:

Please write a simple web crawler in a language of your choice in a couple of hours – please don’t spend much more than that.
The crawler should be limited to one domain. Given a starting URL – say http://wiprodigital.com - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.
The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered), showing links to other pages under the same domain, links to external URLs and links to static content such as images for each respective page.

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
