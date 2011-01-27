1. Extract the package into a folder 
2. Edit the Configuration file in config folder and change all the paths, output dir, path to wsdls etc. 
3. Set classpath to the extracted jars in dist and lib folders(If ebayAnnotationParser is required, 
	set classpath to EbayApiTool jar file). 
4. run java org.ebayopensource.turmeric.tools.annoparser.Main config=<path to Configuration.xml> which is path to the 
config file which got extracted along with this package in the config folder. 

5. You should see output generated in the output folder. Go to FindingService and click on index.html 

6. For more information, read the tutorial bundled with the distribution in the docs folder. 
